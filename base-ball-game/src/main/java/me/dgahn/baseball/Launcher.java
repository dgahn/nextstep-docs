package me.dgahn.baseball;

import java.util.Random;
import java.util.Scanner;

import me.dgahn.baseball.domain.BaseBall;
import me.dgahn.baseball.domain.RandomBaseBallGenerator;
import me.dgahn.baseball.view.BaseBallConsoleView;
import me.dgahn.baseball.view.BaseBallViewModel;

public class Launcher {
	public static void main(final String[] args) {
		final var random = new Random();
		final var generator = new RandomBaseBallGenerator(random);
		final var viewModel = new BaseBallViewModel(generator);
		final var scanner = new Scanner(System.in);
		final var view = new BaseBallConsoleView(scanner);

		runLoop(viewModel, view);
	}

	private static void runLoop(
		final BaseBallViewModel viewModel,
		final BaseBallConsoleView view
	) {
		do {
			play(viewModel, view);
			replay(viewModel, view);
		} while (viewModel.isContinued());
	}

	private static void play(
		final BaseBallViewModel viewModel,
		final BaseBallConsoleView view
	) {
		while (!viewModel.isCompleted()) {
			viewModel.process(createBaseBall(viewModel, view));

			final var result = viewModel.getBaseBallResult();
			view.info(result.getResultMessage());
		}
	}

	private static void replay(
		final BaseBallViewModel viewModel,
		final BaseBallConsoleView view
	) {
		try {
			final String input = view.input(viewModel.getOutput());
			viewModel.setContinued(input);
		} catch (final IllegalArgumentException e) {
			view.error(e.getMessage());
			replay(viewModel, view);
		}
	}

	private static BaseBall createBaseBall(
		final BaseBallViewModel viewModel,
		final BaseBallConsoleView view
	) {
		try {
			final String output = viewModel.getOutput();
			return new BaseBall(view.input(output));
		} catch (IllegalArgumentException e) {
			view.error(e.getMessage());
			return createBaseBall(viewModel, view);
		}
	}
}

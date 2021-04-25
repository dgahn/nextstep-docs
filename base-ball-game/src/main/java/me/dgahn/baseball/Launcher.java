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
		} while (retry(viewModel, view));
	}

	private static boolean retry(
		final BaseBallViewModel viewModel,
		final BaseBallConsoleView view
	) {
		final boolean retry = view.retry();
		if (retry) {
			viewModel.setCompleted(false);
		}
		return retry;
	}

	private static void play(
		final BaseBallViewModel viewModel,
		final BaseBallConsoleView view
	) {
		while (!viewModel.isCompleted()) {
			final BaseBall answerBall = view.input();
			viewModel.process(answerBall);

			final var result = viewModel.getBaseBallResult();
			view.result(result);
		}
	}
}

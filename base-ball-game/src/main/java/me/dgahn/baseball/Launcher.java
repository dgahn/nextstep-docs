package me.dgahn.baseball;

import java.util.Scanner;

import me.dgahn.baseball.domain.BaseBallResult;
import me.dgahn.baseball.repo.RandomNumberGenerator;
import me.dgahn.baseball.view.BaseBallConsoleView;
import me.dgahn.baseball.view.BaseBallViewModel;

public class Launcher {
	public static void main(final String[] args) {
		final var generator = new RandomNumberGenerator();
		final var viewModel = new BaseBallViewModel(generator);
		final var scanner = new Scanner(System.in);
		final var view = new BaseBallConsoleView(scanner);

		playGame(viewModel, view);
	}

	private static void playGame(BaseBallViewModel viewModel, BaseBallConsoleView view) {
		while (true) {
			final var answerBall = view.getBaseball();
			final var result = viewModel.valid(answerBall);

			view.result(result);

			if (result == BaseBallResult.THREE_STRIKE && !view.again()) {
				break;
			}
		}
	}
}

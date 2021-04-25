package me.dgahn.baseball;

import me.dgahn.baseball.application.BaseBallApplicationService;
import me.dgahn.baseball.domain.BaseBallResult;
import me.dgahn.baseball.repo.BaseBallRepository;
import me.dgahn.baseball.view.BaseBallConsoleView;

public class Launcher {
	public static void main(String[] args) {
		final var repository = new BaseBallRepository();
		final var service = new BaseBallApplicationService(repository);
		final var view = new BaseBallConsoleView();

		playGame(repository, service, view);
	}

	private static void playGame(BaseBallRepository repository,
		BaseBallApplicationService service, BaseBallConsoleView view) {
		while (true) {
			final var answerBall = view.getBaseball();
			final var result = service.valid(answerBall);

			view.result(result);

			if (result == BaseBallResult.THREE_STRIKE && !view.again()) {
				break;
			}
		}
	}
}

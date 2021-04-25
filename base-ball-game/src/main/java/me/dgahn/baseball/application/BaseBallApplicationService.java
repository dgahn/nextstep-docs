package me.dgahn.baseball.application;

import static java.lang.System.*;

import me.dgahn.baseball.domain.BaseBall;
import me.dgahn.baseball.domain.BaseBallResult;
import me.dgahn.baseball.repo.BaseBallRepository;

public class BaseBallApplicationService {

	private final BaseBallRepository repository;

	public BaseBallApplicationService(final BaseBallRepository repository) {
		this.repository = repository;
	}

	public BaseBallResult valid(final BaseBall answerBall) {
		final var problemBall = repository.getBaseBall().orElseGet(() -> {
			newProblemBall();
			return repository.getBaseBall().get();
		});

		final var baseBallResult = problemBall.prepare(answerBall);
		if (baseBallResult == BaseBallResult.THREE_STRIKE) {
			repository.setBaseBall(null);
		}
		return baseBallResult;
	}

	private void newProblemBall() {
		final var newBaseBall = new BaseBall();
		out.println(newBaseBall.getValue());
		repository.setBaseBall(newBaseBall);
	}

}


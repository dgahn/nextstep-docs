package me.dgahn.baseball.view;

import static java.lang.System.*;

import me.dgahn.baseball.domain.BaseBall;
import me.dgahn.baseball.domain.BaseBallResult;
import me.dgahn.baseball.repo.RandomNumberGenerator;

public class BaseBallViewModel {

	private final RandomNumberGenerator generator;

	public BaseBallViewModel(final RandomNumberGenerator generator) {
		this.generator = generator;
	}

	public BaseBallResult valid(final BaseBall answerBall) {
		final var problemBall = generator.getBaseBall().orElseGet(() -> {
			newProblemBall();
			return generator.getBaseBall().get();
		});

		final var baseBallResult = problemBall.prepare(answerBall);
		if (baseBallResult == BaseBallResult.THREE_STRIKE) {
			generator.setBaseBall(null);
		}
		return baseBallResult;
	}

	private void newProblemBall() {
		final var newBaseBall = new BaseBall();
		out.println(newBaseBall.getValue());
		generator.setBaseBall(newBaseBall);
	}

}

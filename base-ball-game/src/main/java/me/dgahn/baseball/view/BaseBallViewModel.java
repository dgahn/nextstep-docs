package me.dgahn.baseball.view;

import me.dgahn.baseball.domain.BaseBall;
import me.dgahn.baseball.domain.BaseBallResult;
import me.dgahn.baseball.domain.RandomBaseBallGenerator;

public class BaseBallViewModel {

	private final RandomBaseBallGenerator generator;
	private boolean completed = false;
	private BaseBallResult baseBallResult;

	public BaseBallViewModel(final RandomBaseBallGenerator generator) {
		this.generator = generator;
	}

	public boolean isCompleted() {
		return completed;
	}

	public BaseBallResult getBaseBallResult() {
		return baseBallResult;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public void process(final BaseBall answerBall) {
		final var problemBall = generator.get();

		baseBallResult = problemBall.compare(answerBall);
		if (baseBallResult == BaseBallResult.THREE_STRIKE) {
			setCompleted(true);
			generator.clear();
		}
	}

}

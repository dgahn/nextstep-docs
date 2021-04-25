package me.dgahn.baseball.view;

import static java.lang.System.*;

import me.dgahn.baseball.domain.BaseBall;
import me.dgahn.baseball.domain.BaseBallResult;
import me.dgahn.baseball.repo.RandomNumberGenerator;

public class BaseBallViewModel {

	private final RandomNumberGenerator generator;
	private boolean completed = false;
	private BaseBallResult baseBallResult;

	public BaseBallViewModel(final RandomNumberGenerator generator) {
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
		final var problemBall = generator.getBaseBall();

		baseBallResult = problemBall.prepare(answerBall);
		if(baseBallResult == BaseBallResult.THREE_STRIKE) {
			completed = true;
		}
	}

	private void newProblemBall() {
		final var newBaseBall = new BaseBall();
		out.println(newBaseBall.getValue());
		generator.setBaseBall(newBaseBall);
	}

}

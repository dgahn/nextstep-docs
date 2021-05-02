package me.dgahn.baseball.view;

import static me.dgahn.baseball.util.Constants.*;

import me.dgahn.baseball.domain.BaseBall;
import me.dgahn.baseball.domain.BaseBallResult;
import me.dgahn.baseball.domain.RandomBaseBallGenerator;
import me.dgahn.baseball.util.IntUtil;
import me.dgahn.baseball.util.StringUtil;

public class BaseBallViewModel {

	private final RandomBaseBallGenerator generator;
	private boolean continued = false;
	private boolean completed = false;
	private BaseBallResult baseBallResult;
	private String output;

	public BaseBallViewModel(final RandomBaseBallGenerator generator) {
		this.generator = generator;
		output = "숫자를 입력해주세요. (100 ~ 999 중 3개의 자리 수가 중복되지 않는 수) : ";
	}

	public boolean isCompleted() {
		return completed;
	}

	public boolean isContinued() {
		return continued;
	}

	public BaseBallResult getBaseBallResult() {
		return baseBallResult;
	}

	public String getOutput() {
		return output;
	}

	public void setCompleted(final boolean completed) {
		if (!completed) {
			output = "숫자를 입력해주세요. (100 ~ 999 중 3개의 자리 수가 중복되지 않는 수) : ";
		}
		this.completed = completed;
	}

	public void setContinued(final String value) {
		var intValue = StringUtil.toInt(value);
		if (!IntUtil.isBetween(intValue, GAME_CONTINUE, GAME_THE_END)) {
			throw new IllegalArgumentException("숫자는 1 또는 2입니다. (number : " + value + ")");
		}

		this.continued = intValue == GAME_CONTINUE;
		this.setCompleted(intValue != GAME_CONTINUE);
	}

	public void process(final BaseBall answerBall) {
		final var problemBall = generator.get();

		baseBallResult = problemBall.compare(answerBall);
		if (baseBallResult == BaseBallResult.THREE_STRIKE) {
			setCompleted(true);
			generator.clear();
			output = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요. : ";
		}
	}
}

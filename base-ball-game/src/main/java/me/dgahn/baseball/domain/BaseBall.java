package me.dgahn.baseball.domain;

import static me.dgahn.baseball.util.Constants.*;

import java.util.Random;

public class BaseBall {
	// ToDo 3개의 자리수가 모두 달라야 함으로 다르게 받는 로직 구성 필요.
	private final String value;

	public BaseBall() {
		final var random = new Random();
		this.value = Integer.toString(getRandomValue(random));
	}

	public BaseBall(final int value) {
		if (!checkValidValue(value)) {
			throw new IllegalArgumentException("숫자는 100이상 999이하여야 합니다. (number : " + value + ")");
		}
		this.value = Integer.toString(value);
	}

	public String getValue() {
		return value;
	}

	public BaseBallResult prepare(final BaseBall baseBall) {
		final var strikeNumber = checkStrike(baseBall);
		final var ballNumber = checkBall(baseBall);

		if(strikeNumber != 0 && ballNumber == 0) return prepareStrike(strikeNumber);
		if(strikeNumber == 0 && ballNumber != 0) return prepareBall(ballNumber);

		return prepareNoStrike(strikeNumber, ballNumber);
	}

	private int checkStrike(final BaseBall baseBall) {
		var count = 0;
		for (var i = 0; i < value.length(); i++) {
			if (this.value.charAt(i) == baseBall.value.charAt(i)) {
				count++;
			}
		}
		return count;
	}

	private int checkBall(final BaseBall baseBall) {
		var count = 0;
		for (var i = 0; i < value.length(); i++) {
			final var stringBuilder = new StringBuilder(baseBall.value);
			final var subValue = stringBuilder.deleteCharAt(i).toString();
			// ToDo indexOf를 통해 포함이 되는지 확인하는 함수를 별도로 빼서 가독성 올릴 필요성이 있음.
			if(subValue.indexOf(this.value.charAt(i)) > -1) {
				count++;
			}
		}
		return count;
	}

	private BaseBallResult prepareStrike(final int strikeNumber) {
		if (strikeNumber == BaseBallResult.THREE_STRIKE.getStrikeNumber()) return BaseBallResult.THREE_STRIKE;
		if (strikeNumber == BaseBallResult.TWO_STRIKE.getStrikeNumber()) return BaseBallResult.TWO_STRIKE;

		return BaseBallResult.ONE_STRIKE;
	}

	private BaseBallResult prepareBall(final int ballNumber) {
		if (ballNumber == BaseBallResult.TWO_BALL.getBallNumber()) return BaseBallResult.TWO_BALL;

		return BaseBallResult.ONE_BALL;
	}

	private BaseBallResult prepareNoStrike(final int strikeNumber, final int ballNumber) {
		if (strikeNumber == BaseBallResult.ONE_STRIKE_ONE_BALL.getStrikeNumber() && ballNumber == BaseBallResult.ONE_STRIKE_ONE_BALL
			.getBallNumber()) return BaseBallResult.ONE_STRIKE_ONE_BALL;
		if (strikeNumber == BaseBallResult.ONE_STRIKE_TWO_BALL.getStrikeNumber() && ballNumber == BaseBallResult.ONE_STRIKE_TWO_BALL
			.getBallNumber()) return BaseBallResult.ONE_STRIKE_TWO_BALL;

		return BaseBallResult.NOTHING;
	}

	private boolean checkValidValue(final int value) {
		return value >= BASE_BALL_MIN_NUMBER && value <= BASE_BALL_MAX_NUMBER;
	}

	private int getRandomValue(final Random random) {
		while (true) {
			final var randomValue = random.nextInt(BASE_BALL_NUMBER_OF_DIGITS);
			if (randomValue >= BASE_BALL_MIN_NUMBER) {
				return randomValue;
			}
		}
	}
}

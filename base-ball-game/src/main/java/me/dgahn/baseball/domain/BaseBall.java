package me.dgahn.baseball.domain;

import static me.dgahn.baseball.util.Constants.*;

import java.util.Random;

public class BaseBall {
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
		var sameNumberOfDigits = checkEachNumberOfDigits(baseBall);
		if (sameNumberOfDigits == BaseBallResult.THREE_STRIKE.getStrikeNumber()) return BaseBallResult.THREE_STRIKE;
		if (sameNumberOfDigits == BaseBallResult.TWO_STRIKE.getStrikeNumber()) return BaseBallResult.TWO_STRIKE;
		if (sameNumberOfDigits == BaseBallResult.ONE_STRIKE.getStrikeNumber()) return BaseBallResult.ONE_STRIKE;

		return BaseBallResult.NOTHING;
	}

	private int checkEachNumberOfDigits(final BaseBall baseBall) {
		var count = 0;
		for (var i = 0; i < value.length(); i++) {
			if (this.value.charAt(i) == baseBall.value.charAt(i)) {
				count++;
			}
		}
		return count;
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

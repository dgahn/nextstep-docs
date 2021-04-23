package me.dgahn.baseball.domain;

import static me.dgahn.baseball.util.Constants.*;

import java.util.Random;

public class BaseBall {
	private final int value;

	public BaseBall() {
		final var random = new Random();
		this.value = getRandomValue(random);
	}

	public BaseBall(final int value) {
		if (!checkValidValue(value)) {
			throw new IllegalArgumentException("숫자는 100이상 999이하여야 합니다. (number : " + value + ")");
		}
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public BaseBallResult prepare(final BaseBall baseBall) {
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

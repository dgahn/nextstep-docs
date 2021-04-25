package me.dgahn.baseball.domain;

import static me.dgahn.baseball.util.Constants.*;

import java.util.Random;

public class RandomBaseBallGenerator implements RandomGenerator<BaseBall> {

	private final Random random;
	private BaseBall baseBall;

	public RandomBaseBallGenerator(final Random random) {
		this.random = random;
	}

	@Override
	public void generate() {
		try {
			baseBall = new BaseBall(getRandomValue());
		} catch (IllegalArgumentException e) {
			generate();
		}
	}

	@Override
	public BaseBall get() {
		if (baseBall == null) {
			generate();
		}

		return baseBall;
	}

	@Override
	public void clear() {
		baseBall = null;
	}

	private int getRandomValue() {
		return random.nextInt(BASE_BALL_NUMBER_OF_DIGITS);
	}
}

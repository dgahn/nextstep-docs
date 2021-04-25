package me.dgahn.baseball.domain;

import static me.dgahn.baseball.util.Constants.*;

import java.util.Random;

import me.dgahn.baseball.util.StringUtil;

// ToDo 코딩 컨벤션으로 조건 / 반복문에 중괄호 필수 사용 적용
public class BaseBall {
	private final String value;

	public BaseBall() {
		final var random = new Random();
		this.value = Integer.toString(getRandomValue(random));
	}

	public BaseBall(final int value) {
		if (!checkNumberRange(value)) {
			throw new IllegalArgumentException("숫자는 100이상 999이하여야 합니다. (number : " + value + ")");
		}
		final var stringValue = Integer.toString(value);
		if (!checkDuplicateNumber(stringValue)) {
			throw new IllegalArgumentException("숫자가 중복되면 안됩니다. (number : " + value + ")");
		}
		this.value = stringValue;
	}

	public String getValue() {
		return value;
	}

	public BaseBallResult prepare(final BaseBall baseBall) {
		final var strikeNumber = checkStrike(baseBall);
		final var ballNumber = checkBall(baseBall);

		if (strikeNumber != 0 && ballNumber == 0)
			return prepareStrike(strikeNumber);
		if (strikeNumber == 0 && ballNumber != 0)
			return prepareBall(ballNumber);

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
			if (StringUtil.contains(subValue, this.value.charAt(i))) {
				count++;
			}
		}
		return count;
	}

	private BaseBallResult prepareStrike(final int strikeNumber) {
		if (strikeNumber == BaseBallResult.THREE_STRIKE.getStrikeNumber())
			return BaseBallResult.THREE_STRIKE;
		if (strikeNumber == BaseBallResult.TWO_STRIKE.getStrikeNumber())
			return BaseBallResult.TWO_STRIKE;

		return BaseBallResult.ONE_STRIKE;
	}

	private BaseBallResult prepareBall(final int ballNumber) {
		if (ballNumber == BaseBallResult.TWO_BALL.getBallNumber())
			return BaseBallResult.TWO_BALL;

		return BaseBallResult.ONE_BALL;
	}

	private BaseBallResult prepareNoStrike(final int strikeNumber, final int ballNumber) {
		if (strikeNumber == BaseBallResult.ONE_STRIKE_ONE_BALL.getStrikeNumber()
			&& ballNumber == BaseBallResult.ONE_STRIKE_ONE_BALL
			.getBallNumber())
			return BaseBallResult.ONE_STRIKE_ONE_BALL;
		if (strikeNumber == BaseBallResult.ONE_STRIKE_TWO_BALL.getStrikeNumber()
			&& ballNumber == BaseBallResult.ONE_STRIKE_TWO_BALL
			.getBallNumber())
			return BaseBallResult.ONE_STRIKE_TWO_BALL;

		return BaseBallResult.NOTHING;
	}

	private boolean checkNumberRange(final int value) {
		return value >= BASE_BALL_MIN_NUMBER && value <= BASE_BALL_MAX_NUMBER;
	}

	private boolean checkDuplicateNumber(final String stringValue) {
		for (var i = 0; i < stringValue.length(); i++) {
			final var stringBuilder = new StringBuilder(stringValue);
			final var subValue = stringBuilder.deleteCharAt(i).toString();
			if (StringUtil.contains(subValue, stringValue.charAt(i))) {
				return false;
			}
		}
		return true;
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

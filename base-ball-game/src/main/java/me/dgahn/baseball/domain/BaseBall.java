package me.dgahn.baseball.domain;

import static me.dgahn.baseball.util.Constants.*;

import me.dgahn.baseball.util.IntUtil;
import me.dgahn.baseball.util.StringUtil;

public class BaseBall {

	private final String value;

	public BaseBall(final String value) {
		checkNumberRange(value);
		checkDuplicateNumber(value);
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public BaseBallResult compare(final BaseBall baseBall) {
		final var strikeNumber = checkStrike(baseBall);
		final var ballNumber = checkBall(baseBall);
		if (strikeNumber == 0 && ballNumber == 0) {
			return BaseBallResult.NOTHING;
		}
		if (strikeNumber == 0 || ballNumber == 0) {
			return compareOnlyStrikeOrBall(strikeNumber, ballNumber);
		}

		return compareNotOnlyStrikeOrBall(strikeNumber, ballNumber);
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

	private BaseBallResult compareOnlyStrikeOrBall(
		final int strikeNumber,
		final int ballNumber
	) {
		if (strikeNumber != 0 && ballNumber == 0) {
			return compareOnlyStrike(strikeNumber);
		}

		return compareOnlyBall(ballNumber);
	}

	private BaseBallResult compareOnlyStrike(final int strikeNumber) {
		if (strikeNumber == BaseBallResult.THREE_STRIKE.getStrikeNumber()) {
			return BaseBallResult.THREE_STRIKE;
		}
		if (strikeNumber == BaseBallResult.TWO_STRIKE.getStrikeNumber()) {
			return BaseBallResult.TWO_STRIKE;
		}

		return BaseBallResult.ONE_STRIKE;
	}

	private BaseBallResult compareOnlyBall(final int ballNumber) {
		if (ballNumber == BaseBallResult.TWO_BALL.getBallNumber()) {
			return BaseBallResult.TWO_BALL;
		}

		return BaseBallResult.ONE_BALL;
	}

	private BaseBallResult compareNotOnlyStrikeOrBall(final int strikeNumber, final int ballNumber) {
		if (strikeNumber == BaseBallResult.ONE_STRIKE_ONE_BALL.getStrikeNumber()
			&& ballNumber == BaseBallResult.ONE_STRIKE_ONE_BALL
			.getBallNumber()) {
			return BaseBallResult.ONE_STRIKE_ONE_BALL;
		}

		return BaseBallResult.ONE_STRIKE_TWO_BALL;
	}

	private void checkNumberRange(final String value) {
		final var intValue = StringUtil.toInt(value);
		if(!IntUtil.isBetween(intValue, BASE_BALL_MIN_NUMBER, BASE_BALL_MAX_NUMBER)) {
			throw new IllegalArgumentException("숫자는 100이상 999이하여야 합니다. (number : " + value + ")");
		}
	}

	private void checkDuplicateNumber(final String stringValue) {
		for (var i = 0; i < stringValue.length(); i++) {
			final var stringBuilder = new StringBuilder(stringValue);
			final var subValue = stringBuilder.deleteCharAt(i).toString();
			if (StringUtil.contains(subValue, stringValue.charAt(i))) {
				throw new IllegalArgumentException("숫자가 중복되면 안됩니다. (number : " + stringValue + ")");
			}
		}
	}
}

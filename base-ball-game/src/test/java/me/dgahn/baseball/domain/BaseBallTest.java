package me.dgahn.baseball.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BaseBallTest {

	@ParameterizedTest
	@DisplayName("100에서 999이하의 숫자로 BaseBall을 만들 수 있다.")
	@ValueSource(ints = {123, 150, 213, 987})
	void makePossibleBaseBallTest(final int input) {
		final var baseBall = new BaseBall(input);
		assertThat(baseBall.getValue()).isEqualTo(Integer.toString(input));
	}

	@ParameterizedTest
	@DisplayName("100미만이거나 999초과의 숫자로 BaseBall을 만들면 IllegalArgumentException이 발생한다.")
	@ValueSource(ints = {-1, 99, 1000})
	void makeInvalidNumberRangeBaseBallTest(final int input) {
		assertThatThrownBy(() -> new BaseBall(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("숫자는 100이상 999이하여야 합니다. (number : " + input + ")");
	}

	@ParameterizedTest
	@DisplayName("100미만이거나 999초과의 숫자로 BaseBall을 만들면 IllegalArgumentException이 발생한다.")
	@ValueSource(ints = {111, 112, 121, 211})
	void makeInvalidDuplicateNumberBaseBallTest(final int input) {
		assertThatThrownBy(() -> new BaseBall(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("숫자가 중복되면 안됩니다. (number : " + input + ")");
	}

	@Test
	@DisplayName("기본 생성자로 3자리 숫자의 value를 갖는 BaseBall을 만들 수 있다.")
	void defaultConstructBaseBallTest() {
		for (int i = 0; i < 10000; i++) {
			final BaseBall baseBall = new BaseBall();
			assertThat(Integer.parseInt(baseBall.getValue())).isBetween(100, 999);
		}
	}

	@ParameterizedTest
	@DisplayName("같은 숫자, 같은 자리수가 없으면 NOTHING을 반환한다.")
	@CsvSource(value = {"123:456", "321:456", "987:612"}, delimiter = ':')
	void nothingCompareTest(final String problemString, final String answerString) {
		final var problemBall = new BaseBall(Integer.parseInt(problemString));
		final var answerBall = new BaseBall(Integer.parseInt(answerString));
		assertThat(problemBall.compare(answerBall)).isEqualTo(BaseBallResult.NOTHING);
	}

	@ParameterizedTest
	@DisplayName("3개의 자리수가 모두 같으면 THREE_STRIKE을 반환한다.")
	@CsvSource(value = {"123:123", "345:345", "912:912"}, delimiter = ':')
	void threeStrikeCompareTest(final String problemString, final String answerString) {
		final var problemBall = new BaseBall(Integer.parseInt(problemString));
		final var answerBall = new BaseBall(Integer.parseInt(answerString));
		assertThat(problemBall.compare(answerBall)).isEqualTo(BaseBallResult.THREE_STRIKE);
	}

	@ParameterizedTest
	@DisplayName("2개의 자리수가 모두 같으면 TWO_STRIKE을 반환한다.")
	@CsvSource(value = {"123:124", "123:143", "123:423"}, delimiter = ':')
	void twoStrikeCompareTest(final String problemString, final String answerString) {
		final var problemBall = new BaseBall(Integer.parseInt(problemString));
		final var answerBall = new BaseBall(Integer.parseInt(answerString));
		assertThat(problemBall.compare(answerBall)).isEqualTo(BaseBallResult.TWO_STRIKE);
	}

	@ParameterizedTest
	@DisplayName("1개의 자리수가 모두 같으면 ONE_STRIKE을 반환한다.")
	@CsvSource(value = {"123:145", "123:425", "123:453"}, delimiter = ':')
	void oneStrikeCompareTest(final String problemString, final String answerString) {
		final var problemBall = new BaseBall(Integer.parseInt(problemString));
		final var answerBall = new BaseBall(Integer.parseInt(answerString));
		assertThat(problemBall.compare(answerBall)).isEqualTo(BaseBallResult.ONE_STRIKE);
	}

	@ParameterizedTest
	@DisplayName("2개의 자리수가 모두 다르지만 같은 2개의 수가 존재하면 TWO_BALL을 반환한다.")
	@CsvSource(value = {"123:214", "143:321", "123:432"}, delimiter = ':')
	void threeBallCompareTest(final String problemString, final String answerString) {
		final var problemBall = new BaseBall(Integer.parseInt(problemString));
		final var answerBall = new BaseBall(Integer.parseInt(answerString));
		assertThat(problemBall.compare(answerBall)).isEqualTo(BaseBallResult.TWO_BALL);
	}

	@ParameterizedTest
	@DisplayName("1개의 자리수가 다르지만 같은 1개의 수가 존재하면 ONE_BALL을 반환한다.")
	@CsvSource(value = {"123:415", "123:451", "213:451"}, delimiter = ':')
	void oneBallCompareTest(final String problemString, final String answerString) {
		final var problemBall = new BaseBall(Integer.parseInt(problemString));
		final var answerBall = new BaseBall(Integer.parseInt(answerString));
		assertThat(problemBall.compare(answerBall)).isEqualTo(BaseBallResult.ONE_BALL);
	}

	@ParameterizedTest
	@DisplayName("1개의 자리수가 같고 숫자가 1개의 숫자가 같으면 ONE_STRIKE_ONE_BALL을 반환한다.")
	@CsvSource(value = {"123:142", "321:124", "123:413"}, delimiter = ':')
	void oneStrikeOneBallCompareTest(final String problemString, final String answerString) {
		final var problemBall = new BaseBall(Integer.parseInt(problemString));
		final var answerBall = new BaseBall(Integer.parseInt(answerString));
		assertThat(problemBall.compare(answerBall)).isEqualTo(BaseBallResult.ONE_STRIKE_ONE_BALL);
	}

	@ParameterizedTest
	@DisplayName("1개의 자리수가 같고 숫자가 2개의 숫자가 같으면 ONE_STRIKE_TWO_BALL을 반환한다.")
	@CsvSource(value = {"123:132", "123:321", "123:213"}, delimiter = ':')
	void oneStrikeTwoBallCompareTest(final String problemString, final String answerString) {
		final var problemBall = new BaseBall(Integer.parseInt(problemString));
		final var answerBall = new BaseBall(Integer.parseInt(answerString));
		assertThat(problemBall.compare(answerBall)).isEqualTo(BaseBallResult.ONE_STRIKE_TWO_BALL);
	}

}

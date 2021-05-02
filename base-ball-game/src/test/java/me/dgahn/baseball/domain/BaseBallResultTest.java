package me.dgahn.baseball.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BaseBallResultTest {

	@Test
	@DisplayName("getResultMessage를 통해 결과물에 대한 출력 메시지를 확인할 수 있다.")
	void getMessageTest() {
		final var baseBallResult = BaseBallResult.ONE_STRIKE_ONE_BALL;
		final var expected = "1스트라이크, 1볼";
		final var actual = baseBallResult.getResultMessage();

		assertThat(actual).isEqualTo(expected);
	}

}

package me.dgahn.baseball.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BaseBallTest {

	@ParameterizedTest
	@DisplayName("100에서 999이하의 숫자로 BaseBall을 만들 수 있다.")
	@ValueSource(ints = {100, 150, 200, 999})
	void makePossibleBaseBallTest(final int input) {
		final var baseBall = new BaseBall(input);
		assertThat(baseBall.getValue()).isEqualTo(input);
	}

	@ParameterizedTest
	@DisplayName("100미만이거나 999초과의 숫자로 BaseBall을 만들면 IllegalArgumentException이 발생한다.")
	@ValueSource(ints = {-1, 99, 1000})
	void makeImpossibleBaseBallTest(final int input) {
		assertThatThrownBy(() -> new BaseBall(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("숫자는 100이상 999이하여야 합니다. (number : " + input + ")");
		;
	}

	@Test
	@DisplayName("기본 생성자로 3자리 숫자의 value를 갖는 BaseBall을 만들 수 있다.")
	void defaultConstructBaseBallTest() {
		for (int i = 0; i < 10000; i++) {
			BaseBall baseBall = new BaseBall();
			assertThat(baseBall.getValue()).isBetween(100, 999);
		}
	}

}

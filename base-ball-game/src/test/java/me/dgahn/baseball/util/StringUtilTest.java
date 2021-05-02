package me.dgahn.baseball.util;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringUtilTest {

	@Test
	@DisplayName("유리수 문자열을 정수로 바꾸려고 하면 IllegalArgumentException예외가 발생한다.")
	void toIntTest() {
		final var str = "412.333";

		assertThatThrownBy(() -> StringUtil.toInt(str))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("정수를 입력해야합니다.");
	}

}

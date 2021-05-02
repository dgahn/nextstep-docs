package me.dgahn.baseball.util;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IntUtilTest {

	@Test
	@DisplayName("특정 숫자가 범위에 들어가는지 확인할 수 있다.")
	void isBetweenTest() {
		final boolean isBetween1 = IntUtil.isBetween(3, 1, 10);
		assertThat(isBetween1).isTrue();

		final boolean isBetween2 = IntUtil.isBetween(3, 5, 10);
		assertThat(isBetween2).isFalse();
	}

}

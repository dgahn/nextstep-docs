package me.dgahn.baseball.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomBaseBallGeneratorTest {

	@Test
	@DisplayName("BaseBall을 생성하면 100 ~ 999 사이의 값만 생성된다.")
	void whenCreateBaseballOnlyValuesBetween100And999AreGenerated() {
		final var random = new Random();
		final var generator = new RandomBaseBallGenerator(random);
		for (int i = 0; i < 1000; i++) {
			generator.generate();
			assertThat(Integer.parseInt(generator.get().getValue())).isBetween(100, 999);
			generator.clear();
		}
	}

	@Test
	@DisplayName("BaseBall을 가져오는데 BaseBall이 null이면 BaseBall을 생성한다.")
	void GetsBaseBallAndIfBaseBallIsNullItCreatesBaseBall() {
		final var random = new Random();
		final var generator = new RandomBaseBallGenerator(random);

		assertThat(generator.get()).isNotNull();
	}

}

package me.dgahn.baseball.view;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Random;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import me.dgahn.baseball.domain.BaseBall;
import me.dgahn.baseball.domain.BaseBallResult;
import me.dgahn.baseball.domain.RandomBaseBallGenerator;

class BaseBallViewModelTest {

	@Test
	@DisplayName("게임이 처음 시작하면 완료 상태는 false다.")
	void whenTheGameFirstStartsThenTheCompletionStatusIsFalse() {
		final var random = new Random();
		final var generator = new RandomBaseBallGenerator(random);
		final var viewModel = new BaseBallViewModel(generator);

		assertThat(viewModel.isCompleted()).isFalse();
	}

	@Test
	@DisplayName("숫자를 맞추면 완료 상태는 true고 BaseBallResult는 THREE_STRIKE다.")
	void whenMatchTheNumberThenTheCompletionStatusIsTrueAndBaseBallResultIsThreeStrike() {
		// given
		final var generator = mock(RandomBaseBallGenerator.class);
		final var baseBall = new BaseBall(123);
		when(generator.get()).thenReturn(baseBall);
		final var viewModel = new BaseBallViewModel(generator);
		viewModel.process(baseBall);

		// then
		assertThat(viewModel.isCompleted()).isTrue();
		assertThat(viewModel.getBaseBallResult()).isEqualTo(BaseBallResult.THREE_STRIKE);
	}
}

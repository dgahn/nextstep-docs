package me.dgahn.baseball.view;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import me.dgahn.baseball.domain.BaseBall;
import me.dgahn.baseball.repo.RandomNumberGenerator;

class BaseBallViewModelTest {

	@Test
	@DisplayName("게임이 처음 시작하면 완료 상태는 false다.")
	void whenTheGameFirstStartsThenTheCompletionStatusIsFalse() {
		final var viewModel = new BaseBallViewModel(new RandomNumberGenerator());

		assertThat(viewModel.isCompleted()).isFalse();
	}

	@Test
	@DisplayName("숫자를 맞추면 완료 상태는 true다.")
	void whenMatchTheNumberThenTheCompletionStatusIsTrue() {
		// given
		final var generator = mock(RandomNumberGenerator.class);
		final var baseBall = new BaseBall(123);
		when(generator.getBaseBall()).thenReturn(baseBall);
		final var viewModel = new BaseBallViewModel(generator);
		viewModel.process(baseBall);

		// then
		assertThat(viewModel.isCompleted()).isTrue();
	}
}

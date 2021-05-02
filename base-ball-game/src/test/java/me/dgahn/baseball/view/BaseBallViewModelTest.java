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
	@DisplayName("게임이 처음 시작하면 output은 숫자를 입력하라는 Message다.")
	void whenTheGameFirstStartsThenTheMessageIsInputMessage() {
		final var random = new Random();
		final var generator = new RandomBaseBallGenerator(random);
		final var viewModel = new BaseBallViewModel(generator);

		assertThat(viewModel.getOutput()).isEqualTo("숫자를 입력해주세요. (100 ~ 999 중 3개의 자리 수가 중복되지 않는 수) : ");
	}

	@Test
	@DisplayName("게임이 완료되면 output은 게임 새 시작 / 종료를 선택하는 Message다.")
	void whenTheGameFirstStartsThenTheMessageIsReGameMessage() {
		final var generator = mock(RandomBaseBallGenerator.class);
		final var baseBall = new BaseBall(Integer.toString(123));
		when(generator.get()).thenReturn(baseBall);
		final var viewModel = new BaseBallViewModel(generator);
		viewModel.process(baseBall);

		assertThat(viewModel.getOutput()).isEqualTo("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요. : ");
	}

	@Test
	@DisplayName("숫자를 맞추면 완료 상태는 true고 BaseBallResult는 THREE_STRIKE다.")
	void whenMatchTheNumberThenTheCompletionStatusIsTrueAndBaseBallResultIsThreeStrike() {
		// given
		final var generator = mock(RandomBaseBallGenerator.class);
		final var baseBall = new BaseBall(Integer.toString(123));
		when(generator.get()).thenReturn(baseBall);
		final var viewModel = new BaseBallViewModel(generator);
		viewModel.process(baseBall);

		// then
		assertThat(viewModel.isCompleted()).isTrue();
		assertThat(viewModel.getBaseBallResult()).isEqualTo(BaseBallResult.THREE_STRIKE);
	}

	@Test
	@DisplayName("재 게임을 위해서 완료 상태를 false로 바꾸면 output message는 숫자 입력 message가 된다.")
	void whenCompletionStatusIsChangedToFalseThenOutputIsNumberInputMessage() {
		final var random = new Random();
		final var generator = new RandomBaseBallGenerator(random);
		final var viewModel = new BaseBallViewModel(generator);

		viewModel.setCompleted(false);
		assertThat(viewModel.getOutput()).isEqualTo("숫자를 입력해주세요. (100 ~ 999 중 3개의 자리 수가 중복되지 않는 수) : ");
	}

	@Test
	@DisplayName("continued의 값을 \"1\"로 설정하면 continued는 true가 되고 completed는 false가 된다.")
	void whenContinuedWriteOneThenContinuedIsTrueAndCompletedIsFalse() {
		final var random = new Random();
		final var generator = new RandomBaseBallGenerator(random);
		final var viewModel = new BaseBallViewModel(generator);

		viewModel.setContinued("1");
		assertThat(viewModel.isContinued()).isTrue();
		assertThat(viewModel.isCompleted()).isFalse();
	}

	@Test
	@DisplayName("continued의 값을 \"1\" 또는 \"2\"로 설정하지 않으면 IllegalArgumentException()이 발생한다.")
	void whenContinuedWriteNumberOtherThanOneAndTwoThenThrownTheIllegalArgumentException() {
		final var random = new Random();
		final var generator = new RandomBaseBallGenerator(random);
		final var viewModel = new BaseBallViewModel(generator);

		final var value = "3";
		assertThatThrownBy(() -> viewModel.setContinued(value))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("숫자는 1 또는 2입니다. (number : " + value + ")");
	}
}

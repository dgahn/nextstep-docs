package me.dgahn.baseball.view;

import static java.lang.System.*;

import java.util.InputMismatchException;
import java.util.Scanner;

import me.dgahn.baseball.domain.BaseBall;
import me.dgahn.baseball.domain.BaseBallResult;

public class BaseBallConsoleView {

	private final Scanner scanner;

	public BaseBallConsoleView(final Scanner scanner) {
		this.scanner = scanner;
	}

	public BaseBall input() {
		try {
			out.println("숫자를 입력해주세요(100 ~ 999 중 3개의 자리 수가 중복되지 않는 수) : ");
			return new BaseBall(scanner.nextInt());
		} catch (InputMismatchException e) {
			err.println("숫자를 입력해야합니다.(100 ~ 999)");
			return input();
		} catch (IllegalArgumentException e) {
			err.println(e.getMessage());
			return input();
		}
	}

	public boolean retry() {
		try {
			out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요. : ");
			final var num = scanner.nextInt();
			if (num <= 0 || num > 2) {
				throw new IllegalArgumentException("1 또는 2를 입력해야합니다.");
			}
			return num == 1;
		} catch (InputMismatchException e) {
			err.println("숫자를 입력해야합니다.(1 ~ 2)");
			return retry();
		} catch (IllegalArgumentException e) {
			err.println(e.getMessage());
			return retry();
		}
	}

	public void result(final BaseBallResult baseBallResult) {
		out.println(baseBallResult.getResultMessage());
	}

}

package me.dgahn.baseball.view;

import static java.lang.System.*;

import java.util.InputMismatchException;
import java.util.Scanner;

import me.dgahn.baseball.domain.BaseBall;

public class BaseBallConsoleView {

	public BaseBall getBaseball() {
		try {
			out.println("숫자를 입력해주세요(100 ~ 999) : ");
			final var sc = new Scanner(in);
			return new BaseBall(sc.nextInt());
		} catch (InputMismatchException e) {
			err.println("숫자를 입력해야합니다.(100 ~ 999)");
			return getBaseball();
		} catch (IllegalArgumentException e) {
			err.println(e.getMessage());
			return getBaseball();
		}
	}

}

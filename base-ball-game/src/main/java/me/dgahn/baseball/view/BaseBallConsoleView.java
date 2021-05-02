package me.dgahn.baseball.view;

import static java.lang.System.*;

import java.util.Scanner;

public class BaseBallConsoleView {

	private final Scanner scanner;

	public BaseBallConsoleView(final Scanner scanner) {
		this.scanner = scanner;
	}

	public String input(final String message) {
		info(message);
		return scanner.nextLine();
	}

	public void info(final String message) {
		out.println(message);
	}

	public void error(final String message) {
		err.println(message);
	}

}

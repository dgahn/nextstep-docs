package me.dgahn.baseball;

import me.dgahn.baseball.view.BaseBallConsoleView;

public class Launcher {
	public static void main(String[] args) {
		final var view = new BaseBallConsoleView();
		view.getBaseball();
	}
}

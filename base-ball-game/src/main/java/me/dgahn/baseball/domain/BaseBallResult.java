package me.dgahn.baseball.domain;

public enum BaseBallResult {
	NOTHING(0, 0, "낫싱"),
	THREE_STRIKE(3, 0, "3 스트라이크"),
	TWO_STRIKE(2, 0, "2 스트라이크"),
	ONE_STRIKE(1, 0, "1 스트라이크");

	private final int strikeNumber;
	private final int ballNumber;
	private final String resultMessage;

	BaseBallResult(int strikeNumber, int ballNumber, String resultMessage) {
		this.strikeNumber = strikeNumber;
		this.ballNumber = ballNumber;
		this.resultMessage = resultMessage;
	}

	public int getStrikeNumber() {
		return strikeNumber;
	}

	public int getBallNumber() {
		return ballNumber;
	}

	public String getResultMessage() {
		return resultMessage;
	}
}

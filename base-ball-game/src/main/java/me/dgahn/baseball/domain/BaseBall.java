package me.dgahn.baseball.domain;

public class BaseBall {

	private int value;

	private BaseBall() {}

	public BaseBall(int value) {
		if(!checkValidValue(value)) {
			throw new IllegalArgumentException("숫자는 100이상 999이하여야 합니다. (number : " + value +")");
		}
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	private boolean checkValidValue(int value) {
		return value >= 100 && value <= 999;
	}
}

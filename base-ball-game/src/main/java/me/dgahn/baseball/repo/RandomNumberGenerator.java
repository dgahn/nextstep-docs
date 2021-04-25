package me.dgahn.baseball.repo;

import java.util.Optional;

import me.dgahn.baseball.domain.BaseBall;

public class RandomNumberGenerator {

	private BaseBall baseBall;

	public Optional<BaseBall> getBaseBall() {
		return Optional.ofNullable(baseBall);
	}

	public void setBaseBall(BaseBall baseBall) {
		this.baseBall = baseBall;
	}
}

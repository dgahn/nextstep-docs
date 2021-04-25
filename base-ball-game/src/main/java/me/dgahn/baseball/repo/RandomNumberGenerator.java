package me.dgahn.baseball.repo;

import me.dgahn.baseball.domain.BaseBall;

public class RandomNumberGenerator {

	private BaseBall baseBall;

	public BaseBall getBaseBall() {
		if(baseBall == null) {
			setBaseBall(new BaseBall());
		}
		return baseBall;
	}

	public void setBaseBall(BaseBall baseBall) {
		this.baseBall = baseBall;
	}
}

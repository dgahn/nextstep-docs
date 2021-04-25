package me.dgahn.baseball.repo;

import me.dgahn.baseball.domain.BaseBall;

// ToDo 정수를 생성하는게 아니라 베이스 볼을 만들고 있으므로 분할할 필요성이 있음.
// ToDo 패키지명 변경 필요.
// ToDo 인터페이스화 필요.
public class RandomNumberGenerator {

	private BaseBall baseBall;

	public BaseBall getBaseBall() {
		if (baseBall == null) {
			setBaseBall(new BaseBall());
		}
		return baseBall;
	}

	public void setBaseBall(BaseBall baseBall) {
		this.baseBall = baseBall;
	}
}

package me.dgahn.baseball.util;

// ToDo 테스트 커버리지 채울 필요 있음.
public class IntUtil {

	public static boolean isBetween(
		final int source,
		final int min,
		final int max
	) {
		return source >= min && source <= max;
	}

}

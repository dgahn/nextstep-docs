package me.dgahn.baseball.util;

public class IntUtil {

	private IntUtil() {}

	public static boolean isBetween(
		final int source,
		final int min,
		final int max
	) {
		return source >= min && source <= max;
	}

}

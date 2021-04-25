package me.dgahn.baseball.util;

// ToDo 테스트 커버리지 채울 필요 있음.
public class StringUtil {

	public static boolean contains(final String str, final char ch) {
		return str.indexOf(ch) > -1;
	}

	public static int toInt(final String str) {
		try {
			return Integer.parseInt(str);
		} catch (final NumberFormatException e) {
			throw new IllegalArgumentException("정수를 입력해야합니다.");
		}
	}
}

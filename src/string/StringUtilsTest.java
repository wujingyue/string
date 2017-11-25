package string;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringUtilsTest {

	void verifyAllVersionsOfFind(int expected, String text, String pattern) {
		assertEquals(expected, StringUtils.find(text, pattern));
		assertEquals(expected, StringUtils.findUsingRabinKarp(text, pattern));
		assertEquals(expected, StringUtils.findUsingKMP(text, pattern));
	}

	@Test
	void testFind() {
		verifyAllVersionsOfFind(1, "abcd", "bcd");
		verifyAllVersionsOfFind(-1, "abcd", "bce");
		verifyAllVersionsOfFind(1, "aabc", "abc");
		verifyAllVersionsOfFind(0, "aaaa", "a");
		verifyAllVersionsOfFind(-1, "aaaa", "aaaaa");
		verifyAllVersionsOfFind(0, "", "");
		verifyAllVersionsOfFind(0, "a", "");
		verifyAllVersionsOfFind(22, "asdassdsdasdasdasdasdasddsasdsdsdaadsdsd", "sdd");
		verifyAllVersionsOfFind(13, "baaabbbbaaabbababacabbbbaaabbbbbba", "ababaca");
	}

	@Test
	void testString() {
		String str = "ab\u1001";
		assertEquals(3, str.length());
		assertEquals(5, str.getBytes().length);
	}

	@Test
	void testChar() {
		char ch = (char) -32768;
		assertEquals(32768, (int) ch);
	}
}

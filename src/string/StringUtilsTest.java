package string;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringUtilsTest {

	@Test
	void testFind() {
		assertEquals(1, StringUtils.find("abcd", "bcd"));
		assertEquals(-1, StringUtils.find("abcd", "bce"));
		assertEquals(1, StringUtils.find("aabc", "abc"));
		assertEquals(0, StringUtils.find("aaaa", "a"));
		assertEquals(-1, StringUtils.find("aaaa", "aaaaa"));
		assertEquals(0, StringUtils.find("", ""));
		assertEquals(0, StringUtils.find("a", ""));
	}

	@Test
	void testRabinKarp() {
		assertEquals(1, StringUtils.findUsingRabinKarp("abcd", "bcd"));
		assertEquals(-1, StringUtils.findUsingRabinKarp("abcd", "bce"));
		assertEquals(1, StringUtils.findUsingRabinKarp("aabc", "abc"));
		assertEquals(0, StringUtils.findUsingRabinKarp("aaaa", "a"));
		assertEquals(-1, StringUtils.findUsingRabinKarp("aaaa", "aaaaa"));
		assertEquals(0, StringUtils.findUsingRabinKarp("", ""));
		assertEquals(0, StringUtils.findUsingRabinKarp("a", ""));
	}	
	
	@Test
	void testString() {
		String str = "ab\u1001";
		assertEquals(3, str.length());
		assertEquals(5, str.getBytes().length);
	}
	
	@Test
	void testChar() {
		char ch = (char)-32768;
		assertEquals(32768, (int)ch);
	}
}

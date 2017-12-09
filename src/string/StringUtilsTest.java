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
		verifyAllVersionsOfFind(-1, "", "");
		verifyAllVersionsOfFind(-1, "a", "");
		verifyAllVersionsOfFind(22, "asdassdsdasdasdasdasdasddsasdsdsdaadsdsd", "sdd");
		verifyAllVersionsOfFind(13, "baaabbbbaaabbababacabbbbaaabbbbbba", "ababaca");
	}

	@Test
	void testFindAll() {
		assertArrayEquals(new Integer[] { 0, 2, 4 },
				StringUtils.findAllUsingKMP("abababab", "aba").toArray(new Integer[] {}));
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

	private static String cloneWithRedundantSpacesRemoved(String str) {
		StringBuilder sb = new StringBuilder(str);
		StringUtils.removeRedundantSpaces(sb);
		return sb.toString();
	}

	@Test
	void testRemoveRedundantSpaces() {
		assertEquals("abc", cloneWithRedundantSpacesRemoved("abc"));
		assertEquals("", cloneWithRedundantSpacesRemoved(""));
		assertEquals("a b", cloneWithRedundantSpacesRemoved("  a b  "));
		assertEquals("a b", cloneWithRedundantSpacesRemoved("a  b"));
		assertEquals("a b", cloneWithRedundantSpacesRemoved("  a  b"));
		assertEquals("a b", cloneWithRedundantSpacesRemoved("a  b "));
	}

	private static String cloneWithDeduplication(String str) {
		StringBuilder sb = new StringBuilder(str);
		StringUtils.deduplicate(sb);
		return sb.toString();
	}

	@Test
	void testDeduplicate() {
		assertEquals("abc", cloneWithDeduplication("abc"));
		assertEquals("abc", cloneWithDeduplication("aabbc"));
		assertEquals("aba", cloneWithDeduplication("aabba"));
		assertEquals("aba", cloneWithDeduplication("aabbaa"));
		assertEquals("a", cloneWithDeduplication("aa"));
		assertEquals("a", cloneWithDeduplication("aaaaa"));
	}

	private static String cloneWithRepeatedDeduplication(String str) {
		StringBuilder sb = new StringBuilder(str);
		StringUtils.repeatedlyDeduplicate(sb);
		return sb.toString();
	}

	@Test
	void testRepeatedlyDeduplicate() {
		assertEquals("abc", cloneWithRepeatedDeduplication("abc"));
		assertEquals("c", cloneWithRepeatedDeduplication("aabbc"));
		assertEquals("a", cloneWithRepeatedDeduplication("aabba"));
		assertEquals("", cloneWithRepeatedDeduplication("aabbaa"));
	}

	private static String cloneWithRotation(String str, int shift) {
		StringBuilder sb = new StringBuilder(str);
		StringUtils.rotate(sb, shift);
		return sb.toString();
	}

	@Test
	void testRotate() {
		assertEquals("abc", cloneWithRotation("cab", 2));
		assertEquals("abc", cloneWithRotation("abc", 3));
		assertEquals("abc", cloneWithRotation("abc", 6));
		assertEquals("cdefab", cloneWithRotation("abcdef", 4));
		assertEquals("defabc", cloneWithRotation("abcdef", 3));
		assertEquals("efabcd", cloneWithRotation("abcdef", 2));
		assertEquals("fabcde", cloneWithRotation("abcdef", 1));
		assertEquals("", cloneWithRotation("", 2));
	}

	private static String cloneWithAllPatternsReplaced(String text, String pattern, String replacement) {
		StringBuilder sb = new StringBuilder(text);
		StringUtils.replaceAll(sb, pattern, replacement);
		return sb.toString();
	}

	@Test
	void testReplaceAll() {
		assertEquals("ccba", cloneWithAllPatternsReplaced("abaababa", "aba", "c"));
		assertEquals("ba", cloneWithAllPatternsReplaced("abaababa", "aba", ""));
		assertEquals("abc", cloneWithAllPatternsReplaced("abc", "", "c"));
		assertEquals("ccccccccba", cloneWithAllPatternsReplaced("abaababa", "aba", "cccc"));
	}

	@Test
	void testToHex() {
		assertEquals("0x7FFFFFFF", StringUtils.toHex(Integer.MAX_VALUE));
		assertEquals("0xAA", StringUtils.toHex(10 * 16 + 10));
		assertEquals("0x0", StringUtils.toHex(0));
	}
}

package string;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyStringTest {

	private static String cloneWithRedundantSpacesRemoved(String str) {
		MyString myStr = new MyString(str);
		myStr.removeRedundantSpaces();
		return myStr.toString();
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
		MyString myStr = new MyString(str);
		myStr.deduplicate();
		return myStr.toString();
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
		MyString myStr = new MyString(str);
		myStr.repeatedlyDeduplicate();
		return myStr.toString();
	}

	@Test
	void testRepeatedlyDeduplicate() {
		assertEquals("abc", cloneWithRepeatedDeduplication("abc"));
		assertEquals("c", cloneWithRepeatedDeduplication("aabbc"));
		assertEquals("a", cloneWithRepeatedDeduplication("aabba"));
		assertEquals("", cloneWithRepeatedDeduplication("aabbaa"));
	}
}
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

}
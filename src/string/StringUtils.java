package string;

import java.util.ArrayList;
import java.util.Arrays;

public class StringUtils {
	public static void removeRedundantSpaces(StringBuilder sb) {
		int slow_index = 0;
		for (int fast_index = 0; fast_index < sb.length(); ++fast_index) {
			if (sb.charAt(fast_index) == ' ') {
				if (slow_index > 0 && sb.charAt(slow_index - 1) != ' ') {
					sb.setCharAt(slow_index, ' ');
					++slow_index;
				}
			} else {
				sb.setCharAt(slow_index, sb.charAt(fast_index));
				++slow_index;
			}
		}
		if (slow_index > 0 && sb.charAt(slow_index - 1) == ' ') {
			--slow_index;
		}
		sb.setLength(slow_index);
	}

	public static void deduplicate(StringBuilder sb) {
		int slow_index = 0;
		for (int fast_index = 0; fast_index < sb.length(); ++fast_index) {
			if (slow_index == 0 || sb.charAt(slow_index - 1) != sb.charAt(fast_index)) {
				sb.setCharAt(slow_index, sb.charAt(fast_index));
				++slow_index;
			}
		}
		sb.setLength(slow_index);
	}

	public static void repeatedlyDeduplicate(StringBuilder sb) {
		int slow_index = 0;
		int fast_index = 0;
		while (fast_index < sb.length()) {
			if (slow_index > 0 && sb.charAt(slow_index - 1) == sb.charAt(fast_index)) {
				while (fast_index < sb.length() && sb.charAt(slow_index - 1) == sb.charAt(fast_index)) {
					++fast_index;
				}
				--slow_index;
			} else {
				sb.setCharAt(slow_index, sb.charAt(fast_index));
				++slow_index;
				++fast_index;
			}
		}
		sb.setLength(slow_index);
	}

	private static int greatestCommonDivisor(int a, int b) {
		return (b == 0 ? a : greatestCommonDivisor(b, a % b));
	}

	private static void rotateFrom(StringBuilder sb, int start, int shift) {
		int n = sb.length();
		char oldChar = sb.charAt(start);
		int i = start;
		int j = (start + shift) % n;
		while (j != start) {
			sb.setCharAt(i, sb.charAt(j));
			i = j;
			j = (j + shift) % n;
		}
		sb.setCharAt(i, oldChar);
	}

	public static void rotate(StringBuilder sb, int shift) {
		if (sb.length() == 0) {
			return;
		}

		shift = Math.floorMod(sb.length() - shift, sb.length());
		int gcd = greatestCommonDivisor(sb.length(), shift);
		for (int i = 0; i < gcd; ++i) {
			rotateFrom(sb, i, shift);
		}
	}

	private static boolean matchesFrom(String text, int occurrence_start, String pattern) {
		for (int i = 0; i < pattern.length(); ++i) {
			if (occurrence_start + i >= text.length() || text.charAt(occurrence_start + i) != pattern.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	public static int find(String text, String pattern) {
		for (int occurrence_start = 0; occurrence_start + pattern.length() <= text.length(); ++occurrence_start) {
			if (matchesFrom(text, occurrence_start, pattern)) {
				return occurrence_start;
			}
		}
		return -1;
	}

	private final static int MAX_PRIME_INT = 0x7FFFFFFF;
	private final static int CHAR_BASE = (int) Character.MAX_VALUE + 1;

	public static int findUsingRabinKarp(String text, String pattern) {
		if (pattern.length() == 0) {
			return 0;
		}

		long maxCharToPatternLength = 1;
		for (int i = 0; i < pattern.length(); ++i) {
			maxCharToPatternLength = maxCharToPatternLength * CHAR_BASE % MAX_PRIME_INT;
		}
		long minusMaxCharToPatternLength = (MAX_PRIME_INT - maxCharToPatternLength) % MAX_PRIME_INT;

		long patternHash = 0;
		for (int i = 0; i < pattern.length(); ++i) {
			patternHash = (patternHash * CHAR_BASE + pattern.charAt(i)) % MAX_PRIME_INT;
		}

		long occurrenceHash = 0;
		for (int occurrenceEnd = 0; occurrenceEnd < text.length(); ++occurrenceEnd) {
			occurrenceHash = (occurrenceHash * CHAR_BASE + text.charAt(occurrenceEnd)) % MAX_PRIME_INT;
			int occurrenceStart = occurrenceEnd - pattern.length() + 1;
			if (occurrenceStart > 0) {
				occurrenceHash = (occurrenceHash + text.charAt(occurrenceStart - 1) * minusMaxCharToPatternLength)
						% MAX_PRIME_INT;
			}
			if (occurrenceStart >= 0 && occurrenceHash == patternHash && matchesFrom(text, occurrenceStart, pattern)) {
				return occurrenceStart;
			}
		}
		return -1;
	}

	private static int[] computePi(String pattern) {
		int[] pi = new int[pattern.length()];
		pi[0] = -1;
		for (int j = 1; j < pattern.length(); ++j) {
			int k = pi[j - 1];
			while (k >= 0 && pattern.charAt(k + 1) != pattern.charAt(j)) {
				k = pi[k];
			}
			if (pattern.charAt(k + 1) == pattern.charAt(j)) {
				++k;
			}
			pi[j] = k;
		}
		return pi;
	}

	public static int findUsingKMP(String text, String pattern) {
		ArrayList<Integer> occurrences = findAllUsingKMP(text, pattern);
		if (occurrences.isEmpty()) {
			return -1;
		}
		return occurrences.get(0);
	}

	public static ArrayList<Integer> findAllUsingKMP(String text, String pattern) {
		if (pattern.isEmpty()) {
			return new ArrayList<Integer>(Arrays.asList(0));
		}

		int[] pi = computePi(pattern);

		ArrayList<Integer> occurrences = new ArrayList<Integer>();
		int j = -1;
		for (int i = 0; i < text.length(); ++i) {
			// text[i - 1] == pattern[j]
			while (j >= 0 && (j + 1 >= pattern.length() || pattern.charAt(j + 1) != text.charAt(i))) {
				j = pi[j];
			}
			if (pattern.charAt(j + 1) == text.charAt(i)) {
				++j;
			}
			if (j == pattern.length() - 1) {
				occurrences.add(i - pattern.length() + 1);
			}
		}
		return occurrences;
	}
}

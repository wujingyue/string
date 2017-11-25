package string;

public class StringUtils {
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

		long patternHash = 0;
		for (int i = 0; i < pattern.length(); ++i) {
			patternHash = (patternHash * CHAR_BASE + pattern.charAt(i)) % MAX_PRIME_INT;
		}

		long occurrenceHash = 0;
		for (int occurrenceEnd = 0; occurrenceEnd < text.length(); ++occurrenceEnd) {
			occurrenceHash = (occurrenceHash * CHAR_BASE + text.charAt(occurrenceEnd)) % MAX_PRIME_INT;
			int occurrenceStart = occurrenceEnd - pattern.length() + 1;
			if (occurrenceStart > 0) {
				occurrenceHash = (occurrenceHash
						- text.charAt(occurrenceStart - 1) * maxCharToPatternLength % MAX_PRIME_INT + MAX_PRIME_INT)
						% MAX_PRIME_INT;
			}
			if (occurrenceStart >= 0 && occurrenceHash == patternHash && matchesFrom(text, occurrenceStart, pattern)) {
				return occurrenceStart;
			}
		}
		return -1;
	}
}

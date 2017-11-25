package string;

import java.util.ArrayList;

public class MyString {
	public MyString(String str) {
		data = new ArrayList<Character>();
		for (int i = 0; i < str.length(); ++i) {
			data.add(str.charAt(i));
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(data.size());
		for (Character ch : data) {
			sb.append(ch);
		}
		return sb.toString();
	}

	public void removeRedundantSpaces() {
		int slow_index = 0;
		for (int fast_index = 0; fast_index < data.size(); ++fast_index) {
			if (data.get(fast_index) == ' ') {
				if (slow_index > 0 && data.get(slow_index - 1) != ' ') {
					data.set(slow_index, ' ');
					++slow_index;
				}
			} else {
				data.set(slow_index, data.get(fast_index));
				++slow_index;
			}
		}
		if (slow_index > 0 && data.get(slow_index - 1) == ' ') {
			--slow_index;
		}
		data.subList(slow_index, data.size()).clear();
	}

	public void deduplicate() {
		int slow_index = 0;
		for (int fast_index = 0; fast_index < data.size(); ++fast_index) {
			if (slow_index == 0 || data.get(slow_index - 1) != data.get(fast_index)) {
				data.set(slow_index, data.get(fast_index));
				++slow_index;
			}
		}
		data.subList(slow_index, data.size()).clear();
	}

	public void repeatedlyDeduplicate() {
		int slow_index = 0;
		int fast_index = 0;
		while (fast_index < data.size()) {
			if (slow_index > 0 && data.get(slow_index - 1) == data.get(fast_index)) {
				while (fast_index < data.size() && data.get(slow_index - 1) == data.get(fast_index)) {
					++fast_index;
				}
				--slow_index;
			} else {
				data.set(slow_index, data.get(fast_index));
				++slow_index;
				++fast_index;
			}
		}
		data.subList(slow_index, data.size()).clear();
	}

	private static int greatestCommonDivisor(int a, int b) {
		return (b == 0 ? a : greatestCommonDivisor(b, a % b));
	}

	private void rotateFrom(int start, int shift) {
		int n = data.size();
		char oldChar = data.get(start);
		int i = start;
		int j = (start + shift) % n;
		while (j != start) {
			data.set(i, data.get(j));
			i = j;
			j = (j + shift) % n;
		}
		data.set(i, oldChar);
	}

	public void rotate(int shift) {
		if (data.isEmpty()) {
			return;
		}

		shift = Math.floorMod(data.size() - shift, data.size());
		int gcd = greatestCommonDivisor(data.size(), shift);
		for (int i = 0; i < gcd; ++i) {
			rotateFrom(i, shift);
		}
	}

	private ArrayList<Character> data;
}

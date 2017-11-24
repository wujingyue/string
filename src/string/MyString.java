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

	private boolean matchesFrom(int occurrence_start, MyString pattern) {
		for (int i = 0; i < pattern.data.size(); ++i) {
			if (occurrence_start + i >= data.size() || data.get(occurrence_start + i) != pattern.data.get(i)) {
				return false;
			}
		}
		return true;
	}

	public int find(MyString pattern) {
		for (int occurrence_start = 0; occurrence_start < data.size(); ++occurrence_start) {
			if (matchesFrom(occurrence_start, pattern)) {
				return occurrence_start;
			}
		}
		return -1;
	}

	private ArrayList<Character> data;
}

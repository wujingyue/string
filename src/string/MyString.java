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

	private ArrayList<Character> data;
}

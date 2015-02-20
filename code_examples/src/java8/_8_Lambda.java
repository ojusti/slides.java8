package java8;

import java.util.Arrays;
import java.util.Comparator;

public class _8_Lambda {

	void sortByLengthInJava7(String[] strings) {
		Arrays.sort(strings, new Comparator<String>() {
			@Override
			public int compare(String first, String second) {
				return Integer.compare(first.length(), second.length());
			}
		});
	}
	
	void sortByLengthInJava8(String[] strings) {
		Arrays.sort(strings, (first, second) -> Integer.compare(first.length(), second.length()));
//		Arrays.sort(strings, ((Comparator<String>) ((first, second) -> Integer.compare(first.length(), second.length()))).thenComparing(Comparator.naturalOrder()));
	}
	
	void sortByLengthInJava8WithMethodRed(String[] strings) {
		Arrays.sort(strings, _8_Lambda::compareLengths);
	}

	static int compareLengths(String first, String second) {
		return Integer.compare(first.length(), second.length());
	}
}

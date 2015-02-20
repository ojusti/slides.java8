package java8;

import java.util.Arrays;
import java.util.Comparator;

public class _8_Lambda {

	void compareInJava7(String[] strings) {
		Arrays.sort(strings, new Comparator<String>() {
			@Override
			public int compare(String first, String second) {
				return Integer.compare(first.length(), second.length());
			}
		});
	}
	
	void compareInJava8(String[] strings) {
		Arrays.sort(strings, (first, second) -> Integer.compare(first.length(), second.length()));
	}
	
}

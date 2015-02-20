package java8;

import java.math.BigDecimal;
import java.util.Arrays;

public class _8_Refs {
	void sortByLengthInJava8(String[] strings) {
		Arrays.sort(strings, (first, second) -> Integer.compare(first.length(), second.length()));
	}
	
	void staticMethodRef(String[] strings) {
		Arrays.sort(strings, _8_Lambda::compareLengths);
	}

	static int compareLengths(String first, String second) {
		return Integer.compare(first.length(), second.length());
	}
	
	
	void knownInstanceMethodRef(String[] strings) {
		Arrays.sort(strings, this::compareStringByLength);
	}

	int compareStringByLength(String first, String second) {
		return Integer.compare(first.length(), second.length());
	}
	
	void λParameterInstanceMethodRef(String[] strings) {
		Arrays.sort(strings, String::compareToIgnoreCase);
	}
	
	void constructorRef(String[] strings) {
		Arrays.stream(strings).forEach(BigDecimal::new);
	}

	
}

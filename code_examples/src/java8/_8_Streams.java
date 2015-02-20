package java8;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import org.junit.Test;

public class _8_Streams {
	@Test
	public void printAll() {
		Arrays.asList(1, 5, 7, 9).stream().forEach(System.out::println);
	}

	public void printAll_java8(Collection<Integer> collection) {
		collection.stream().filter(i -> i < 10).sorted().forEach(System.out::println);
	}
	
	
	@Test
	public void statelessOperations() {
		Arrays.asList(1, 5, 7, 9).stream().peek(System.out::println).forEach(System.out::println);
	}
	
	@Test
	public void statefulOperations() {
		Arrays.asList(1, 5, 7, 9).stream().peek(System.out::println).sorted().forEach(System.out::println);
	}
	
	@Test
	public void statelessOperations_memory() {
		System.out.println(IntStream.iterate(0, i -> i+1).limit(999_999_999).max());
	}
	
	@Test
	public void statefullOperations_memory() {
		System.out.println(IntStream.iterate(0, i -> i + 1).limit(999_999_999).sorted().max());
	}

	
}

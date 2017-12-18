package tujuh.suganda.training;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class SearchStream {
public static void main(String[] args) {
	List<String> myList =
		    Arrays.asList("a1", "c28", "b1", "c2", "c1","c1","c2");
		
	myList
		    .stream()
		    .filter(s -> s.startsWith("c"))
		    .map(String::toUpperCase)
		    .sorted()
		    .forEach(System.out::println);
	
	Iterator<String> x = myList
    .stream()
    .distinct().iterator();
	
//	for (String element : x) {
//
//	}
	
}
}

package com.ebdesk.training;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamFilter {
public static void main(String[] args) {
	//Stream for Filter
	List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
	List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
	List<String> filtered2 = strings.stream().filter(string -> string.contains("jkl")).collect(Collectors.toList());
	System.out.println(strings);
	
	System.out.println(filtered);
}
}

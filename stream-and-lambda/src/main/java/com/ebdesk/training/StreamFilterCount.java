package com.ebdesk.training;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamFilterCount {
public static void main(String[] args) {
	List<String> strings = Arrays.asList("abc"," ","sulung"," ","suganda","sulung", "sulung", "bc", "efg", "sulung", "jkl");
	
	List<String> distinct = strings.stream().filter(data->!data.isEmpty()).distinct().collect(Collectors.toList());

	//Filter With two
	//List<String> distinct = strings.stream().filter(data->!data.isEmpty() && data.equals("sulung")).distinct().collect(Collectors.toList());
	long countempty = strings.parallelStream().filter(string -> string.isEmpty()).count();

	long countNotEmpty = strings.parallelStream().filter(string -> !string.isEmpty()).count();
	
	long countw = strings.parallelStream().filter(string -> string.startsWith("a")).count();
	
	
//	List<String> map = strings.stream().map(String::toUpperCase).filter(data->!data.isEmpty()).distinct().collect(Collectors.toList());
	
	List<String> map = strings.stream().filter(data->!data.trim().isEmpty()).map(data -> data+" changed").distinct().collect(Collectors.toList());
	
	System.out.println(map);
	
	System.out.println("from list\t\t: "+strings);
	System.out.println("After Distict\t\t: "+distinct);
	System.out.println("Count Empty\t\t: "+countempty);
	System.out.println("Count Not Empty\t\t: "+countNotEmpty);
	System.out.println("Count start with a\t: "+countw);
}
}

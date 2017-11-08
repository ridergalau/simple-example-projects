package com.ebdesk;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TestTime {
public static void main(String[] args) {
//	long timestamp = Long.valueOf("1509694414105"); //Example -> in ms
//	Date d = new Date(timestamp );
//	System.out.println(d);
	List<String> flavours = new ArrayList<>();
    flavours.add("chocolate");
    flavours.add("strawberry");
    flavours.add("vanilla");
    
	Iterator<String> flavoursIter = flavours.iterator();
    while (flavoursIter.hasNext()){
      System.out.println(flavoursIter.next());
    }
}
}

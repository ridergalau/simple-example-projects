package tujuh.suganda.mongo.basic;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class createDBandColl {
public static void main(String[] args) {
	
	try
	{
	//Connect to Database
	MongoClient mongoClient=new MongoClient("192.168.20.75",27017);
	DB db=mongoClient.getDB("test-create");
	System.out.println("Your connection to DB is ready for Use::"+db);

	//Create Collection
//
	
	String line = "col1,col2,col3";
	String[] parts = line.split(",");
	for (String part : parts) {

		DBCollection linked=db.createCollection(part,new BasicDBObject()); 
		System.out.println("Collection created successfully "+part);
	}
	
	}

	catch(Exception e)
	{
	System.out.println(e.getClass().getName()+":"+e.getMessage());

	}
	
	
}
}

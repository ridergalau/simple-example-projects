package tujuh.suganda.mongo.basic;

import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoLocal {
public static void main(String[] args) {
	MongoClient mongo = new MongoClient("localhost", 27017);
	//List<String> dbs = mongo.getDatabaseNames();
	//System.out.println(dbs);
	DB db = mongo.getDB("pegawai");	 //nama DB
	DBCollection col = db.getCollection("datanya"); //nama Collection
	
	for (int x=1;x<=30;x++)
	{	
	try {
		BasicDBObject doc = new BasicDBObject("_id","peg_"+x).
				append("nama", "Isabela"+x).
	            append("alamat", "ciamis"+x).
	            append("no_hp", "071596629192,"+x).
	            append("email", "didon.rosll@gmail.com"+x).
	            append("noRumah", "Blok no 989"+x);
	         col.insert(doc);
	         System.out.println("Document inserted successfully ke-"+x);	
	} catch (Exception e) {
        System.out.println("Document inserted Failed");	
	}
	}
}
}
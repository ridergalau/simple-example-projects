package tujuh.suganda.mongo.basic;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class InsertMongo {
public static void main(String[] args) {
	MongoClient mongo = new MongoClient("localhost", 27017);
	DB db = mongo.getDB("test");	
	DBCollection col = db.getCollection("coba");	
	for (int x=6;x<=10;x++) //Insert dengan Perulangan
	{	
	try {
		BasicDBObject doc = new BasicDBObject("_id","peg_"+x).
				append("nama", "Isabela"+x).
	            append("alamat", "ciamis"+x).
	            append("no_hp", "081312651652,"+x).
	            append("email", "didon.roll@gmail.com"+x).
	            append("noRumah", "Blok no 989"+x);
	         col.insert(doc);
	         System.out.println("Document inserted successfully ke-"+x);	
	} catch (Exception e) {
        System.out.println("Document inserted Failed");	
	}
	}
}
}
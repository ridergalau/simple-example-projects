package tujuh.suganda.mongo.basic;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class QueryDoc {
	public static void main(String[] args) {
		MongoClient cli = new MongoClient("suganda", 27017);
		DB db = cli.getDB("test");
		DBCollection collection = db.getCollection("facebookcoll");	//namaCollection
		try {
			DBCursor cursor = collection.find().skip(3);
			while(cursor.hasNext()) {
			    System.out.println(cursor.next());
			}
		} catch (Exception e) {
	     System.out.println("GAGAL!");	
		}
		
		//query and
//		System.out.println("=================================");
//		try {
//			BasicDBObject andQuery = new BasicDBObject();
//			List<BasicDBObject> objYgDicari = new ArrayList<BasicDBObject>();
//			objYgDicari.add(new BasicDBObject("nama", "Isabela7"));
//			objYgDicari.add(new BasicDBObject("alamat", "ciamis13"));
//			andQuery.put("$and", objYgDicari);
//			
//			System.out.println(andQuery.toString());
//
//			DBCursor cursor = collection.find(andQuery);
//			while (cursor.hasNext()) {
//				System.out.println(cursor.next());
//			}
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
	}

}

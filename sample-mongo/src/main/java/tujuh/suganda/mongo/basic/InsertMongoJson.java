package tujuh.suganda.mongo.basic;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class InsertMongoJson {
public static void main(String[] args) {
	MongoClient mongo = new MongoClient("localhost", 27017);
	DB db = mongo.getDB("pegawai");	
	DBCollection col = db.getCollection("datanya");	
	
	try {
		String json = "{'_id':'mencobajason','name':'mkyong', 'age':30}";
		DBObject dbObject = (DBObject)JSON.parse(json);
		col.insert(dbObject);
		System.out.println("Insert Json Berhasil!");
	} catch (Exception e) {
System.out.println("GAGAL");
	}

}
}

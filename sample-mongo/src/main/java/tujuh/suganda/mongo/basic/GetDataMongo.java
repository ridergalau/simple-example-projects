package tujuh.suganda.mongo.basic;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class GetDataMongo {
public static void main(String[] args) {
	MongoClient cli = new MongoClient("localhost", 27017);
	DB db = cli.getDB("pegawai");
	DBCollection collection = db.getCollection("datanya");	
	
	try {
		DBCursor mydata = collection.find().limit(10);//dengan Limit
//		DBCursor mydata = collection.find(); //Tanpa Limit
		
		//Cara Pertama (Semua)
		int i = 1;	
	  while (mydata.hasNext()) { 
	     System.out.println("Document ke - "+i); 
	     System.out.println(mydata.next()); 
	     i++;
	  }
	  
	  //Cara Kedua (Berdasarkan Field (nama))
//	  int x=1;
//		while (mydata.hasNext()) {
//		DBObject data = mydata.next();
//		String nama = data.get("nama").toString(); //get Element/Object
//		System.out.println(x+". "+nama);
//		x++;
//		}  
		
	} catch (Exception e) {
     System.out.println("GAGAL!");	
	} 
}
}

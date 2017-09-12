package tujuh.suganda.mongo.basic;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class RemoveDoc {
	public static void main(String[] args) {
		MongoClient cli = new MongoClient("localhost", 27017);
		DB db = cli.getDB("pegawai");
		DBCollection collection = db.getCollection("datanya");

		
		try {
           //Hapus 1 Doc berdasrkan _id
//			BasicDBObject document = new BasicDBObject();
//			document.put("_id", "peg_");
//			collection.remove(document);
//			System.out.println("Berhasil Di Hapus! ");

			// hapus doc Berdasarkan _id Dalam perulangan 
			for (int i=29;i>=11;i--)
			{
		BasicDBObject document = new BasicDBObject();
		document.put("_id", "peg_"+i);
		collection.remove(document);
		System.out.println("Berhasil Di Hapus! -> "+i);
			}
			}
		catch(Exception e)
		{
			System.out.println("gagal Di Hapus!");
		}
}
}
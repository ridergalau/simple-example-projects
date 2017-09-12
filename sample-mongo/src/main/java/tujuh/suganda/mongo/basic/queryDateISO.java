package tujuh.suganda.mongo.basic;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;

public class queryDateISO {
	public static void main(String[] args) {

		// Calendar calendar = Calendar.getInstance();
		// calendar.add(Calendar.HOUR, -30);
		// Date date = calendar.getTime();
		//
		//
		// Calendar calendar1 = Calendar.getInstance();
		// calendar1.add(Calendar.HOUR, -50);
		// Date date1 = calendar1.getTime();
		//

		long to = Long.valueOf("1504855165062");
		long from = Long.valueOf("1504714117062");
		
		System.out.println("--> " + timestampToDate(from));
		System.out.println("--> " + timestampToDate(to));

		try {
			MongoClient cli = new MongoClient("192.168.20.139", 27017);
			DB db = cli.getDB("social_media_analysis");
			DBCollection collection = db.getCollection("sma_post");
			// DBCursor cursor = collection.find().limit(10);

			// DBCursor cursor = collection.find();
			DBObject query = QueryBuilder.start().put("created_at")
					.greaterThan(
							new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(timestampToDate(from)))
					.lessThan(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(timestampToDate(to)))
					.get();

			// BasicDBObject query = new BasicDBObject();
			// query.put("created_at", BasicDBObjectBuilder
			// .start("$gte","ISODate("+(timestampToDate(from)+")"))
			// .add("$lte", "ISODate("+timestampToDate(to)+")").get());
			System.out.println(query.toString());
			DBCursor docs = collection.find(query);
			System.out.println(docs.count());
			while (docs.hasNext()) {
				System.out.println(docs.next());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

	public static Date getDateTimeFromTimestamp(Long value) {
		TimeZone timeZone = TimeZone.getDefault();
		long offset = timeZone.getOffset(value);
		if (offset < 0) {
			value -= offset;
		} else {
			value += offset;
		}
		return new Date(value);
	}

	private static String timestampToDate(long timestamp) {
		Timestamp ts = new Timestamp(timestamp);
		Date date = new Date(ts.getTime());

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

		return dateFormat.format(date);
		// return date;
	}

}

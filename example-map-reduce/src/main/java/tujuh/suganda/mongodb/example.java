package tujuh.suganda.mongodb;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;
import com.mongodb.MongoClient;

public class example {
	public static void main(String[] args) throws UnknownHostException {

		// create an instance of client and establish the connection
		MongoClient m1 = new MongoClient("localhost");

		// get the test db,use your own
		DB db = m1.getDB("mydb");

		// get the car collection
		DBCollection coll = db.getCollection("car");

		// map function to categorize overspeed cars
//		String carMap = "function (){" + "var criteria;" + "if ( this.speed > 70 ) {" + "criteria = 'overspeed';"
//				+ "emit(criteria,this.speed);" + "}" + "};";
//System.out.println(carMap);
//		// reduce function to add all the speed and calculate the average speed
//
//		String carReduce = "function(key, speed) {" + "var total =0;" + "for (var i = 0; i < speed.length; i++) {"
//				+ "total = total+speed[i];" + "}" + "return total/speed.length;" + "};";
//System.out.println(carReduce);

		String map ="function () {"+
	            "emit('size', {count:1});"+
	    "}";
	 
	    String reduce = "function (key, values) { "+
	    " total = 0; "+
	    " for (var i in values) { "+
	        " total += values[i].count; "+
	    " } "+
	    " return {count:total} }";
		// create the mapreduce command by calling map and reduce functions
		MapReduceCommand mapcmd = new MapReduceCommand(coll, map, reduce, null,
				MapReduceCommand.OutputType.INLINE, null);

		// invoke the mapreduce command
		MapReduceOutput cars = coll.mapReduce(mapcmd);
        
		// print the average speed of cars
		for (DBObject o : cars.results()) {
			coll.insert(o);
			System.out.println(o.toString());

		}

	}
}

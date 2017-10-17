package tujuh.suganda.mongodb;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;
import com.mongodb.MongoClient;

public class example2 {
	public static void main(String[] args) throws UnknownHostException {

		// create an instance of client and establish the connection
		MongoClient m1 = new MongoClient("localhost");

		// get the test db,use your own
		DB db = m1.getDB("mydb");

		// get the car collection
		DBCollection coll = db.getCollection("car");


	     String map = "function (){" + "var criteria;"
	                + "if ( this.speed > 70 ) {" + "criteria = 'overspeed';"
	                + "emit(criteria,this.speed);" + "}" + "}";
	 
	        // reduce function to add all the speed and calculate the average speed
	 
	        String reduce = "function(key, speed) {" + "var total =0;"
	                + "for (var i = 0; i < speed.length; i++) {"
	                + "total = total+speed[i];" + "}"
	                + "return total/speed.length;" + "}";
	     		// create the mapreduce command by calling map and reduce functions
	    		MapReduceCommand mapcmd = new MapReduceCommand(coll, map, reduce, null,
	    				MapReduceCommand.OutputType.INLINE, null);

	    		// invoke the mapreduce command
	    		MapReduceOutput cars = coll.mapReduce(mapcmd);

	    		// print the average speed of cars
	    		for (DBObject o : cars.results()) {
//	    			coll.insert(o);
	    			System.out.println(o.toString());

	    


	}
}}
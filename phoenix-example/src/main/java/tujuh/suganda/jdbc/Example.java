package tujuh.suganda.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
public class Example {
public static void main(String[] args) throws SQLException {
	
	  String phoenixDriver = "org.apache.phoenix.jdbc.PhoenixDriver";
	    try {

	        Class.forName(phoenixDriver);
	    } catch (ClassNotFoundException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	        System.exit(1);
	    }

	Statement stmt = null;
	ResultSet rset = null;
	
	Connection con = DriverManager.getConnection("jdbc:phoenix:192.168.20.125:/hbase-unsecure");
	stmt = con.createStatement();
    System.out.println(1);	
	stmt.executeUpdate("create table testjava (mykey integer not null primary key, mycolumn varchar)");
	stmt.executeUpdate("upsert into testjava values (1,'Hello')");
	stmt.executeUpdate("upsert into testjava values (2,'World!')");
	con.commit();
    System.out.println(2);	
	PreparedStatement statement = con.prepareStatement("select * from testjava");
	rset = statement.executeQuery();
	while (rset.next()) {

		System.out.println(rset.getString("mycolumn"));
	
	}
    System.out.println(3);	
	statement.close();
	con.close();
}
}

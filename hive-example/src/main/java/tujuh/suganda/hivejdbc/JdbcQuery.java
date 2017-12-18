package tujuh.suganda.hivejdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcQuery {
	public static void main(String[] args) throws SQLException {
     String driverName = "org.apache.hive.jdbc.HiveDriver";

		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
 
		try {
			Connection con = DriverManager.getConnection("jdbc:hive2://192.168.20.123:10000/default");
			Statement stmt = con.createStatement();
			String sql = "select * from employeorc";
			
			ResultSet res = stmt.executeQuery(sql);
			System.out.println("Running: " + sql);
			res = stmt.executeQuery(sql);
			while (res.next()) {
				System.out.println(res.getString(1)+" | "+res.getString(2));
			}
			res.close();
			stmt.close();
			con.close();
						
		} catch (Exception e) {
	e.printStackTrace();		}
	}
}

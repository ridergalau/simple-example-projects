package tujuh.suganda.hivejdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcInsert {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		try {
			Class.forName("org.apache.hive.jdbc.HiveDriver");
			Connection con = DriverManager.getConnection("jdbc:hive2://192.168.20.123:10000/default", "hdfs", "");
			Statement stmt = con.createStatement();

			String sql = "insert into employeorc  values (5,'menciba','oke')";

			System.out.println("-> " + stmt.executeQuery(sql));

			System.out.println("OKE");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

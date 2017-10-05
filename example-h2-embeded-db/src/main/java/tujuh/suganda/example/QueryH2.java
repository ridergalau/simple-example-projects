package tujuh.suganda.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tujuh.suganda.config.H2Config;

public class QueryH2 {
	static H2Config h2;
	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		System.out.println("Connecting to database...");
		conn = h2.getDBConnection();
		// STEP 3: Execute a query
		System.out.println("Connected database successfully...");
		stmt = conn.createStatement();
		String sql = "SELECT id,name FROM PERSON";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			// Retrieve by column name
			int id = rs.getInt("id");
			String last = rs.getString("name");

			// Display values
			System.out.print("ID: " + id);
			System.out.print(", Age: " + last);
		}
	}
}

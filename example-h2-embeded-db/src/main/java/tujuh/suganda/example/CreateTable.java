package tujuh.suganda.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tujuh.suganda.config.H2Config;

public class CreateTable {
	static H2Config h2;
	static Connection connection = h2.getDBConnection();

	public static void main(String[] args) throws SQLException {

//		creaate();
		insertWithStatement();
	}

	public static void creaate() {
		PreparedStatement createPreparedStatement = null;
		String CreateQuery = "CREATE TABLE students(id int primary key, name varchar(25),address varchar(50),hp varchar(15))";
		try {
			connection.setAutoCommit(false);
			createPreparedStatement = connection.prepareStatement(CreateQuery);
			createPreparedStatement.executeUpdate();
			createPreparedStatement.close();
			System.out.println("OKE");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	  private static void insertWithStatement() throws SQLException {
	        Statement stmt = null;
	        try {
	            connection.setAutoCommit(false);
	            stmt = connection.createStatement();
	            stmt.execute("INSERT INTO students(id, name,address,hp) VALUES(1, 'Isabela','Ciamis no 2','081217872382')");
	            stmt.execute("INSERT INTO students(id, name,address,hp) VALUES(2, 'Adalah','Ciamis no 3','0812173298')");
	            stmt.execute("INSERT INTO students(id, name,address,hp) VALUES(3, 'Kisah','Ciamis no 7','0812178120')");

	            ResultSet rs = stmt.executeQuery("select * from students");
	            System.out.println("H2 In-Memory Database inserted through Statement");
	            while (rs.next()) {
	                System.out.println("Id " + rs.getInt("id") + " \nName " + rs.getString("name")+ " \nAddress " + rs.getString("address")+ " \nHandphone " + rs.getString("hp"));
	            }
	            stmt.close();
	            connection.commit();
	        } catch (SQLException e) {
	            System.out.println("Exception Message " + e.getLocalizedMessage());
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            connection.close();
	        }
	    }
	
}

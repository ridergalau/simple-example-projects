package tujuh.suganda.example;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement;  
public class Read {
	// JDBC driver name and database URL 
	   static final String JDBC_DRIVER = "org.h2.Driver";   
	   static final String DB_URL = "jdbc:h2:~/test";  
	   
	   //  Database credentials 
	   static final String USER = "sa"; 
	   static final String PASS = ""; 
	   
	   public static void main(String[] args) { 
	      Connection conn = null; 
	      Statement stmt = null; 
	      try { 
	         // STEP 1: Register JDBC driver 
	         Class.forName(JDBC_DRIVER); 
	         
	         // STEP 2: Open a connection 
	         System.out.println("Connecting to database..."); 
	         conn = DriverManager.getConnection(DB_URL,USER,PASS);  
	         
	         // STEP 3: Execute a query 
	         System.out.println("Connected database successfully..."); 
	         stmt = conn.createStatement(); 
	         String sql = "SELECT * FROM students"; 
	         ResultSet rs = stmt.executeQuery(sql); 
	         
	         // STEP 4: Extract data from result set 
	         while(rs.next()) { 
	             System.out.println("Id " + rs.getInt("id") + " \nName " + rs.getString("name")+ " \nAddress " + rs.getString("address")+ " \nHandphone " + rs.getString("hp"));
		           
	        	 
	            // Retrieve by column name 
//	            int id  = rs.getInt("id"); 
//	            int age = rs.getInt("age"); 
//	            String first = rs.getString("first"); 
//	            String last = rs.getString("name");  
	            
	            // Display values 
//	            System.out.print("ID: " + id); 
//	            System.out.print(", Age: " + last); 
//	            System.out.print(", First: " + first); 
//	            System.out.println(", Last: " + last); 
	         } 
	         // STEP 5: Clean-up environment 
	         rs.close(); 
	      } catch(SQLException se) { 
	         // Handle errors for JDBC 
	         se.printStackTrace(); 
	      } catch(Exception e) { 
	         // Handle errors for Class.forName 
	         e.printStackTrace(); 
	      } finally { 
	         // finally block used to close resources 
	         try { 
	            if(stmt!=null) stmt.close();  
	         } catch(SQLException se2) { 
	         } // nothing we can do 
	         try { 
	            if(conn!=null) conn.close(); 
	         } catch(SQLException se) { 
	            se.printStackTrace(); 
	         } // end finally try 
	      } // end try 
	      System.out.println("Goodbye!"); 
	   } 
}

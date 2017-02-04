package calapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConn {
	public static void main(String[] args) {

		try {
			String host = "jdbc:mysql://localhost:3306/Calendar";
			String uName = "root";
			String uPass = "";
			Connection con = DriverManager.getConnection(host, uName, uPass);	
			
		}
		
		catch (SQLException err) {
			System.out.println(err.getMessage());
		}
		
	}
}

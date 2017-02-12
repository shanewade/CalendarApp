package calapp;


import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

public class ValidateLogin {
//	public static void main(String[] args) {
//		System.out.println(checkPassword("swade", "password"));
//		
//	}
	
	
	
	
	public static Boolean checkPassword(String userName, String password) {
		UserDAO testDAO = new UserDAO();
		User testUser = testDAO.getUser(userName);
		if (password.equals(testUser.password)) {
			return true;
		}
		else {
			return false;
		}
		
		
	}

}

class UserDAO {
	public User getUser(String userName){
            User u = new User();
            u.userName = userName;
            try {
              String query = "SELECT userName, password FROM `user` WHERE userName= '"+userName+"'";
//            Connection conn = DataConn.getConnection();
//            Statement st= conn.createStatement();
            ResultSet rs= DataConn.Query(query);
            rs.next();
            String password = rs.getString("password");
            System.err.println(password);
            u.password = password;
            return u;
            } catch (SQLException e){
                System.out.println(e);
            }
            return null;
            
            
//		try {
//			String query = "SELECT userName, password FROM `user` WHERE userName= '"+userName+"'";
//			User u = new User();
//			u.userName = userName;
//			String host = "jdbc:mysql://localhost:3306/Calendar";
//			String uName = "root";
//			String uPass = "";
//			Connection con = DriverManager.getConnection(host, uName, uPass);	
//			Statement st = con.createStatement();
//			ResultSet rs = st.executeQuery(query);
//			rs.next();
//			String password = rs.getString("password");
//			u.password = password;
//			return u;
//			
//		}
//		catch (SQLException e) {
//			System.out.println(e);
//
//		}
//		return null;
	}
}

class User {
	String userName;
	String password;
	
}




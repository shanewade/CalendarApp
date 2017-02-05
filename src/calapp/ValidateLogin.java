package calapp;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

public class ValidateLogin {
	public static void main(String[] args) {
		UserDAO testDAO = new UserDAO();
		User testUser = testDAO.getUser("swade");
		System.out.println(testUser.password);
		
		
	}

}

class UserDAO {
	public User getUser(String userName){
		try {
			String query = "SELECT userName, password FROM `user` WHERE userName= '"+userName+"'";
			User u = new User();
			u.userName = userName;
			String host = "jdbc:mysql://localhost:3306/Calendar";
			String uName = "root";
			String uPass = "";
			Connection con = DriverManager.getConnection(host, uName, uPass);	
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
	//		String userName = rs.getString("1");
			String password = rs.getString("password");
			u.password = password;
			return u;
			
		}
		catch (SQLException e) {
			System.out.println(e);
			//return u;
		}
		return null;
	}
}

class User {
	String userName;
	String password;
	
}




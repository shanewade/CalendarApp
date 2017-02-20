package calapp;


import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

public class ValidateLogin {

static String loggedInUser;

	public static Boolean checkPassword(String userName, String password) {
		UserDAO testDAO = new UserDAO();
		User testUser = testDAO.getUser(userName);
		if (password.equals(testUser.getPassword())) {
		    loggedInUser = testUser.getUserName();
                    return true;
		}
		else {
			return false;
		}
		
		
	}

}

class UserDAO {
	public User getUser(String userName){
            User u = new User(userName);
            try {
              String query = "SELECT userName, password FROM `user` WHERE userName= '"+userName+"'";
            ResultSet rs= DataConn.Query(query);
            rs.next();
            String password = rs.getString("password");
            System.err.println(password);
            u.setPassword(password);
            return u;
            } catch (SQLException e){
                System.out.println(e);
            }
            return null;
            
            

	}
}

    




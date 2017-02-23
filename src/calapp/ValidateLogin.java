package calapp;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidateLogin {
    
public User u;

public static String loggedInUser;

	public static Boolean checkPassword(String userName, String password) {
		UserDAO testDAO = new UserDAO();
		User testUser = testDAO.getUser(userName);
		if (password.equals(testUser.getPassword())) {
		    //AppLauncher.loggedInUser = userName;
                    GlobalDataStore gsd = GlobalDataStore.getInstance();
                    gsd.setLoggedInUser(userName);
                    
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
            u.setPassword(password);
            return u;
            } catch (SQLException e){
                System.out.println(e);
            }
            return null;
            
            

	}
}

    




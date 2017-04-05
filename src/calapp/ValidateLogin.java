package calapp;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.time.*;

public class ValidateLogin {
    
public User u;

public static String loggedInUser;

	public static Boolean checkPassword(String userName, String password) {
		UserDAO testDAO = new UserDAO();
		User testUser = testDAO.getUser(userName);
                LoginActivity la = new LoginActivity();
                ZoneId zid = ZoneId.of("UTC");
                ZonedDateTime utc = ZonedDateTime.now(zid);
                
		if (password.equals(testUser.getPassword())) {
		    //AppLauncher.loggedInUser = userName;
                    GlobalDataStore gsd = GlobalDataStore.getInstance();
                    gsd.setLoggedInUser(userName);
                    CheckForAppointments.setReminderTimer();
                    try {
                      la.writelogin(userName + " Has successfully logged in at " + utc);  
                    } catch (IOException e) {
                        System.err.println(e);
                    }
                    
                    
                    return true;
                    
		}
		else {
                    try {
                      la.writelogin(userName + " Has failed to log in at " + utc);  
                    } catch (IOException e) {
                        System.err.println(e);
                    }
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

class LoginActivity {
    
    public void writelogin(String logvalue) throws IOException {
    
    Logger logger = Logger.getLogger("MyLog");  
    FileHandler fh;  

    try {  

        // This block configure the logger with handler and formatter  
        fh = new FileHandler("%h/UserActivity.log", true);  
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();  
        fh.setFormatter(formatter);  

        // the following statement is used to log any messages  
        logger.info(logvalue);  

    } catch (SecurityException | IOException e) {  
        e.printStackTrace();  
    }  
 
    }
}

    



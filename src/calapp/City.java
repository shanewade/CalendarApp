/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calapp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author swade
 */
public class City {
        public static int getCityID(String cityName){
        String query =  "SELECT cityID AS id FROM city WHERE city = '" + cityName + "' ;";  
        ResultSet rs = DataConn.Query(query);
            try {
                if (rs.next()){
                int nextID = rs.getInt("id");
                return nextID;
                }
        }
        catch (SQLException e) {
            System.err.println(e);
        }
        return 0; 
        }
      
        public static int getCurrentOrGetNextCityID(String cityName) {
        int cityId = getCityID(cityName);
        if ( cityId == 0){
                    ResultSet rs = DataConn.Query("SELECT MAX(cityID) AS id FROM city;");
        try {
            rs.next();
            int nextID = rs.getInt("id");
            nextID++;
            return nextID;
        }
        catch (SQLException e) {
            System.err.println(e);
        }
        }
        else {
            return cityId;
        }
        
        
        return 0;
    }

        public static int addNewCity(String cityName, int countryID){
            GlobalDataStore gsd = GlobalDataStore.getInstance();
            String loggedInUser = gsd.getLoggedInUser();
            Calendar calendar = Calendar.getInstance();
            int newCityID = getCurrentOrGetNextCityID(cityName);
            java.sql.Timestamp ts = new java.sql.Timestamp(calendar.getTime().getTime());
            String query = "INSERT INTO `city` (`city`, `cityID`,`countryID` ,`createDate`, `createBy`) "
                        + "VALUES ('" + cityName +"', '"+ newCityID +"', '"+countryID+"', '"+ts +"', '"+loggedInUser+"');";
            DataConn.Update(query);
            return newCityID;  
        } 
        
}

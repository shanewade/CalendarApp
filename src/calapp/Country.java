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
public class Country {
        public static int getCountryID(String countryName){
        String query =  "SELECT countryID AS id FROM country WHERE country = '" + countryName + "' ;";  
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
        public static int getCurrentOrGetNextCountryID(String countryName) {
        int countryId = getCountryID(countryName);
        if ( countryId == 0){
                    ResultSet rs = DataConn.Query("SELECT MAX(countryID) AS id FROM country;");
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
            return countryId;
        }
        return 0;   
}
        public static int addNewCountry(String countryName) {
        
            GlobalDataStore gsd = GlobalDataStore.getInstance();
            String loggedInUser = gsd.getLoggedInUser();
            Calendar calendar = Calendar.getInstance();
            int newCountryID = getCurrentOrGetNextCountryID(countryName);
            java.sql.Timestamp ts = new java.sql.Timestamp(calendar.getTime().getTime());
            String query = "INSERT INTO `country` (`country`, `countryID`,`createDate`, `createBy`) "
                        + "VALUES ('" + countryName +"', '"+ newCountryID +"','"+ts +"', '"+loggedInUser+"');";
            DataConn.Update(query);
            return newCountryID;
        }
}

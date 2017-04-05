/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calapp;

import static calapp.City.getCurrentOrGetNextCityID;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author swade
 */
public class Address {
    
    //Look in this method tomorrow.
    public static int getAddressID(String addressName){
        String query =  "SELECT addressID AS id FROM address WHERE address = '" + addressName + "' ;";  
        ResultSet rs = DataConn.Query(query);

        try {
                if (rs.next()){
                int nextID = rs.getInt("id");
                return nextID;
                }
        }
        catch (Exception e) {
            System.err.println(e);
        }
        return 0; 
    } 
    
    
    public static int addNewAddress(String addressName, String phone, int cityID, String postalCode) {
            GlobalDataStore gsd = GlobalDataStore.getInstance();
            String loggedInUser = gsd.getLoggedInUser();
            Calendar calendar = Calendar.getInstance();
            int newAddressID = getCurrentOrGetNextAddressID(addressName);
            java.sql.Timestamp ts = new java.sql.Timestamp(calendar.getTime().getTime());
            String query = "INSERT INTO `address` (`address`,`address2`, `addressID`, `cityID` , `phone` ,`createDate`,`lastUpdateBy`, `createdBy`, `postalCode`) "
                            + "VALUES ('" + addressName +"', 'none', '"+ newAddressID +"', '"+cityID+"','"+phone+"' , '"+ts +"', '"+loggedInUser+"','"+loggedInUser+"','"+postalCode+"');";
            System.err.println(query);
            Boolean updated = DataConn.Update(query);
            return newAddressID;  
    }
    
        public static int addNewAddress(String addressName, String addressName2, String phone, int cityID, String postalCode) {
            GlobalDataStore gsd = GlobalDataStore.getInstance();
            String loggedInUser = gsd.getLoggedInUser();
            Calendar calendar = Calendar.getInstance();
            int newAddressID = getCurrentOrGetNextAddressID(addressName);
            java.sql.Timestamp ts = new java.sql.Timestamp(calendar.getTime().getTime());
            String query = "INSERT INTO `address` (`address`,`address2`, `addressID`, `cityID` , `phone` ,`createDate`,`lastUpdateBy`, `createdBy`, `postalCode`) "
                            + "VALUES ('" + addressName +"', '"+ addressName2 +"', '" +newAddressID +"', '"+cityID+"','"+phone+"' , '"+ts +"', '"+loggedInUser+"','"+loggedInUser+"','"+postalCode+"');";
            System.err.println(query);
            Boolean updated = DataConn.Update(query);
            return newAddressID;  
    }
        
    public static int setAddressID(String addressName, int cityID, int phone, String country) {
        String queryCountry = "SELECT countryID AS id FROM country WHERE country = '" + country + "' ;";
        ResultSet rs = DataConn.Query(queryCountry);
        try {
            rs.next();
            int nextID = rs.getInt("id");
            if (nextID > 0) {
            int countryID = nextID;
            }else {
                String updateCompany;
            }
        }
        catch (SQLException e) {
            System.err.println(e);
        }
        
        
        return 0;
    }
    
    public static int getCurrentOrGetNextAddressID(String addressName) {
        int addressId = getAddressID(addressName);
        if ( addressId == 0){
                    String query = "SELECT MAX(addressID) AS id FROM address;";
                    ResultSet rs = DataConn.Query(query);
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
            return addressId;
        }
        
        
        return 0;
    }
    
}

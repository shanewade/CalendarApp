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
    public static int getAddressID(String addressName){
        String query =  "SELECT addressID AS id FROM address WHERE address = '" + addressName + "' ;";  
        System.err.println(query);
        ResultSet rs = DataConn.Query(query);
            try {
                rs.next();
                int nextID = rs.getInt("id");
                return nextID;
        }
        catch (SQLException e) {
            System.err.println(e);
        }
        return 0; 
    } 
    
    public static int addNewAddress(String addressName, int cityID) {
        Calendar calendar = Calendar.getInstance();
            int newAddressID = getCurrentOrGetNextAddressID(addressName);
            java.sql.Timestamp ts = new java.sql.Timestamp(calendar.getTime().getTime());
            String query = "INSERT INTO `address` (`address`, `addressID`,`cityID` ,`createDate`, `createBy`) "
                            + "VALUES ('" + addressName +"', '"+ newAddressID +"', '"+cityID+"', '"+ts +"', '"+ValidateLogin.loggedInUser+"');";
            System.err.println(query);
            Boolean updated = DataConn.Update(query);
            return newAddressID;  
    }
    
    public static int addNewAddress(String addressName,String address2Name, int cityID) {
        Calendar calendar = Calendar.getInstance();
            int newAddressID = getCurrentOrGetNextAddressID(addressName);
            java.sql.Timestamp ts = new java.sql.Timestamp(calendar.getTime().getTime());
            String query = "INSERT INTO `address` (`address`, `addressID`, `address2`,`cityID` ,`createDate`, `createBy`) "
                            + "VALUES ('" + addressName +"', '"+ newAddressID +"', '"+address2Name+", '"+cityID+"', '"+ts +"', '"+ValidateLogin.loggedInUser+"');";
            System.err.println(query);
            Boolean updated = DataConn.Update(query);
            return newAddressID;  
    }
    
    public static int addNewAddress(String addressName, int cityID, String phone, String username) {
        Calendar calendar = Calendar.getInstance();
            int newAddressID = getCurrentOrGetNextAddressID(addressName);
            java.sql.Timestamp ts = new java.sql.Timestamp(calendar.getTime().getTime());
            String query = "INSERT INTO `address` (`address`, `addressID`,`cityID` , `phone`, `createDate`, `createBy`) "
                            + "VALUES ('" + addressName +"', '"+ newAddressID +"', '"+cityID+"',"+phone+" , '"+ts +"', '"+username+"');";
            System.err.println(query);
            Boolean updated = DataConn.Update(query);
            return newAddressID;  
    }
    
    public static int setAddressID(String addressName, int cityID, int phone, String country) {
        String queryCountry = "SELECT countryID AS id FROM country WHERE country = '" + country + "' ;";
        System.err.println(queryCountry);
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
                    ResultSet rs = DataConn.Query("SELECT MAX(addressID) AS id FROM address;");
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

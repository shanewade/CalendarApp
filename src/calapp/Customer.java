/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calapp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author swade
 */
public class Customer {

    private int custId;
    private int custCityID;
    private int custCountryID;
    private int custPhone;
    private String custName;
    private int custAddress;
    
    Customer (int id,String name, int addr, int city, int country, int phone ) {
        custId = id;
        custName = name;
        custAddress = addr;
        custCityID = city;
        custCountryID = country;
        custPhone = phone;
        
    }

    public int getCustid(Customer c) {
        return c.custId;
    }
    
    public String getCustName (Customer c) {
        return c.custName;
    }
    
    public void setCustid(int id) {
        custId = id;
    }
    
    public void setCityid(int id) {
        custCityID = id;
    }
    
    public void setCountryID (int id) {
        custCountryID = id;
    }
    
    public static void printCustValues(Customer c) {
        System.err.println(c.custId);
        System.err.println(c.custName);
        System.err.println(c.custAddress);
        System.err.println(c.custCountryID);
        System.err.println(c.custCityID);
    }
    
    public static boolean addNewCustomer(int custID, String custName, int custAddrId) {
        String query = "INSERT INTO `customer` (`customerID`, `customerName`, `addressID`, `active`, `createDate`, `createdBy`) "
                        + "VALUES ('" + custID +"', '"+ custName +"', '1', '1', '2017-02-11 00:00:00', 'swade');";
        System.err.println(query);
        Boolean updated = DataConn.Update(query);
        
        
//INSERT INTO `customer` (`customerID`, `customerName`, `addressID`, `active`, `createDate`, `createdBy`, `lastUpdate`, `lastUpdatedBy`) 
//VALUES ('1', 'Joe Duncan', '1', '1', '2017-02-11 00:00:00', 'swade', NULL, NULL), 
//('2', 'Sandy Cheeks', '2', '1', '2017-02-11 00:00:00', 'swade', NULL, NULL);
   return false;
    }
    
    public static int getNextCustID() {
        ResultSet rs = DataConn.Query("SELECT MAX(customerID) AS id FROM customer;");
        try {
            rs.next();
            int nextID = rs.getInt("id");
            nextID++;
            return nextID;
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
    
    private static int getAddressID(String addressName){
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



    private static int getCountryID(String countryName){
        String query =  "SELECT countryID AS id FROM country WHERE country = '" + countryName + "' ;";  
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
    
    private static int getCityID(String cityName){
        String query =  "SELECT cityID AS id FROM city WHERE city = '" + cityName + "' ;";  
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
}

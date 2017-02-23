/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calapp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 *
 * @author swade
 */
public class Customer {

    private int custId;
    private int custCityID;
    private int custCountryID;
    private String custPhone;
    private String custName;
    private int custAddress;
    
    Customer (int id,String name, int addr, int city, int country, String phone ) {
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
    
    
    public static boolean addNewCustomer(int custID, String custName, int custAddrId) {
        GlobalDataStore gsd = GlobalDataStore.getInstance();
        String loggedInUser = gsd.getLoggedInUser();
        Calendar calendar = Calendar.getInstance();
        java.sql.Timestamp ts = new java.sql.Timestamp(calendar.getTime().getTime());
        String query = "INSERT INTO `customer` (`customerID`, `customerName`, `addressID`, `active`, `createDate`, `createdBy`) "
                        + "VALUES ('" + custID +"', '"+ custName +"', '"+custAddrId+"', '1', '" +ts +"', '"+loggedInUser+"');";
        Boolean updated = DataConn.Update(query);
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
    
    
    
    
    
    
    
    
    
    




}

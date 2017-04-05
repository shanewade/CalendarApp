/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calapp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 *
 * @author swade
 */
public class Customer {

    private int custId;
    private int custCityID;
    private int custCountryID;
    private final String custPhone;
    private final String custName;
    private final int custAddress;
    
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
        LocalDateTime ldt = LocalDateTime.now();
        Timestamp ts = Timestamp.from(MySqlDate.getGMTMySQLDate(ldt));
        System.err.println(ts);
        String query = "INSERT INTO `customer` (`customerID`, `customerName`, `addressID`, `active`, `createDate`,`lastUpdateBy`, `createdBy`) "
                        + "VALUES ('" + custID +"', '"+ custName +"', '"+custAddrId+"', '1', '" +ts +"', '"+loggedInUser+"', '"+loggedInUser+"');";
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
    
    public static int getCustIDWithName(String name){
        ResultSet rs = DataConn.Query("SELECT customerID AS id FROM customer WHERE customerName = '"+name+"';");
        try {
            rs.next();
            int custID = rs.getInt("id");
            return custID;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return 0;
    }
    
    
    
    
    
    
    
    




}

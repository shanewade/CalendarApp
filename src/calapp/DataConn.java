package calapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Clock;


public class DataConn {
    
    //Database configuration settings
    private static final String address = "localhost";
    private static final String user = "root";
    private static final String pass = "";  
    private static final String database = "Calendar";
    //Database configurations settings
    
    private static final DataConn db = new DataConn();
    static Connection conn;
    
    private DataConn() {
        conn = null;
    }
    public static Connection getConnection(){
        return conn;
    }
    
    public static DataConn getInstance() {
        return db;
    }
    
    public static boolean connectmysql() {
        
        String URL = "jdbc:mysql://" + address + "/" + database;
        System.err.println(URL);
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            System.err.println(e);
        }
        try {
            conn = DriverManager.getConnection(URL, user, pass);
            
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
        return true;
    } 
    
    public static ResultSet Query(String q) {
        ResultSet rs;
        try {
            DataConn.connectmysql();
            //conn = DataConn.getConnection();
            System.err.println(conn);
            System.err.println(q);
            Statement smt = conn.createStatement();
            rs = smt.executeQuery(q);
            return rs;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }
    
    public static boolean Update(String q) {
        System.out.println(q);
        try {
            Statement smt = conn.createStatement();
            int executeUpdate = smt.executeUpdate(q);
            System.out.println(executeUpdate + " rows changed");
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public static PreparedStatement GetPreparedStatement(String smt) {
        try {
            return conn.prepareStatement(smt);
            } catch (SQLException e) {
                System.out.println(e);
            }
        return null;
    }

}

package calapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DataConn {
    
    //Database configuration settings
    private static final String address = "52.206.157.109";
    private static final String user = "U02zbj";
    private static final String pass = "53687866205";  
    private static final String database = "U02zbj";
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
        // + "?useLegacyDatetimeCode=false&serverTimezone=UTC"
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
            Statement smt = conn.createStatement();
            rs = smt.executeQuery(q);
            return rs;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }
    
    public static boolean Update(String q) {
        try {
            Statement smt = conn.createStatement();
            int executeUpdate = smt.executeUpdate(q);
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

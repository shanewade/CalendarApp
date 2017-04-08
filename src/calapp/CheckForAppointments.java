/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calapp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;


/**
 *
 * @author swade
 */
public class CheckForAppointments {

    public static void setReminderTimer(){
    Timer timedevent = new Timer();
    timedevent.schedule(new RemindTask(), 0, 60000);
    }    
    }

    class RemindTask extends TimerTask{

        @Override
        public void run() {
           GlobalDataStore gsd = GlobalDataStore.getInstance();
           String loggedInUser = gsd.getLoggedInUser();
           checkForUpcomingAppts(loggedInUser);
        }

    

    static void checkForUpcomingAppts(String loggedInUser) {
        System.err.println("Checking for appts!");
        ArrayList<Appointment> data = new ArrayList();
        try {
        String query =   "SELECT title, customer.customerName, contact, start, end "
                        +"FROM `appointment` JOIN `customer` ON customer.customerID = appointment.customerID "
                        +"WHERE start BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 15 MINUTE) AND appointment.createdBy = '"+loggedInUser+"';";
                
        ResultSet rs = DataConn.Query(query);
            
           if (!rs.isLast()) {

           
           Appointment appt;
           while(rs.next()) {
               appt = new Appointment(rs.getString("title"), rs.getString("customer.customerName"), rs.getString("contact"), rs.getString("start"), rs.getString("end"));
               data.add(appt);
               
           }
           }
           
           //data.forEach(appointment -> System.err.println(appointment.apptTitle));
           
           Platform.runLater(new Runnable() {
                 @Override public void run() {
                     data.forEach(appointment -> Alert.alertInformation("Appointment Reminder: "+appointment.getApptTitle()+" is upcoming.", "Appointment Reminder", "Upcoming Appointment"));
                 }
           });
           
            
           
        } catch (SQLException e) {
            e.printStackTrace();
        }  
    }
    }

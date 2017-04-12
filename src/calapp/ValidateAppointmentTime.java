/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calapp;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author swade
 */
public class ValidateAppointmentTime {

static ObservableList<Appointment> data = FXCollections.observableArrayList();
    
   

public static boolean checkForConflicts(LocalDateTime start, LocalDateTime end, String loggedInUser) {
    
    //Get the data from MySQL and add to the ObservableList;
    try {
        String query =   "SELECT start, end, title , customer.customerName, contact  \n" +
                   "FROM `appointment`\n" +
                   "INNER JOIN `customer` ON  customer.customerID = appointment.customerID\n" +
                   "WHERE   appointment.createdBy = '"+loggedInUser+"';";
                
        ResultSet rs = DataConn.Query(query);
           
           DateTimeFormatter sqldf = DateTimeFormatter.ofPattern("yyy-MM-dd kk:mm:ss.S");
           if (!rs.isLast()) {
               int columnNumber = rs.findColumn("title");
           }
           Appointment appt;
           while(rs.next()) {
               String startString = rs.getString("start");
               String endString = rs.getString("end");
               
               LocalDateTime ldtStartFromString = LocalDateTime.parse(startString, sqldf);
               LocalDateTime ldtEndFromString = LocalDateTime.parse(endString, sqldf);
               
               ZonedDateTime zdtStartFromString = ldtStartFromString.atZone(ZoneId.of("UTC"));
               ZonedDateTime zdtEndFromString = ldtEndFromString.atZone(ZoneId.of("UTC"));
            
               ZoneId newzid = ZoneId.systemDefault();
               
               ZonedDateTime newzdtStart = zdtStartFromString.withZoneSameInstant(newzid);
               ZonedDateTime newzdtEnd = zdtEndFromString.withZoneSameInstant(newzid);
               
               appt = new Appointment(rs.getString("title"), rs.getString("customer.customerName"), rs.getString("contact"), newzdtStart.toLocalDateTime().toString(), newzdtEnd.toLocalDateTime().toString());
               data.add(appt);
               
           }

           
       } catch (Exception e){
           e.printStackTrace();
       }
    
    //Check for overlap for each one of the appointments
    
    for (Appointment appt : data) {
        LocalDateTime start1 = start;
        LocalDateTime end1 = end;
        LocalDateTime start2 = LocalDateTime.parse(appt.getStartDate());
        LocalDateTime end2 = LocalDateTime.parse(appt.getEndDate());
        
        LocalDateTime ldtMax = max(start1, start2);
        LocalDateTime ldtMin = min(end1, end2);
        
        if (ldtMax.isBefore(ldtMin)){
            return true;
        }
        
        
    }
    return false;
}


public static boolean checkForBusinessHours(LocalDateTime start, LocalDateTime end) {
    
    GlobalDataStore gsd = GlobalDataStore.getInstance();
    
    LocalTime businessStartTime = gsd.getBusinessStartTime();
    LocalTime businessEndTime = gsd.getBusinessEndTime();
    
    LocalTime apptStartTime = start.toLocalTime();
    LocalTime apptEndTime = end.toLocalTime();
    
    if (businessStartTime.isAfter(apptStartTime)){
        return true;
    }
    if (businessEndTime.isBefore(apptEndTime)){
        return true;
    }
    
    return false;
}


    private static LocalDateTime max(LocalDateTime ldt1, LocalDateTime ldt2){
        if (ldt1.isAfter(ldt2)) {
            return ldt1;
        }    
        else {
            return ldt2;
        }
       }
    
    private static LocalDateTime min(LocalDateTime ldt1, LocalDateTime ldt2){
            if (ldt1.isBefore(ldt2)) {
            return ldt1;
        }    
        else {
            return ldt2;
        }
       }
    
    
    
}

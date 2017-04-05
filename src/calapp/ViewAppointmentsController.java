/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calapp;

import java.net.URL;
import java.sql.ResultSet;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author swade
 */


public class ViewAppointmentsController implements Initializable {

@FXML TableView<Appointment> table;
@FXML TableColumn<Appointment, String> titleColumn;
@FXML TableColumn<Appointment, String> custNameColumn;
@FXML TableColumn<Appointment, String> custContactColumn;
@FXML TableColumn<Appointment, String> startDateColumn;
@FXML TableColumn<Appointment, String> endDateColumn;
@FXML Button viewByMonth;
@FXML Button viewByWeek;
@FXML Button incrementWeekMonth;
@FXML Button decrementWeekMonth;
@FXML Button cancel;
@FXML Button addNewAppt;

private int week;
private int month;
private int year;
private GlobalDataStore gsd;
private String loggedInUser;

private Boolean isMonthlyView;

ObservableList<Appointment> data = FXCollections.observableArrayList();
    
   

private void getByWeekAppts(int week, int year) {
    if (!data.isEmpty()) {
        data.clear();
    }
    isMonthlyView = false;
    try {
        String query =   "SELECT start, end, title , customer.customerName, contact  \n" +
                   "FROM `appointment`\n" +
                   "INNER JOIN `customer` ON  customer.customerID = appointment.customerID\n" +
                   "WHERE  YEAR(start) = "+year+"\n" +
                   "AND    WEEK(start) = "+week+"" +
                   " AND    appointment.createdBy = '"+loggedInUser+"';";
                
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
           
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
            custNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            custContactColumn.setCellValueFactory(new PropertyValueFactory<>("customerContact"));
            startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            
            
            table.setItems(data);
           
       } catch (Exception e){
           e.printStackTrace();
       }
}


private void getByMonthAppts(int month, int year){
        if (!data.isEmpty()) {
            data.clear();
            
        }
    
        isMonthlyView = true;
        try {
                   String query =   "SELECT start\n"
                              + ", end, title , customer.customerName\n"
                              + ", contact FROM `appointment` INNER JOIN `customer` ON customer.customerID = appointment.customerID\n" +
                              "WHERE  YEAR(start) = "+year+"\n" +
                              "AND    MONTH(start) = "+month+"" +
                              " AND    appointment.createdBy = '"+loggedInUser+"';";
                
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
           
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
            custNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            custContactColumn.setCellValueFactory(new PropertyValueFactory<>("customerContact"));
            startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            
            
            table.setItems(data);
           
       } catch (Exception e){
           e.printStackTrace();
       }
   }

@FXML private void cancel(){
        Control.mainscreen();
    }


    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gsd = GlobalDataStore.getInstance();
        loggedInUser = gsd.getLoggedInUser();
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        month = cal.get(Calendar.MONTH);
        month++; 
        year = cal.get(Calendar.YEAR);
        week = cal.get(Calendar.WEEK_OF_YEAR);

        try {
          getByMonthAppts(month, year);  
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        viewByMonth.setOnAction((ActionEvent event) -> {

            try {
            
                getByMonthAppts(month, year);
            } catch (Exception e) {
                System.err.println(e);
            }
        
        });  
        
        viewByWeek.setOnAction((ActionEvent event) -> {
            try {
                getByWeekAppts(week, year);
            } catch (Exception e) {
            }
        });
        
        incrementWeekMonth.setOnAction((ActionEvent event) -> {
           if (isMonthlyView)
            if (month == 12) {
                month = 1;
                year++;
                getByMonthAppts(month, year);}
            else {month++;
                getByMonthAppts(month, year);
            }
             
           else {
               if (week == 52) {
                   week = 1;
                   year++;
               }
               else {
               week++;
               }
               try { 
                   getByWeekAppts(week, year);
               } catch (Exception e) {
                   week--;
                   e.printStackTrace();
               }
           }
           
        });
        
        
        
        decrementWeekMonth.setOnAction((ActionEvent event) -> {
           if (isMonthlyView)
              
            if (month == 1) {
                month = 12;
                year--;
                getByMonthAppts(month, year);}
            else {
                month--;
                getByMonthAppts(month, year);
            }
           else {
               if (week == 1) {
                   week = 52;
                   year--;
               }
               else {
               week--;
               }
               try { 
                   getByWeekAppts(week, year);
               } catch (Exception e) {
                   week++;
                   e.printStackTrace();
               }
           }
           
        });
        
        addNewAppt.setOnAction((ActionEvent event) -> {

                           try {
                Control c = Control.getInstance();
                
                Stage stage = c.getStage();
                c.SetStage(stage);
                c.SetPane("newAppt");             
				
                } catch (Exception e1) {
                        System.err.println(e1);
                               
                }
        });
        

    }
}

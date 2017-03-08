/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calapp;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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


private Boolean isMonthlyView;

ObservableList<Appointment> data = FXCollections.observableArrayList();
    
   

private void getByWeekAppts(int week, int year) {
    if (!data.isEmpty()) {
        data.clear();
    }
    isMonthlyView = false;
    try {
           ResultSet rs = DataConn.Query(
                   "SELECT start, end, title , customer.customerName, contact  \n" +
                   "FROM `appointment`\n" +
                   "INNER JOIN `customer` ON  customer.customerID = appointment.customerID\n" +
                   "WHERE  YEAR(start) = "+year+"\n" +
                   "AND    WEEK(start) = "+week+""
           );
           if (!rs.isLast()) {
               int columnNumber = rs.findColumn("title");
               System.err.println("Got the resultset back");
           }
           Appointment appt;
           while(rs.next()) {
               System.err.println(rs.getString("title"));
               appt = new Appointment(rs.getString("title"), rs.getString("customer.customerName"), rs.getString("contact"), rs.getString("start"), rs.getString("end"));
               System.err.println(appt);
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
           ResultSet rs = DataConn.Query(
                   "SELECT start, end, title , customer.customerName, contact  \n" +
                   "FROM `appointment`\n" +
                   "INNER JOIN `customer` ON  customer.customerID = appointment.customerID\n" +
                   "WHERE  YEAR(start) = "+year+"\n" +
                   "AND    MONTH(start) = "+month+""
           );
           if (!rs.isLast()) {
               System.err.println("Got the resultset back");
 //              int columnNumber = rs.findColumn("title");
           }
           Appointment appt;
           while(rs.next()) {
               System.err.println(rs.getString("title"));
               appt = new Appointment(rs.getString("title"), rs.getString("customer.customerName"), rs.getString("contact"), rs.getString("start"), rs.getString("end"));
               System.err.println(appt);
               data.add(appt);
               
           }
           System.err.println("Size: "+data.size());
           
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
                System.err.println(e);
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
                System.err.println("Line 220: Month: "+month+"Year: "+year);
                getByMonthAppts(month, year);}
            else {
                month--;
                System.err.println("Line : 224ELSE Month: "+month+"Year: "+year);
                getByMonthAppts(month, year);
            }
             // System.err.println(isMonthlyView);
           else {
               if (week == 1) {
                   week = 52;
                   year--;
                   System.err.println("Line 232L Week: "+week+"Year: "+year);
               }
               else {
               week--;
               System.err.println("Line 236 Week: "+week+"Year: "+year);
               }
               try { 
                   getByWeekAppts(week, year);
                    //System.err.println("Line 240: Week: "+week+"Year: "+year);
               } catch (Exception e) {
                   week++;
                   System.err.println("Line 243: Week: "+week+"Year: "+year);
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

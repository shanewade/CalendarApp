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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


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

ObservableList<Appointment> data = FXCollections.observableArrayList();
    
   private void getByMonthAppts(int month, int year){
       try {
           System.err.println("Executed Method");
           ResultSet rs = DataConn.Query(
                   "SELECT start, end, title , customer.customerName, contact  \n" +
                   "FROM `appointment`\n" +
                   "INNER JOIN `customer` ON  customer.customerID = appointment.customerID\n" +
                   "WHERE  YEAR(start) = "+year+"\n" +
                   "AND    MONTH(start) = "+month+""
           );
           if (rs.next()) {
               System.err.println("Got the resultset back");
               int columnNumber = rs.findColumn("title");
               System.err.println(columnNumber);
           }
           Appointment appt;
           while(rs.next()) {
               //data.add(new Appointment(rs.getString("title"), rs.getString("customer.customerName"), rs.getString("contact"), rs.getString("start"), rs.getString("end")));
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        month++;   
        int year = cal.get(Calendar.YEAR);
        int week = cal.get(Calendar.WEEK_OF_YEAR);
//        System.err.println(month);
//        System.err.println(year);
//        System.err.println(week);
        try {
          getByMonthAppts(month, year);  
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
//       titleColumn.setCellFactory(new PropertyValueFactory("apptTitle"));
//       custNameColumn.setCellFactory(new PropertyValueFactory("customerName"));
//       custContactColumn.setCellFactory(new PropertyValueFactory("customerContact"));
//       startDateColumn.setCellFactory(new PropertyValueFactory("startDate"));
//       endDateColumn.setCellFactory(new PropertyValueFactory("endDate"));
//        
        //firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
    // TODO
    }    
    /**
     * SELECT start, end, title , customer.customerName, contact  
      FROM `appointment`
      INNER JOIN `customer` ON  customer.customerID = appointment.customerID
      WHERE  YEAR(start) = 2017
      AND    MONTH(start) = 2
     */
}

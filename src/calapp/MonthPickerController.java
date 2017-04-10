/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calapp;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

/**
 * FXML Controller class
 *
 * @author swade
 */


public class MonthPickerController implements Initializable {
    @FXML private ChoiceBox monthSelector;
    @FXML private ChoiceBox yearSelector;
    @FXML private Button runReport;
    
        ObservableList<String> month = observableArrayList(
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct"
           ,"Nov", "Dec");
        
            ObservableList<String> years = observableArrayList(
            "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026"
          , "2027", "2028");
            
            
        @FXML private void runReport() throws IOException, SQLException{
            String month = (String)monthSelector.getValue();
            String year = (String)yearSelector.getValue();
            String date = "01 " + month + " " + year;
            DateTimeFormatter  df = DateTimeFormatter.ofPattern("dd MMM yyyy");
            LocalDate startDate = LocalDate.parse(date, df);
            LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
            String query =   "SELECT customer.CustomerName, title , location, contact, start, end FROM appointment JOIN customer ON customer.customerId = appointment.customerId WHERE appointment.start BETWEEN '"+startDate+"' AND '"+endDate+"';";
            ResultSet rs = DataConn.Query(query);
            File fileToWrite;
            fileToWrite = new File("AppointmentsByMonth.html");
            FileWriter fw = new FileWriter(fileToWrite);
            ResultSetToHtmlTable rstht = new ResultSetToHtmlTable();
            rstht.writeTable(rs, fw);
            fw.close();
            Desktop.getDesktop().browse(fileToWrite.toURI());
        
            
            
        }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        monthSelector.setItems(month);
        yearSelector.setItems(years);
        
    }    
    
}

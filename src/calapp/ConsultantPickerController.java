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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

/**
 * FXML Controller class
 *
 * @author swade
 */
public class ConsultantPickerController implements Initializable {

     @FXML private ChoiceBox consultantList;
     @FXML private Button runReport;
    
    /**
     * Initializes the controller class.
     */
     
     @FXML private void runReport() throws IOException, SQLException {
        String userName = (String)consultantList.getValue();
        String query =   "SELECT customer.CustomerName, title , location, contact, start, end FROM appointment JOIN customer ON customer.customerId = appointment.customerId WHERE appointment.createdBy = '"+userName+"';";
        ResultSet rs = DataConn.Query(query);
        File fileToWrite;
        fileToWrite = new File("AppointmentsByConsultant.html");
        FileWriter fw = new FileWriter(fileToWrite);
        ResultSetToHtmlTable rstht = new ResultSetToHtmlTable();
        rstht.writeTable(rs, fw);
        fw.close();
        Desktop.getDesktop().browse(fileToWrite.toURI());
         
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ResultSet rs = DataConn.Query("SELECT userName FROM user;");
        ObservableList<String> data = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                data.add(rs.getString("userName"));    
            }
            consultantList.setItems(data);
            
        } catch (SQLException e){
            System.err.println(e);
        }
        
//        runReport.setOnAction((ActionEvent event) -> {
//
//            try {
//                runReport();
//            } catch (Exception e) {
//                System.err.println(e);
//            }
//        });
    }
    
}

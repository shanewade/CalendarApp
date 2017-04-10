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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author swade
 */
public class CustomersWithoutAppointmentsController implements Initializable {
    
    @FXML private Button runReport;
    
    @FXML private void runReport()throws IOException, SQLException{
         String query =   "SELECT customerName FROM customer LEFT JOIN appointment ON customer.customerId = appointment.customerId WHERE appointment.customerId IS NULL;";
            ResultSet rs = DataConn.Query(query);
            File fileToWrite;
            fileToWrite = new File("CustomersWithoutAppointments.html");
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
        // TODO
    }    
    
}

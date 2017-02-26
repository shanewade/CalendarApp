/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author swade
 */
public class CustomerEntryFormController implements Initializable {
    
    @FXML
    private void launchNewCustomer() {
                try {
                Control c = Control.getInstance();
                
                Stage stage = c.getStage();
                c.SetStage(stage);
                c.SetPane("newCust");             
				
                } catch (Exception e1) {
                        System.err.println(e1);
                }
}
    @FXML
    private void launchNewAppointment() {
                try {
                Control c = Control.getInstance();
                
                Stage stage = c.getStage();
                c.SetStage(stage);
                c.SetPane("newAppt");             
				
                } catch (Exception e1) {
                        System.err.println(e1);
                }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author swade
 */
public class MenuController implements Initializable {
     @FXML private Button newCustomer;
     @FXML private Button newAppointment;
     @FXML private Button viewCustomers;
     @FXML private Button viewAppointments;
     @FXML private Button viewCalendar;
     @FXML private Button reportAppointmentsPerMonth;
     @FXML private Button reportScheduleByConsultant;
     @FXML private Button reportCustomersWithNoRecentAppointments;
     
     
     
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
    
    private void launchViewAppointments() {
        try {
            Control c = Control.getInstance();
            Stage stage = c.getStage();
            c.SetStage(stage);
            c.SetPane("viewAppt");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
        private void launchConsultantPicker() {
        try {
            Control c = Control.getInstance();
            Stage stage = c.getStage();
            c.SetStage(stage);
            c.SetPane("pickConsultant");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
        
        private void launchMonthPicker() {
        try {
            Control c = Control.getInstance();
            Stage stage = c.getStage();
            c.SetStage(stage);
            c.SetPane("pickMonth");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
        
        private void launchCustomersWOAppts() {
        try {
            Control c = Control.getInstance();
            Stage stage = c.getStage();
            c.SetStage(stage);
            c.SetPane("pickNoCusts");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        newCustomer.setOnAction((ActionEvent event) -> {

            try {
            
                launchNewCustomer();
            } catch (Exception e) {
                System.err.println(e);
            }
        });
        
        newAppointment.setOnAction((ActionEvent event) -> {

            try {
            
                launchNewAppointment();
            } catch (Exception e) {
                System.err.println(e);
            }
        });
        
        viewAppointments.setOnAction((ActionEvent event) -> {
           
           try {
               launchViewAppointments();
           } catch (Exception e) {
               e.printStackTrace();
           }
        });
        
        reportScheduleByConsultant.setOnAction((ActionEvent event) -> {
            try {
               launchConsultantPicker();
            } catch (Exception e) {
               e.printStackTrace();
            }
        });
        
        reportAppointmentsPerMonth.setOnAction((ActionEvent event) -> {
            try {
               launchMonthPicker();
            } catch (Exception e) {
               e.printStackTrace();
            }
        });
        
        reportCustomersWithNoRecentAppointments.setOnAction((ActionEvent event) -> {
            try {
               launchCustomersWOAppts();
            } catch (Exception e) {
               e.printStackTrace();
            }
        });
    
    
    
    }
}

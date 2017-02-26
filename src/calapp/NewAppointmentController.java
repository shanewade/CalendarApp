/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calapp;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author swade
 */
public class NewAppointmentController implements Initializable {

//    @FXML private TextField custName;
    @FXML private TextField apptTitle;
    @FXML private TextField apptContact;
    @FXML private TextField apptCustomer;
    @FXML private TextArea apptDescript;
    @FXML private TextField apptWebsite;
    @FXML private DatePicker apptStartDate;
    @FXML private DatePicker apptEndDate;
    @FXML private ComboBox timeSelectorStart;
    @FXML private ComboBox timeSelectorEnd;
    
    private Date startDateTime;
    private Date endDateTime;
    
    
    
    ObservableList<String> times = observableArrayList(
            "00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30"
          , "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30"
          , "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30"
          , "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30"
          , "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30");
    
    
    @FXML private void validate() throws Exception{
        String title = apptTitle.getText();
        String contact = apptContact.getText();
        String customer = apptCustomer.getText();
        String decription = apptDescript.getText();
        String website = apptWebsite.getText();
        String startDate = apptStartDate.getValue()+ " " +timeSelectorStart.getValue();
        String endDate = apptEndDate.getValue() + " " + timeSelectorEnd.getValue();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm");
        Date resultStart = df.parse(startDate);
        Date resultEnd = df.parse(endDate);



        
        if (title.length() < 2) {
            alert("Title is not long enough.  Min character length is 2");
        }
        
        if (contact.length() < 2 ) {
            alert("Contact is not long enough.  Min character length is 3");
        }
        
        if (customer.length() < 2) {
            alert("Customer is not long enough.  Min character length is 3");
        }
        
        if (decription.length() < 10 ) {
            alert("Decription is not long enough.  Min character length is 10");
        }
        
        if (website.length() < 3) {
            alert("Website is not long enough.  Min character length is 3");
        }
        if (resultStart.after(resultEnd)) {
            alert("Your appointment can not have an end date that is before the start date");
            
        }
        alert("Object saved!!");

        
    }
    
    private void alert(String context){
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Save New Appointment");
            alert.setHeaderText("Validation Error");
            alert.setContentText(context);
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                System.err.println(context);
                }
                    
            });
    }
    @FXML private void cancel(){
        Control control = Control.getInstance();
        control.SetPane("custEntry");
    }
    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        timeSelectorStart.setItems(times);
        timeSelectorEnd.setItems(times);
        
    }    
    
    
    
    
}

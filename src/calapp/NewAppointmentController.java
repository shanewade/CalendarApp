/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calapp;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    @FXML private ComboBox apptCustomer;
    @FXML private TextArea apptDescript;
    @FXML private TextField apptWebsite;
    @FXML private TextField apptLocation;
    @FXML private DatePicker apptStartDate;
    @FXML private DatePicker apptEndDate;
    @FXML private ComboBox timeSelectorStart;
    @FXML private ComboBox timeSelectorEnd;
    @FXML private Button save;
    
    private ZonedDateTime startDateTime;
    private ZonedDateTime endDateTime;
    
    
    
    ObservableList<String> times = observableArrayList(
            "00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30"
          , "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30"
          , "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30"
          , "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30"
          , "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30");
    
    
    
    
    @FXML private void saveAppointment() throws Exception{
        if (validate()) {


            String title = apptTitle.getText();
            String contact = apptContact.getText();
            String customer = (String) apptCustomer.getValue();
            String decription = apptDescript.getText();
            String website = apptWebsite.getText();
            String location = apptLocation.getText();
            String startDate = apptStartDate.getValue()+ " " +timeSelectorStart.getValue();
            String endDate = apptEndDate.getValue() + " " + timeSelectorEnd.getValue();
            int newApptID = getNextApptID();
            int custID = Customer.getCustIDWithName(customer);
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm");
            
            System.err.println(startDate);
            System.err.println(endDate);
            
            LocalDateTime ldtStart = LocalDateTime.parse(startDate, df);
            LocalDateTime ldtEnd = LocalDateTime.parse(endDate, df);
            
            ZoneId zid = ZoneId.systemDefault();
            
            ZonedDateTime zdtStart = ldtStart.atZone(zid);
            ZonedDateTime zdtEnd = ldtEnd.atZone(zid);
            
            ZonedDateTime utcStart = zdtStart.withZoneSameInstant(ZoneId.of("UTC"));
            ZonedDateTime utcEnd = zdtEnd.withZoneSameInstant(ZoneId.of("UTC"));
            
            LocalDateTime utcLDTStart = utcStart.toLocalDateTime();
            LocalDateTime utcLDTEnd = utcEnd.toLocalDateTime();
            
            String utcStringStart = utcLDTStart.toString();
            String utcStringEnd = utcLDTEnd.toString();
            
            
            GlobalDataStore gsd = GlobalDataStore.getInstance();
            String loggedInUser = gsd.getLoggedInUser();
            Calendar calendar = Calendar.getInstance();
            
            Timestamp ts = Timestamp.from(Instant.now());

            
            String query = "INSERT INTO `appointment` (`appointmentID`, `customerID`, `title`, `description`, `contact`, `url`, `location`, `start`, `end`, `createDate`, `createdBy`, `lastUpdateBy`) "
                         + "VALUES ('" + newApptID +"', '"+ custID +"', '"+title+"', '"+decription+"', '" +contact +"', '"+website+"', '"+location+"', '"+utcStringStart+"', '"+utcStringEnd+"', '"+ts+"', '"+loggedInUser+"', '"+loggedInUser+"');";
            System.err.println(query);
            Boolean updated = DataConn.Update(query);

            Control.mainscreen();
   
        }
    }
    
    @FXML private Boolean validate() throws Exception{
        String title = apptTitle.getText();
        String contact = apptContact.getText();
        String customer = (String) apptCustomer.getValue();
        String decription = apptDescript.getText();
        String website = apptWebsite.getText();
        String location = apptLocation.getText();
        String startDate = apptStartDate.getValue()+ " " +timeSelectorStart.getValue();
        String endDate = apptEndDate.getValue() + " " + timeSelectorEnd.getValue();

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm");
        LocalDateTime resultStart = LocalDateTime.parse(startDate, df);
        LocalDateTime resultEnd = LocalDateTime.parse(endDate, df);
       
        if (title.length() < 2) {
            alert("Title is not long enough.  Min character length is 2");
            return false;
        }
        
        if (contact.length() < 2 ) {
            alert("Contact is not long enough.  Min character length is 3");
            return false;
        }
        
        if (customer == null) {
            alert("You must select a customer");
            return false;
        }
        
        
        if (decription.length() < 10 ) {
            alert("Decription is not long enough.  Min character length is 10");
            return false;
        }
        
        if (website.length() < 3) {
            alert("Website is not long enough.  Min character length is 3");
            return false;
        }
        if (location.length() < 3) {
            alert("Location is not long enough.  Min character length is 3");
        }
        if (resultStart.isAfter(resultEnd)) {
            alert("Your appointment can not have an end date that is before the start date");
            return false;
            
        } else {

        return true;
        }
        
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
        Control.mainscreen();
    }
    
    public static int getNextApptID() {
        ResultSet rs = DataConn.Query("SELECT MAX(appointmentID) AS id FROM appointment;");
        try {
            rs.next();
            int nextID = rs.getInt("id");
            nextID++;
            return nextID;
        }
        catch (SQLException e) {
            System.err.println(e);
        }
        return 0;
    }
   
    /**
     * Initializes the controller class.
     */
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        timeSelectorStart.setItems(times);
        timeSelectorEnd.setItems(times);
        
        ResultSet rs = DataConn.Query("SELECT customerName from customer WHERE active = 1;");
        ObservableList<String> data = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                data.add(rs.getString("customerName"));    
            }
            apptCustomer.setItems(data);
            
        } catch (SQLException e){
            System.err.println(e);
        }
        
        save.setOnAction((ActionEvent event) -> {

            try {
            
                saveAppointment();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        
        
        
        
        
        );
    }    
    
}

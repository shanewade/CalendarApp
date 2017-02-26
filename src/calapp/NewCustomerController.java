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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author swade
 */
public class NewCustomerController implements Initializable {
    
    @FXML private TextField custName;
    @FXML private TextField custAddress;
    @FXML private TextField custPhone;
    @FXML private TextField custCity;
    @FXML private TextField custZip;
    @FXML private TextField custCountry;
    
    @FXML private void saveNewCust(){
//       
        if (validate()) {
        Customer newCust = new Customer (Customer.getNextCustID(), custName.getText(), Address.getCurrentOrGetNextAddressID(
                                            custAddress.getText()), City.getCurrentOrGetNextCityID(custCity.getText())
                                         ,Country.getCurrentOrGetNextCountryID(custCountry.getText()), custPhone.getText() );
        
        if (Country.getCountryID(custCountry.getText())==0){
            Country.addNewCountry(custCountry.getText());
        }
        
        if (City.getCityID(custCity.getText())==0) {
            City.addNewCity(custCity.getText(), Country.getCountryID(custCountry.getText()));
        }
        
        
        Address.addNewAddress(custAddress.getText(), custPhone.getText(), City.getCurrentOrGetNextCityID(custCity.getText()), 
                              custZip.getText());
        
        Customer.addNewCustomer(newCust.getCustid(newCust), newCust.getCustName(newCust),Address.getCurrentOrGetNextAddressID(
                                custAddress.getText()));
        
        custscreen(); 
        
            }
        
        
        }

    
    @FXML private void custscreen(){
        try {
            Control c = Control.getInstance();
            System.err.println();
            Stage stage = c.getStage();
            c.SetStage(stage);
            c.SetPane("custEntry");             

            } catch (Exception e1) {
                System.err.println(e1);
            }
    }
    
    private boolean validate() {
        String name = custName.getText();
        String address = custAddress.getText();
        String phone = custPhone.getText();
        String city = custCity.getText();
        String zip = custZip.getText();
        String country = custCountry.getText();
        
        if (name.length()<3 ){
            alert("Name is not long enough.  Min character length is 3");
            return false;
        }
        
        if (address.length()< 5){
            alert("Address is not long enough.  Min character length is 5");
            return false;
        }
        if (phone.length()<7){
            alert("Phone number is not long enough.  Min character length is 7");
            return false;
        }
        if (city.length()<2){
            alert("City name is not long enough.  Min character length is 2");
            return false;
        }
        if (zip.length()<5){
            alert("Zip is not long enough.  Min character length is 5");
            return false;
        }
        if (country.length()<2){
            alert("Country name is not long enough.  Min character length is 2");
            return false;
        }
        
        return true;
    }
    
    private void alert(String context){
        Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Save New Customer");
            alert.setHeaderText("Validation Error");
            alert.setContentText(context);
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                System.err.println(context);
                }
                    
            });
}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

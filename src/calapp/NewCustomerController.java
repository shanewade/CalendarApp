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
import javafx.scene.control.TextField;

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
        Customer newCust = new Customer (Customer.getNextCustID(), custName.getText(), Address.getCurrentOrGetNextAddressID(custAddress.getText()), City.getCurrentOrGetNextCityID(custCity.getText())
                                         ,Country.getCurrentOrGetNextCountryID(custCountry.getText()), custPhone.getText() );
        
        if (Country.getCountryID(custCountry.getText())==0){
            Country.addNewCountry(custCountry.getText());
        }
        
        if (City.getCityID(custCity.getText())==0) {
            City.addNewCity(custCity.getText(), Country.getCountryID(custCountry.getText()));
        }
        
        
        Address.addNewAddress(custAddress.getText(), custPhone.getText(), City.getCurrentOrGetNextCityID(custCity.getText()), custZip.getText());
        
        Customer.addNewCustomer(newCust.getCustid(newCust), newCust.getCustName(newCust),Address.getCurrentOrGetNextAddressID(custAddress.getText()));
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

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
        Customer newCust = new Customer (Customer.getNextCustID(), custName.getText(), 1, Customer.getCurrentOrGetNextCityID(custCity.getText()),Customer.getCurrentOrGetNextCountryID(custCountry.getText()), 1);
        Customer.addNewCustomer(newCust.getCustid(newCust), newCust.getCustName(newCust), 1);
        //(int id,String name, int addr, int city, int country, int phone )
        Customer.printCustValues(newCust);
//        System.err.println(custName.getText());
//        System.err.println(custAddress.getText());
//        System.err.println(custPhone.getText());
//        System.err.println(custCity.getText());
//        System.err.println(custZip.getText());
//        System.err.println(custCountry.getText());
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author swade
 */
public class SignInController implements Initializable {

    
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Text status;
    
    ResourceBundle rb = ResourceBundle.getBundle("calapp.ApplicationResources");
    
    @FXML
    private void validateSignin(ActionEvent ae) {
        String user = username.getText();
        String pass = password.getText();
        if (ValidateLogin.checkPassword(user, pass)){
            System.err.println("Validated Signin");
            try {
                Control c = Control.getInstance();
                
                Stage stage = c.getStage();
                c.SetStage(stage);
                c.SetPane("custEntry");             
				
                } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        System.err.println(e1);
                }
        }if (pass.equals("")) {
            //System.err.println(status.getText());
            System.err.println("Invalid Signin");
            status.setText(rb.getString("nopass"));
        }else{
            System.err.println(password.getText());
            status.setText(rb.getString("badpass"));
            username.setText("");
            password.setText(""); 
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}

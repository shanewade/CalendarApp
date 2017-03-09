/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calapp;

import java.util.Locale;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ResourceBundle;
import java.util.TimerTask;

/**
 *
 * @author swade
 */
public class AppLauncher extends Application {
        GlobalDataStore gsd = GlobalDataStore.getInstance();
    
     @Override
    public void start(Stage stage) throws Exception {
        
        
        
        
        Locale locale = Locale.getDefault();
        Control c = Control.getInstance();       
        c.SetStage(stage);
        Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"), ResourceBundle.getBundle("calapp.ApplicationResources", locale)); 
        Scene scene = new Scene(root);
        stage.setTitle("Calendar App");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
 
        launch(args);
    }
    
}

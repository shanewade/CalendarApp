/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calapp;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author swade
 */
public class Control {


    private Stage stage;
    private String username;
    private Scene scene;
    private boolean logedin;
    private static final Control Instance = new Control();
   // private MainDocumentController maincontroller;

    private Control() {
        logedin = false;
    }
    public static Control getInstance(){
        return Instance;
    }
//    public void SetMainDocumentController(MainDocumentController mc){
//        maincontroller = mc;
//    }
//    public MainDocumentController getMainDocumentController(){
//        return maincontroller;
//    }
    public void SetStage(Stage stage){
        this.stage = stage;
    }
    public Stage getStage(){
        return stage;
    }
    public void Maximizewindow(){
        stage.setMaximized(true);
    }
    public Scene getScene(){
        return scene;
    }
    public void SetPane(String s){
        try {
            
            if(s.equals("main")){
                Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
                stage.close();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Calendar Application");
                //Maximizewindow();
                stage.show();
            }
            else if (s.equals("custEntry")){
                Parent root = FXMLLoader.load(getClass().getResource("customerEntryForm.fxml"));
                stage.close();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Calendar Application");
                //Maximizewindow();
                stage.show();
            }
            else if (s.equals("newCust")){
                Parent root = FXMLLoader.load(getClass().getResource("NewCustomer.fxml"));
                stage.close();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Calendar Application");
                //Maximizewindow();
                stage.show();
            }
            else if (s.equals("newAppt")) {
                Parent root = FXMLLoader.load(getClass().getResource("NewAppointment.fxml"));
                stage.close();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Calendar Application");
                //Maximizewindow();
                stage.show();
            }
        } catch (IOException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

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
    private final boolean logedin;
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
    
    public static void custscreen(){
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
    public void SetPane(String s){
        try {
            
            switch (s) {
                case "main":
                    {
                        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
                        stage.close();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setTitle("Calendar Application");
                        //Maximizewindow();
                        stage.show();
                        break;
                    }
                case "custEntry":
                    {
                        Parent root = FXMLLoader.load(getClass().getResource("customerEntryForm.fxml"));
                        stage.close();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setTitle("Calendar Application");
                        //Maximizewindow();
                        stage.show();
                        break;
                    }
                case "newCust":
                    {
                        Parent root = FXMLLoader.load(getClass().getResource("NewCustomer.fxml"));
                        stage.close();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setTitle("Calendar Application");
                        //Maximizewindow();
                        stage.show();
                        break;
                    }
                case "newAppt":
                    {
                        Parent root = FXMLLoader.load(getClass().getResource("NewAppointment.fxml"));
                        stage.close();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setTitle("Calendar Application");
                        //Maximizewindow();
                        stage.show();
                        break;
                    }
                default:
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

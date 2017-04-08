/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calapp;

import java.io.IOException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;
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
    
    public static void mainscreen(){
        try {
            Control c = Control.getInstance();
            System.err.println();
            Stage stage = c.getStage();
            c.SetStage(stage);
            c.SetPane("main");             

            } catch (Exception e1) {
                System.err.println(e1);
            }
    }
    
  
	
	public static class LocaleCell extends ListCell<Locale> {
	    @Override
	    public void updateItem(Locale locale, boolean empty) {
	        super.updateItem(locale, empty);
	        setText(locale == null ? null : locale.getDisplayName(locale));
	    }
        }
	
    
    public void SetPane(String s){
        try {
            
            switch (s) {
                case "main":
                    {
                        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
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
                case "viewAppt":
                    {
                        Parent root = FXMLLoader.load(getClass().getResource("ViewAppointments.fxml"));
                        stage.close();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setTitle("Calendar Application");
                        //Maximizewindow();
                        stage.show();
                        break;
                    }
                case "pickConsultant":
                {
                     Parent root = FXMLLoader.load(getClass().getResource("ConsultantPicker.fxml"));
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

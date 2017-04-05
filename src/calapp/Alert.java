/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calapp;

import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 *
 * @author swade
 */
public class Alert {
        public static void alertError(String content, String title, String headerText){
        
        Control c = Control.getInstance();
        Stage stage = c.getStage();
        c.SetStage(stage);
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(content);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
            System.err.println(content);
            }

            });
            }
            
            public static void alertWarning(String content, String title, String headerText){
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
            alert.setTitle(title);
            alert.setHeaderText(headerText);
            alert.setContentText(content);
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                System.err.println(content);
                }
            });   
        }
            
            public static void alertInformation(String content, String title, String headerText){
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(headerText);
            alert.setContentText(content);
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                System.err.println(content);
                } 
                    
            });   
        }
}

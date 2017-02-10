package calapp;

import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main {
	public static void start(Stage primaryStage) throws Exception {

		ResourceBundle rb = ResourceBundle.getBundle("calapp.ApplicationResources");
		//Set up the initial grid
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25,25,25,25));
		
		//Add the grid to the Scene
		Scene scene = new Scene(grid, 300, 275);
		Text scenetitle = new Text(rb.getString("greeting"));
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);
		

//		
		primaryStage.setTitle("Customer Appointments - Main");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
}

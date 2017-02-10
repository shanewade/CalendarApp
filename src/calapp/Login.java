package calapp;


import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class Login extends Application{
public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		ResourceBundle rb = ResourceBundle.getBundle("ApplicationResources");
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
		
		//Setup userName and Password Inputs
		Label userName = new Label(rb.getString("username"));
		grid.add(userName, 0, 1);

		TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Label pw = new Label(rb.getString("password"));
		grid.add(pw, 0, 2);

		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);
		
		Label errorText = new Label();
		grid.add(errorText, 0, 5);
		
		//Add the Sign In button
		Button btn = new Button(rb.getString("login"));
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 4);
		
		//Starts the application
		
		primaryStage.setTitle("Customer Appointments");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		//add action on button press
		

		
		btn.setOnAction(e -> {
			String value1 = String.valueOf(userTextField.getText());
	        String value2 = String.valueOf(pwBox.getText());
			Boolean validlogin = ValidateLogin.checkPassword(value1, value2);
			if (validlogin == true) {
				errorText.setText(rb.getString("goodpass"));
				try {
					Main.start(primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else {
				errorText.setText(rb.getString("badpass"));	
			}
	
		});
		
//  Watching this video  https://www.youtube.com/watch?v=RifjriAxbw8
		
		
	}
	

	
}

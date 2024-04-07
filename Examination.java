
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Examination extends Application{
	public Map<String, TextField> fieldMap2 = new HashMap<>();
	 public void start(Stage primaryStage) {
		 GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			//grid.setPadding(new Insets(25, 25, 25, 25));
			// grid takes in  necessary info
			addFormField(grid, "Patient ID", 0);
			addFormField(grid, "Heart Rate", 1);
			
			
			
			addFormField(grid, "Weight ", 3);
			addFormField(grid, "Height ", 4);
			addFormField(grid, "Blood Pressure ", 5);
			addFormField(grid, "Persscription", 6);

			
			
			
			// alows to save info 
			Button btnSave = new Button("Save");
			btnSave.setStyle("-fx-background-color: lightblue; -fx-padding:10px");
			GridPane.setConstraints(btnSave, 1, 8);
			grid.getChildren().add(btnSave);
			
			// allows to go back to pevious page
			Button btnback = new Button("Back");
			btnback.setStyle("-fx-background-color: lightblue; -fx-padding:10px");
			GridPane.setConstraints(btnback, 1, 9);
			grid.getChildren().add(btnback);
			
			btnback.setOnAction(event -> {
		    	 DoctorUI doctorUI = new DoctorUI();
		        	doctorUI.start(new Stage());
		        	primaryStage.close();
		        });
			
			 StackPane root = new StackPane();

		     Scene scene = new Scene(grid, 600, 500);

		        primaryStage.setTitle("Examinations");
		        primaryStage.setScene(scene);

		        primaryStage.show();





		 }
	 
	 private void addFormField(GridPane grid, String labelText, int rowIndex) {
			System.out.println("Called");
			Label label = new Label(labelText);
			TextField text = new TextField();
			GridPane.setHalignment(label, HPos.LEFT);
			fieldMap2.put(labelText, text);
			grid.add(label, 0, rowIndex);
			grid.add(text, 1, rowIndex);
			
		}
	 
	 public static void main(String[] args) {
	        launch(args);
	    }
	 
	    
	    

}

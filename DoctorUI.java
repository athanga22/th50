
import javafx.application.Application;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.GridPane;
import javafx.geometry.*;
import javafx.scene.control.*;

public class DoctorUI extends Application {
	
	
	private Map<String, TextField> fieldMap = new HashMap<>();
	 public void start(Stage primaryStage) {
	        Label label2 = new Label("Welcome Dr <doctor name> ");
	        
	        
	        GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25, 25, 25, 25));
			
			GridPane.setConstraints(label2, 0, 0, 2, 1);
			GridPane.setHalignment(label2, HPos.CENTER);
			label2.setFont(new Font("Ariel", 20));
			grid.getChildren().add(label2);
			
			
			
			
			Button button1 = new Button("Patient List ");
			button1.setStyle("-fx-background-color: lightblue; -fx-padding:10px");
			button1.setMaxWidth(Double.MAX_VALUE);
			GridPane.setHalignment(button1, HPos.CENTER);
			GridPane.setConstraints(button1 , 0, 1);
	        Button button2 = new Button("Messages");
	        button2.setMaxWidth(Double.MAX_VALUE);
	        button2.setStyle("-fx-background-color: lightblue; -fx-padding:10px");
	        GridPane.setHalignment(button2, HPos.CENTER);
	        GridPane.setConstraints(button2 , 0, 2);
	        Button button3 = new Button("Past Appointments ");
	        button3.setStyle("-fx-background-color: lightblue; -fx-padding:10px");
	        button3.setMaxWidth(Double.MAX_VALUE);
	        GridPane.setHalignment(button3, HPos.CENTER);
	        GridPane.setConstraints(button3 , 0, 3);
	        Button button4 =  new Button("Examination ");
	        button4.setStyle("-fx-background-color: lightblue; -fx-padding:10px");
	        button4.setMaxWidth(Double.MAX_VALUE);
	        GridPane.setHalignment(button4, HPos.CENTER);
	        GridPane.setConstraints(button4 , 0, 4);
	        
	        button1.setOnAction(event -> {
	        	patientList patient = new patientList();
	        	patient.start(new Stage());
	        	primaryStage.close();
	        });
	        
	        button2.setOnAction(event -> {
	        	Messages message = new Messages();
	        	message.start(new Stage());
	        	primaryStage.close();
	        });
	        
	        button3.setOnAction(event -> {
	        	pastAppiontments pastApp = new pastAppiontments();
	        	pastApp.start(new Stage());
	        	primaryStage.close();
	        });
	        
	        button4.setOnAction(event -> {
	        	Examination calender = new Examination();
	        	calender.start(new Stage());
	        	primaryStage.close();
	        });
	        
	        grid.getChildren().add(button1);
	        grid.getChildren().add(button2);
	        grid.getChildren().add(button3);
	        grid.getChildren().add(button4);

	        StackPane root = new StackPane();
	       // root.getChildren().add(label);
	        //root.getChildren().add(button);

	        Scene scene = new Scene(grid, 600, 500);

	        primaryStage.setTitle("Doctor Portal ");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }

	    public static void main(String[] args) {
	        launch(args);
	    }
	    
	    private void addFormField(GridPane grid, String labelText, int rowIndex) {
			Label label = new Label(labelText);
			TextField text = new TextField();
			fieldMap.put(labelText, text);
			grid.add(label, 0, rowIndex);
			grid.add(text, 1, rowIndex);
			
		}

}

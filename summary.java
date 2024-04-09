//package asuHelloWorldJavaFX;

package th50;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class summary   extends Application {
	public void start(Stage primaryStage) {
		 BorderPane border = new BorderPane();
	        
	        // Title label
	     Label titleLabel = new Label(" Apoimtment Summaries");
	     titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
	     titleLabel.setPadding(new Insets(10));
	     titleLabel.setMaxWidth(Double.MAX_VALUE);
	     titleLabel.setAlignment(Pos.CENTER);
	     
	     TextField patientIdField = new TextField();
	     patientIdField.setPromptText("Enter Patient ID...");
	     patientIdField.setMaxWidth(200); // Set a max width for the field
	     
	     Button backButton= new Button("Back ");
	     backButton.setStyle("-fx-background-color: #98FF98; -fx-padding:10px");
	     
	     backButton.setOnAction(event -> {
	    	 patientUI  patientUI     = new patientUI  ();
	    	 patientUI.start(new Stage());
	        	primaryStage.close();
	        });
	     
	     
	     Canvas canvas = new Canvas(700, 700); 
	        GraphicsContext gc = canvas.getGraphicsContext2D();
	        gc.setFill(Color.WHITE);
	        gc.fillRect(100, 100, 500, 500);
	     
	     border.setTop(titleLabel);
	     border.setCenter(canvas);
	     border.setBottom(backButton);
	     
	     border.setStyle("-fx-background-color: F0FFF0;");
	      
	     
	     
	     Scene scene = new Scene(border, 1000, 1000); 
	     
	     

	     primaryStage.setTitle("Summary");
	     primaryStage.setScene(scene);
	     primaryStage.show();   
	        
	        
		
		
	}
	
	public static void main(String[] args) {
      launch(args);
  }

	
	

}

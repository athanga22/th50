package asuHelloWorldJavaFX;
//package th50;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class patientMessages  extends Application {
	public void start(Stage primaryStage) {
		 BorderPane border = new BorderPane();
	        
	        // Title label
	     Label titleLabel = new Label(" Patient  Messages");
	     titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
	     titleLabel.setPadding(new Insets(10));
	     titleLabel.setMaxWidth(Double.MAX_VALUE);
	     titleLabel.setAlignment(Pos.CENTER);
	     
	    
	     
	     
	     Canvas canvas = new Canvas(520, 520); 
	        GraphicsContext gc = canvas.getGraphicsContext2D();
	        gc.setFill(Color.WHITE);
	        gc.fillRect(100, 100, 300, 300);
	     
	     border.setTop(titleLabel);
	     border.setCenter(canvas);
	     
	     border.setStyle("-fx-background-color: F0FFF0;");
	     
	     Scene scene = new Scene(border, 1000, 1000); 
	     
	     VBox buttonsVBox = new VBox(10);
	     buttonsVBox.setAlignment(Pos.CENTER);
	     buttonsVBox.setPadding(new Insets(10, 0, 10, 0));
	     TextField searchField = new TextField();
	     searchField.setPromptText("Send Message...");
	     searchField.setPrefWidth(200);
	     searchField.setMaxWidth(200);
	     Button searchButton = new Button("Send");
	     searchButton.setStyle("-fx-background-color:  #98FF98; -fx-padding:10px");
	     
	     Button BackButton = new Button("Back");
	     BackButton.setStyle("-fx-background-color: #98FF98; -fx-padding:10px");
	     
	     BackButton.setOnAction(event -> {
	    	 patientUI  patientUI     = new patientUI  ();
	    	 patientUI.start(new Stage());
	        	primaryStage.close();
	        });
	     

	     buttonsVBox.getChildren().addAll( searchField, searchButton,  BackButton);
	     border.setBottom(buttonsVBox);

	     primaryStage.setTitle("Messages");
	     primaryStage.setScene(scene);
	     primaryStage.show();   
	        
	        
		
		
	}
	
	public static void main(String[] args) {
       launch(args);
   }

}

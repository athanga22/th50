package asuHelloWorldJavaFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
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

public class pastAppiontments extends Application {
    public void start(Stage primaryStage) {
        BorderPane border = new BorderPane();
        
      
        Label titleLabel = new Label("Patients List");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setPadding(new Insets(10));
        titleLabel.setMaxWidth(Double.MAX_VALUE);
        titleLabel.setAlignment(Pos.CENTER);
        
        
        Canvas canvas = new Canvas(700, 700); 
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(100, 100, 400, 400);
       
        VBox buttonsVBox = new VBox(10);
        buttonsVBox.setAlignment(Pos.CENTER_LEFT);
        buttonsVBox.setPrefWidth(200);
        
        
      
        
        HBox hbox =  new HBox(10);
        hbox.setAlignment(Pos.CENTER);

        Button viewButton = new Button("View Past Appointments");
        viewButton.setStyle("-fx-background-color: lightblue; -fx-padding:10px");
        viewButton.setMinWidth(200.0);
        Button viewButton2 = new Button("View Past Records");
        viewButton2.setStyle("-fx-background-color: lightblue; -fx-padding:10px");
        viewButton2.setMinWidth(200.0);
     
      
        
        TextField searchField = new TextField();
        searchField.setPromptText("Enter Patient ID...");
        searchField.setMinWidth(200.0);
        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-background-color: lightblue; -fx-padding:10px");
        searchButton.setMinWidth(200.0);
        Button BackButton = new Button("Back");
	    BackButton.setStyle("-fx-background-color: lightblue; -fx-padding:10px");
	    BackButton.setMinWidth(200.0);
	    
	    BackButton.setOnAction(event -> {
	    	 DoctorUI doctorUI = new DoctorUI();
	        	doctorUI.start(new Stage());
	        	primaryStage.close();
	        });

        buttonsVBox.getChildren().addAll(viewButton, viewButton2, searchField, searchButton, BackButton);
        hbox.getChildren().addAll(canvas, buttonsVBox);

        // Setting elements in the border pane
        border.setTop(titleLabel);
        border.setCenter(hbox);
        
        
        buttonsVBox.setFillWidth(true);
       
        
        
        // Create the scene with a specific size that can show both the canvas and the buttons
        Scene scene = new Scene(border, 1000, 800); // Adjust the scene size to show everything

        primaryStage.setTitle("Patients List");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

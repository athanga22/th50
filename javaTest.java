package asuHelloWorldJavaFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class javaTest extends Application {
	
	 public void start(Stage primaryStage) {
	        Label label = new Label("Hello, JavaFX!");
	        Button button = new Button("Click me please!");
	        
	        button.setOnAction(e -> label.setText("Button clicked!"));

	        StackPane root = new StackPane();
	        root.getChildren().add(label);
	        root.getChildren().add(button);

	        Scene scene = new Scene(root, 300, 250);

	        primaryStage.setTitle("JavaFX Test");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }

	    public static void main(String[] args) {
	        launch(args);
	    }

}

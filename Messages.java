
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

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Messages extends Application {
	@Override
	public void start(Stage primaryStage) {
		//Present as part of extending an abstract class(Application)
	}
	public static Scene getScene(String username) {
		Label titleLabel = new Label("MESSAGING ACTIONS");
		titleLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

		VBox titleLayout = new VBox();
		titleLayout.setAlignment(Pos.CENTER);
		titleLayout.setPadding(new Insets(10, 20, 20, 20));
		titleLayout.getChildren().add(titleLabel);

		String backgroundStyle = "-fx-background-color: #F0FFF0;";
		String buttonStyle = "-fx-background-color: #98FF98; -fx-text-fill: #005A31; -fx-font-size: 16px; -fx-font-weight: bold;";

		Button sendButton = new Button("SEND MESSAGES");
		Button viewButton = new Button("VIEW MESSAGES");

		configureButtonStyle(sendButton, buttonStyle);
		configureButtonStyle(viewButton, buttonStyle);

		sendButton.setOnAction(actionEvent -> {
			Stage stage = new Stage();
			stage.setTitle("PATIENT INTAKE");
			stage.setScene(SendMessageUI.getScene(username));
			stage.show();
		});

		viewButton.setOnAction(actionEvent -> {
			Stage stage = new Stage();
			stage.setTitle("View Patient Records");
			stage.setScene(ViewMessageUI.getScene(username));
			stage.show();
		});

		VBox layout = new VBox(10);
		layout.getChildren().addAll(titleLabel, sendButton, viewButton);
		layout.setAlignment(Pos.CENTER);
		layout.setPadding(new Insets(20,20,20,20));
		layout.setStyle(backgroundStyle);

		return new Scene(layout, 1000, 800);
	}
	private static void configureButtonStyle(Button button, String style) {
		//Visual style for the buttons
		button.setMinWidth(300);
		button.setMinHeight(100);
		button.setStyle(style);

	}






	public static void main(String[] args) {
        launch(args);
    }

}

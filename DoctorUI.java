
import javafx.application.Application;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.*;
import javafx.scene.control.*;

public class DoctorUI extends Application {

	@Override
	public void start(Stage primaryStage) {
		//Present as part of extending an abstract class(Application)
	}
	public static Scene getScene() {
		Label titleLabel = new Label("HELLO DOCTOR");
		titleLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

		VBox titleLayout = new VBox();
		titleLayout.setAlignment(Pos.CENTER);
		titleLayout.setPadding(new Insets(10, 20, 20, 20));
		titleLayout.getChildren().add(titleLabel);

		String backgroundStyle = "-fx-background-color: #F0FFF0;";
		String buttonStyle = "-fx-background-color: #98FF98; -fx-text-fill: #005A31; -fx-font-size: 16px; -fx-font-weight: bold;";

		Button examButton = new Button("CONDUCT EXAM");
		Button recordButton = new Button("VIEW RECORDS");
		Button messageButton = new Button("MESSAGE");

		configureButtonStyle(examButton, buttonStyle);
		configureButtonStyle(recordButton, buttonStyle);
		configureButtonStyle(messageButton, buttonStyle);

		VBox layout = new VBox(10);
		layout.getChildren().addAll(titleLabel, examButton, recordButton, messageButton);
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

	}
}
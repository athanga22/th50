
import javafx.application.Application;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.geometry.*;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DoctorUI extends Application {

	@Override
	public void start(Stage primaryStage) {
		//Present as part of extending an abstract class(Application)
	}
	public static Scene getScene(String userName) {
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

		examButton.setOnAction(actionEvent -> {
			Stage stage = new Stage();
			stage.setTitle("Physical checkup");
			stage.setScene(ExamUI.getScene());
			stage.show();
		});

		recordButton.setOnAction(actionEvent -> {
			int patientID = getPatientIDByUsername(userName);
			Stage stage = new Stage();
			stage.setTitle("View Patient Records");
			stage.setScene(Records.getScene(userName, patientID));
			stage.show();
		});

		VBox layout = new VBox(10);
		layout.getChildren().addAll(titleLabel, examButton, recordButton, messageButton);
		layout.setAlignment(Pos.CENTER);
		layout.setPadding(new Insets(20,20,20,20));
		layout.setStyle(backgroundStyle);

		return new Scene(layout, 1000, 800);
	}

	public static int getPatientIDByUsername(String username) {
		int patientID = -1; // Default to an empty string if not found
		String query = "SELECT patientID FROM PatientInfo WHERE username = ?";

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/th50", "root", "Frappe22$");
			 PreparedStatement statement = conn.prepareStatement(query)) {

			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				patientID = Integer.parseInt(String.valueOf(rs.getInt("patientID")));
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Handle exception, possibly returning null or a custom error message
		}
		return patientID;
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
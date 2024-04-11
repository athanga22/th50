import javafx.geometry.Insets;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.sql.*;


public class PatientUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        //Present as part of extending an abstract class(Application)
    }
    public static Scene getScene(String username) {
        Label titleLabel = new Label("PATIENT VIEW");
        titleLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

        //layout to place the label at the top of the window with proper spacing
        VBox titleLayout = new VBox();
        titleLayout.setAlignment(Pos.CENTER);
        titleLayout.setPadding(new Insets(10, 20, 20, 20));
        titleLayout.getChildren().add(titleLabel);

        //Styles for button and background to look appeal visually
        String backgroundStyle = "-fx-background-color: #F0FFF0;";
        String buttonStyle = "-fx-background-color: #98FF98; -fx-text-fill: #005A31; -fx-font-size: 16px; -fx-font-weight: bold;";

        Button messageButton = new Button("MESSAGE");
        Button summaryButton = new Button("VIEW SUMMARY");
        Button editButton = new Button("EDIT ACCOUNT DETAILS");


        //Applying the style to buttons
        configureButtonStyle(messageButton, buttonStyle);
        configureButtonStyle(summaryButton, buttonStyle);
        configureButtonStyle(editButton, buttonStyle);


        messageButton.setOnAction(e->{
            Stage stage = new Stage();
            stage.setTitle("Message View");
            stage.setScene(Messages.getScene(username));
            stage.show();
        });

        summaryButton.setOnAction(e->{
            int patientID = getPatientIDByUsername(username);
            Stage stage = new Stage();
            stage.setTitle("Records View");
            stage.setScene(Records.getScene(username, patientID));
            stage.show();
        });

        editButton.setOnAction(e->{
            int patientID = getPatientIDByUsername(username);
            Stage stage = new Stage();
            stage.setTitle("Edit View");
            stage.setScene(EditAccountUI.getScene(patientID));
            stage.show();
        });

        //Layout to organize all the components with proper alignment and style.
        VBox layout = new VBox(10);
        layout.getChildren().addAll(titleLabel, messageButton, summaryButton, editButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20,20,20,20));
        layout.setStyle(backgroundStyle);

        //Create the scene and display
        Scene scene = new Scene(layout, 1000, 800);
        return scene;
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

    private static void showAlert(Alert.AlertType alertType, Stage owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
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

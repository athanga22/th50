
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Records extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //Present as part of extending an abstract class(Application)
    }

    public static Scene getScene(String username, int patientID) {
        Label titleLabel = new Label("Your Visiting Summaries");
        titleLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

        String backgroundStyle = "-fx-background-color: #F0FFF0;";

        Label IDLabel = new Label("PATIENT ID:");
        TextField IDField = new TextField();
        IDLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        Button viewButton = new Button("View");
        viewButton.setStyle("-fx-background-color: #98FF98;");

        HBox IDBox = new HBox(10, IDLabel, IDField, viewButton);
        IDBox.setVisible(username.endsWith("@doc.com") || username.endsWith("@nur.com"));

        TextArea visitSummaries = new TextArea();
        visitSummaries.setEditable(false);
        visitSummaries.setWrapText(true);

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleLabel, IDBox, visitSummaries);
        layout.setStyle(backgroundStyle);

        if (!IDBox.isVisible()) {
            fetchDataAndDisplay(String.valueOf(patientID), visitSummaries);
        } else {
            viewButton.setOnAction(event -> fetchDataAndDisplay(IDField.getText(), visitSummaries));
        }

        return new Scene(layout, 1000, 800);
    }


    //Method to select the fields to display on patient window
    private static void fetchDataAndDisplay(String patientID, TextArea visitSummaries) {
        String query = "SELECT ExamDate, Findings, Prescriptions FROM PhysicalExam WHERE PatientId = ? ORDER BY ExamDate DESC";

        StringBuilder summaries = new StringBuilder();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/th50", "root", "Frappe22$");
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, Integer.parseInt(patientID));
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String examDate = rs.getString("ExamDate");
                String findings = rs.getString("Findings");
                String prescription = rs.getString("Prescriptions");

                summaries.append("Date: ").append(examDate)
                        .append(" - Findings: ").append(findings)
                        .append(" - Prescriptions: ").append(prescription)
                        .append("\n---------------------------------------\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            displayAlert("Record is not available yet.","Your summary is yet to come.", false);
            return;
            // Handle exception
        }

        visitSummaries.setText(summaries.toString());
    }

    //Alert popup to display when any validation fails
    private static void displayAlert(String title, String message, boolean status) {

        Alert alert = new Alert(status ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        if(status) {
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    LoginUI loginPage = new LoginUI();
                    loginPage.start(new Stage());
                }
            });
        }
        else{
            alert.showAndWait();
        }
    }

}

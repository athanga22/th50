import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExamUI extends Application {
    @Override
    public void start(Stage stage) throws Exception {

    }
    public static Scene getScene(){
        Label titleLabel = new Label("PHYSICAL CHECK UP");
        titleLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        String todayDate = dtf.format(localDate);

        VBox titleLayout = new VBox();
        titleLayout.setAlignment(Pos.CENTER);
        titleLayout.setPadding(new Insets(10, 20, 20, 20));
        titleLayout.getChildren().add(titleLabel);

        String backgroundStyle = "-fx-background-color: #F0FFF0;";
        String buttonStyle = "-fx-background-color: #98FF98; -fx-text-fill: #005A31; -fx-font-size: 16px; -fx-font-weight: bold;";

        Label dateLabel = new Label("DATE:");
        TextField dateField = new TextField(todayDate);
        dateField.setEditable(false);
        dateLabel.setMinWidth(125);
        dateLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox dateBox = new HBox(10, dateLabel, dateField);

        Label findingsLabel = new Label("FINDINGS:");
        TextArea findingsField = new TextArea();
        findingsLabel.setMinWidth(125);
        findingsLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox findingsBox = new HBox(10, findingsLabel, findingsField);

        Label prescLabel = new Label("PRESCRIPTION:");
        TextArea prescField = new TextArea();
        prescLabel.setMinWidth(125);
        prescLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox prescBox = new HBox(10, prescLabel, prescField);

        Label IDLabel = new Label("PATIENT ID:");
        TextField IDField = new TextField();
        IDLabel.setMinWidth(125);
        IDLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox IDBox = new HBox(10, IDLabel, IDField);

        Button saveButton = new Button("SAVE");
        saveButton.setStyle(buttonStyle);

        saveButton.setOnAction(actionEvent -> {
            Stage currentStage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            if(!validPatient(Integer.parseInt(IDField.getText()))){
                showAlert(Alert.AlertType.ERROR, currentStage, "Patient ID error", "Patient doesn't exist.");
            }
            if (findingsLabel.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, currentStage, "Findings Error", "Findings cannot be empty.");
            } else {
                showAlert(Alert.AlertType.INFORMATION, currentStage, "Success", "Patient ID: " + IDField.getText() + " exam findings and prescriptions are uploaded.");
                insertDataIntoDatabase(dateField.getText(), findingsField.getText(), prescField.getText(), IDField.getText());

                dateField.setText("");
                findingsField.setText("");
                prescField.setText("");
                IDField.setText("");
            }
        });
        VBox layout = new VBox(10);
        layout.getChildren().addAll(titleLayout, dateBox, findingsBox, prescBox, IDBox, saveButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20,20,20,20));
        layout.setStyle(backgroundStyle);

        return new Scene(layout, 1000, 800);
    }
    private static boolean validPatient(int patientID) {
        //variables for establishing a database connection
        String jdbcURL = "jdbc:mysql://localhost/th50";
        String dbUser = "root";
        String dbPassword = "Frappe22$";

        //Query to execute
        String query = "SELECT COUNT(*) FROM PatientVitals WHERE patientID = ?";

        //try-catch block to deal with the database connection and check if the patient ID exists
        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, patientID);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private static void insertDataIntoDatabase(String date, String findings, String prescription, String patientID) {
        //variables for establishing a database connection
        String jdbcURL = "jdbc:mysql://localhost/th50";
        String dbUser = "root";
        String dbPassword = "Frappe22$";

        //Query to execute
        String sql = "INSERT INTO PhysicalExam (PatientId, Findings, Prescriptions, ExamDate) VALUES (?, ?, ?, ?)";

        //try-catch block to deal with the database connection
        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            //Filling up the details into the query
            statement.setInt(1, Integer.parseInt(patientID));
            statement.setString(2, findings);
            statement.setString(3, prescription);
            statement.setString(4, date);

            //Statement execution
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void showAlert(Alert.AlertType alertType, Stage owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}

import javafx.collections.FXCollections;
import javafx.geometry.Insets;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class NurseUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        //Present as part of extending an abstract class(Application)
    }
    public static Scene getScene() {

        Label titleLabel = new Label("PATIENT INTAKE FORM");
        titleLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

        //layout to place the label at the top of the window with proper spacing
        VBox titleLayout = new VBox();
        titleLayout.setAlignment(Pos.CENTER);
        titleLayout.setPadding(new Insets(10, 20, 20, 20));
        titleLayout.getChildren().add(titleLabel);

        //Styles for button and background to look appeal visually
        String backgroundStyle = "-fx-background-color: #F0FFF0;";
        String buttonStyle = "-fx-background-color: #98FF98; -fx-text-fill: #005A31; -fx-font-size: 16px; -fx-font-weight: bold;";

        //Input fields with their labels and styles
        Label ageQuestionLabel = new Label("IS THE AGE ABOVE 12?");
        ageQuestionLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        ComboBox<String> ageQuestionComboBox = new ComboBox<>(FXCollections.observableArrayList("Yes", "No"));
        ageQuestionComboBox.setMinWidth(125);

        Label ageLabel = new Label("AGE:");
        TextField ageField = new TextField();
        ageLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox ageBox = new HBox(10, ageLabel, ageField);
        ageBox.setVisible(false);

        ageQuestionComboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if ("Yes".equals(newValue)) {
                ageBox.setVisible(true);
            } else {
                ageBox.setVisible(false);
            }
        });
        HBox ageQuestionBox = new HBox(10, ageQuestionLabel, ageQuestionComboBox, ageBox);
        ageQuestionBox.setAlignment(Pos.CENTER_LEFT);

        Label weightLabel = new Label("WEIGHT: (In KG)");
        TextField weightField = new TextField();
        weightLabel.setMinWidth(125);
        weightLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox weightBox = new HBox(10, weightLabel, weightField);

        Label heightLabel = new Label("HEIGHT: (In CM)");
        TextField heightField = new TextField();
        heightLabel.setMinWidth(125);
        heightLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox heightBox = new HBox(10, heightLabel, heightField);

        Label BPLabel = new Label("BLOOD PRESSURE:");
        TextField BPField = new TextField();
        BPLabel.setMinWidth(125);
        BPLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox BPBox = new HBox(10, BPLabel, BPField);

        Label tempLabel = new Label("BODY TEMPERATURE:");
        TextField tempField = new TextField();
        tempLabel.setMinWidth(125);
        tempLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox tempBox = new HBox(10, tempLabel, tempField);

        Label allergyLabel = new Label("ALLERGIES:");
        TextArea allergyField = new TextArea();
        allergyLabel.setMinWidth(140);
        allergyLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox allergyBox = new HBox(10, allergyLabel, allergyField);

        Label concernLabel = new Label("HEALTH CONCERNS:");
        TextArea concernField = new TextArea();
        concernLabel.setMinWidth(125);
        concernLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox concernBox = new HBox(10, concernLabel, concernField);

        Label IDLabel = new Label("PATIENT ID:");
        TextField IDField = new TextField();
        IDField.setEditable(false);
        IDLabel.setMinWidth(125);
        IDLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        Button generate = new Button("GENERATE");
        generate.setStyle(buttonStyle);
        HBox IDBox = new HBox(10, IDLabel, IDField, generate);


        Button saveButton = new Button("SAVE");
        saveButton.setStyle(buttonStyle);

        generate.setOnAction(event ->{
            String patientID = String.valueOf((int) (Math.random() * 100000));
            IDField.setText(patientID);
        });

        saveButton.setOnAction(event -> {
            Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            // Check if the ComboBox is not selected or the IDField is empty as base conditions
            if (ageQuestionComboBox.getSelectionModel().getSelectedItem() == null || IDField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, primaryStage, "Validation Error", "All required fields must be filled out.");
            } else if ("No".equals(ageQuestionComboBox.getSelectionModel().getSelectedItem())) {
                showAlert(Alert.AlertType.ERROR, primaryStage, "Validation Error", "No need of vitals for below 12.");
            } else {
                // If "Yes" is selected but age field is empty or other fields are empty
                if (ageField.getText().isEmpty() || weightField.getText().isEmpty() || heightField.getText().isEmpty() ||
                        BPField.getText().isEmpty() || tempField.getText().isEmpty()) {

                    showAlert(Alert.AlertType.ERROR, primaryStage, "Validation Error", "All fields must be filled out.");
                } else {
                    try {
                        int age = Integer.parseInt(ageField.getText());
                        // Check if age is not above 12

                        if (age <= 12) {
                            showAlert(Alert.AlertType.ERROR, currentStage, "Age Error", "Age must be above 12.");
                        } else {
                            // If all validations pass, insert data and show success message
                            showAlert(Alert.AlertType.INFORMATION, primaryStage, "Success", "Patient ID: " + IDField.getText() + " entry made successfully with vitals!");
                            insertDataIntoDatabase(IDField.getText(), ageField.getText(), weightField.getText(), heightField.getText(), tempField.getText(), BPField.getText(), allergyField.getText(), concernField.getText());
                            // Clear fields after successful insertion
                            ageQuestionComboBox.setValue(null);
                            ageBox.setVisible(false);
                            ageField.setText("");
                            weightField.setText("");
                            heightField.setText("");
                            BPField.setText("");
                            tempField.setText("");
                            IDField.setText("");
                            allergyField.setText("");
                            concernField.setText("");
                        }
                    } catch (NumberFormatException e) {

                        showAlert(Alert.AlertType.ERROR, primaryStage, "Input Error", "Please enter a valid number for age.");
                    }
                }
            }
        });


        VBox layout = new VBox(10);
        layout.getChildren().addAll(titleLayout, ageQuestionBox, weightBox, heightBox, BPBox, tempBox, IDBox, allergyBox, concernBox, saveButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20,20,20,20));
        layout.setStyle(backgroundStyle);

        return new Scene(layout, 1000, 8000);
    }

    private static void insertDataIntoDatabase(String patientID, String age, String wt, String ht, String temp, String bp, String allergy, String concern) {
        //variables for establishing a database connection
        String jdbcURL = "jdbc:mysql://localhost/th50";
        String dbUser = "root";
        String dbPassword = "Frappe22$";

        //Query to execute
        String sql = "INSERT INTO PatientVitals (PatientID, Age , Weight, Height, BodyTemp, BloodPressure, Allergies, HealthConcerns) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        //try-catch block to deal with the database connection
        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            //Filling up the details into the query
            statement.setInt(1, Integer.parseInt(patientID));
            statement.setString(2, age);
            statement.setString(3, wt);
            statement.setString(4, ht);
            statement.setString(5, temp);
            statement.setString(6, bp);
            statement.setString(7, allergy);
            statement.setString(8, concern);

            //Statement execution
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void configureButtonStyle(Button button, String style) {
        button.setMinWidth(300);
        button.setMinHeight(100);
        button.setStyle(style);
    }
    private static void showAlert(Alert.AlertType alertType, Stage owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.sql.*;

public class LoginUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Office Automation System Login");

        Label titleLabel = new Label("LOGIN FORM");
        titleLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

        VBox titleLayout = new VBox();
        titleLayout.setAlignment(Pos.CENTER);
        titleLayout.setPadding(new Insets(30, 20, 20, 20));
        titleLayout.getChildren().add(titleLabel);

        ImageView loginImage = new ImageView("./images/loginUserImage.png"); // Load image from folder
        loginImage.setOpacity(0.8);
        loginImage.setFitWidth(90);
        loginImage.setFitHeight(90);

        String backgroundStyle = "-fx-background-color: #F0FFF0;";
        String buttonStyle = "-fx-background-color: #98FF98; -fx-text-fill: #005A31; -fx-font-size: 16px; -fx-font-weight: bold;";
        String adjButtonStyle = "-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;";

        // Username
        Label userNameLabel = new Label("User Name/Email:");
        TextField userNameField = new TextField();
        userNameField.setPromptText("User name");
        userNameLabel.setMinWidth(100);
        userNameLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox userNameBox = new HBox(10, userNameLabel, userNameField);
        userNameBox.setAlignment(Pos.CENTER);

        Label passwordLabel = new Label("Password:");
        TextField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordLabel.setMinWidth(100);
        passwordLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox passwordBox = new HBox(10, passwordLabel, passwordField);
        passwordBox.setAlignment(Pos.CENTER);

        Button loginBtn = new Button("LOGIN");
        Button forgotPWBtn = new Button("Forgot Password");
        loginBtn.setStyle(adjButtonStyle);
        forgotPWBtn.setStyle(buttonStyle);

        forgotPWBtn.setOnAction(event -> {
            Stage stage = new Stage();
            stage.setTitle("Forgot Password");
            stage.setScene(ResetPasswordUI.getScene());
            stage.show();
        });

        Label newPatient = new Label("New Patient?");
        newPatient.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #3c9ac9;");
        Button createAccountBtn = new Button("Create Account");
        createAccountBtn.setStyle(adjButtonStyle);

        createAccountBtn.setOnAction(event -> {
            Stage stage = new Stage();
            stage.setTitle("Create Account");
            stage.setScene(CreatePatientUI.getScene());
            stage.show();
        });


        // Functionality to validate the login fields
        loginBtn.setOnAction((event) -> {
            String userName = userNameField.getText();
            String password = passwordField.getText();

            // Initial check for empty fields
            if(userName.isEmpty() || password.isEmpty()) {
                displayAlert("Login Failed", userName.isEmpty() ? "UserID can't be empty!" : "Password can't be empty!", false);
                return; // Prevent further execution if either field is empty
            }

            boolean isValid = isValidLogin(userName, password);

            if (!isValid) {
                userNameField.setText("");
                passwordField.setText("");
                displayAlert("Login Failed", "User not found. Please enter valid credentials.", false);
                return; // Prevent further execution if credentials are invalid
            }

            // Proceed with user type check only if login is valid
            displayAlert("Login Successful", "Welcome to the Office Automation System!", true);
            userNameField.setText("");
            passwordField.setText("");

            if(userName.endsWith("@doc.com")){
                Stage stage = new Stage();
                stage.setTitle("Doctor's View");
                stage.setScene(DoctorUI.getScene(userName));
                stage.show();
            }
            else if(userName.endsWith("@nur.com")){
                Stage stage = new Stage();
                stage.setTitle("Nurse' View");
                stage.setScene(NurseUI.getScene(userName));
                stage.show();
            }
            else{
                Stage stage = new Stage();
                stage.setTitle("Patient's View");
                stage.setScene(PatientUI.getScene(userName));
                stage.show();
            }
        });

        VBox loginLayout = new VBox(10);
        loginLayout.getChildren().addAll(titleLayout, loginImage);
        loginLayout.setAlignment(Pos.CENTER);

        HBox loginForgot = new HBox(15);
        loginForgot.getChildren().addAll(loginBtn, forgotPWBtn);
        loginForgot.setAlignment(Pos.CENTER);

        HBox createPatient = new HBox(15);
        createPatient.getChildren().addAll(newPatient, createAccountBtn);
        createPatient.setAlignment(Pos.CENTER);

        VBox fieldLayout = new VBox(10);
        fieldLayout.getChildren().addAll(userNameBox, passwordBox, loginForgot);
        fieldLayout.setAlignment(Pos.CENTER);
        fieldLayout.setPadding(new Insets(20, 20, 20, 20));
        fieldLayout.setStyle("-fx-border-color: green; -fx-border-width: 2px;");

        HBox root = new HBox();
        root.getChildren().add(fieldLayout);
        root.setAlignment(Pos.CENTER);

        VBox overallLayout = new VBox(10);
        overallLayout.getChildren().addAll(loginLayout, root, createPatient);
        overallLayout.setStyle(backgroundStyle);

        Scene scene = new Scene(overallLayout, 600, 500);
        fieldLayout.maxWidthProperty().bind(scene.widthProperty().multiply(0.7));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static boolean isValidLogin(String username, String password) {
        String query = "SELECT COUNT(*) FROM Employee WHERE Username = ? AND Password = ?";
        //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/th50", "root", "Frappe22$"
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/th50", "root", "Frappe22$");
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);

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

    void displayAlert(String title, String message, boolean status) {
        Alert alert = new Alert(status ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

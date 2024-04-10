import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class ResetPasswordUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Office Automation System - Reset Password");

        Label titleLabel = new Label("RESET PASSWORD");
        titleLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

        VBox titleLayout = new VBox();
        titleLayout.setAlignment(Pos.CENTER);
        titleLayout.setPadding(new Insets(30, 20, 20, 20));
        titleLayout.getChildren().add(titleLabel);


        String backgroundStyle = "-fx-background-color: #F0FFF0;";
        String buttonStyle = "-fx-background-color: #98FF98; -fx-text-fill: #005A31; -fx-font-size: 16px; -fx-font-weight: bold;";
        String adjButtonStyle = "-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;";

        // Username
        Label userNameLabel = new Label("User Name:");
        TextField userNameField = new TextField();
        userNameField.setPromptText("User name");
        userNameLabel.setMinWidth(125);
        userNameLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox userNameBox = new HBox(10, userNameLabel, userNameField);
        userNameBox.setAlignment(Pos.CENTER);

        // New Password
        Label newPasswordLabel = new Label("New Password:");
        TextField newPasswordField = new PasswordField();
        newPasswordField.setPromptText("password");
        newPasswordLabel.setMinWidth(120);
        newPasswordLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox newPasswordBox = new HBox(10, newPasswordLabel, newPasswordField);
        newPasswordBox.setAlignment(Pos.CENTER);


        // Confirm Password
        Label confirmPasswordLabel = new Label("Confirm Password:");
        TextField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("password");
        confirmPasswordLabel.setMinWidth(100);
        confirmPasswordLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox confirmPasswordBox = new HBox(10, confirmPasswordLabel, confirmPasswordField);
        confirmPasswordBox.setAlignment(Pos.CENTER);

        // Submit Button
        Button resetBtn = new Button("RESET");
        resetBtn.setStyle(buttonStyle);

        resetBtn.setOnAction(event -> {
            String userName = userNameField.getText();
            String newPassword = newPasswordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            if(!Objects.equals(newPassword, confirmPassword)) {
                displayAlert("Password Mismatch", "Try to reset again", false);
            }

            if(userName.isEmpty()) {
                displayAlert("Login Failed", "UserID can't be empty!", false);
            }
            //TO-DO: Also the username should be valid person in database.

            if(newPassword.isEmpty()) {
                displayAlert("Login Failed", "Password can't be empty!", false);
            }

            boolean isValid = true;

            if (isValid) {
                displayAlert("Password reset successful", "You can login now.", true);
                primaryStage.close();
            } else {
                displayAlert("Login Failed", "User not found. Please enter valid credentials.", false);
            }

        });

        VBox resetAccountLayout = new VBox(20);
        resetAccountLayout.getChildren().addAll(titleLayout);
        resetAccountLayout.setAlignment(Pos.CENTER);

        VBox fieldLayout = new VBox(10);
        fieldLayout.getChildren().addAll( userNameBox, newPasswordBox, confirmPasswordBox, resetBtn);
        fieldLayout.setAlignment(Pos.CENTER);
        fieldLayout.setPadding(new Insets(20, 20, 20, 20));
        VBox.setMargin(newPasswordBox, new Insets(30, 0, 0, 0));
        VBox.setMargin(resetBtn, new Insets(30, 0, 0, 0));

        HBox root = new HBox();
        root.getChildren().add(fieldLayout);
        root.setAlignment(Pos.CENTER);

        VBox overallLayout = new VBox(10);
        overallLayout.getChildren().addAll(resetAccountLayout, root);
        overallLayout.setStyle(backgroundStyle);

        Scene scene = new Scene(overallLayout, 600, 500);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    void displayAlert(String title, String message, boolean status) {

        Alert alert = new Alert(status ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                LoginUI loginPage = new LoginUI();
                loginPage.start(new Stage());
            }
        });
    }
}

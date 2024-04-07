import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class LoginUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Label titleLabel = new Label("Login");
        titleLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

        VBox titleLayout = new VBox();
        titleLayout.setAlignment(Pos.CENTER);
        titleLayout.setPadding(new Insets(10, 20, 20, 20));
        titleLayout.getChildren().add(titleLabel);

        String backgroundStyle = "-fx-background-color: #F0FFF0;";
        String buttonStyle = "-fx-background-color: #98FF98; -fx-text-fill: #005A31; -fx-font-size: 16px; -fx-font-weight: bold;";

        Label emailLabel = new Label("EMAIL:");
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailLabel.setMinWidth(100);
        emailLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox emailBox = new HBox(10, emailLabel, emailField);
        emailBox.setAlignment(Pos.CENTER);

        Label passwordLabel = new Label("PASSWORD:");
        TextField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordLabel.setMinWidth(100);
        passwordLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox passwordBox = new HBox(10, passwordLabel, passwordField);
        passwordBox.setAlignment(Pos.CENTER);

        Button loginBtn = new Button("Login");
        loginBtn.setStyle(buttonStyle);

        VBox loginLayout = new VBox(10);
        loginLayout.getChildren().addAll(titleLayout, emailBox, passwordBox, loginBtn);
        loginLayout.setStyle(backgroundStyle);
        loginLayout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(loginLayout, 600, 500);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import static com.sun.webkit.graphics.WCImage.getImage;

public class LoginUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
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

        Button loginBtn = new Button("LOGIN");
        loginBtn.setStyle(buttonStyle);

        VBox loginLayout = new VBox(10);
        loginLayout.getChildren().addAll(titleLayout, loginImage);

        loginLayout.setAlignment(Pos.CENTER);

        VBox fieldLayout = new VBox(10);
        fieldLayout.getChildren().addAll(emailBox, passwordBox, loginBtn);
        fieldLayout.setAlignment(Pos.CENTER);
        fieldLayout.setPadding(new Insets(20, 20, 20, 20));
        fieldLayout.setStyle("-fx-border-color: green; -fx-border-width: 2px;");

        HBox root = new HBox();
        root.getChildren().add(fieldLayout);
        root.setAlignment(Pos.CENTER);

        VBox overallLayout = new VBox(10);
        overallLayout.getChildren().addAll(loginLayout, root);
        overallLayout.setStyle(backgroundStyle);

        Scene scene = new Scene(overallLayout, 600, 500);
        fieldLayout.maxWidthProperty().bind(scene.widthProperty().multiply(0.7));
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class CreatePatientUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Office Automation System - Create Account");

        Label titleLabel = new Label("CREATE ACCOUNT");
        titleLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

        VBox titleLayout = new VBox();
        titleLayout.setAlignment(Pos.CENTER);
        titleLayout.setPadding(new Insets(30, 20, 20, 20));
        titleLayout.getChildren().add(titleLabel);

        ImageView loginImage = new ImageView("./images/loginUserImage.png"); // Load image from folder
        loginImage.setOpacity(0.8);
        loginImage.setFitWidth(100);
        loginImage.setFitHeight(100);

        String backgroundStyle = "-fx-background-color: #F0FFF0;";
        String buttonStyle = "-fx-background-color: #98FF98; -fx-text-fill: #005A31; -fx-font-size: 16px; -fx-font-weight: bold;";
        String adjButtonStyle = "-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;";

        // Patient ID
        Label patientIDLabel = new Label("Patient ID:");
        TextField patientIDField = new TextField();
        patientIDField.setPromptText("patient ID");
        patientIDLabel.setMinWidth(100);
        patientIDLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox patientIDBox = new HBox(10, patientIDLabel, patientIDField);
        patientIDBox.setAlignment(Pos.CENTER);

        // First Name
        Label firstNameLabel = new Label("First Name:");
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("first name");
        firstNameLabel.setMinWidth(100);
        firstNameLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox firstNameBox = new HBox(10, firstNameLabel, firstNameField);
        firstNameBox.setAlignment(Pos.CENTER);

        // Last Name
        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("last name");
        lastNameLabel.setMinWidth(100);
        lastNameLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox lastNameBox = new HBox(10, lastNameLabel, lastNameField);
        lastNameBox.setAlignment(Pos.CENTER);

        // Date of Birth
        Label dobLabel = new Label("Date of Birth:");
        TextField dobField = new TextField();
        dobField.setPromptText("dd-mm-yyyy");
        dobLabel.setMinWidth(100);
        dobLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox dobBox = new HBox(10, dobLabel, dobField);
        dobBox.setAlignment(Pos.CENTER);

        // Contact
        Label contactLabel = new Label("Contact:");
        TextField contactField = new TextField();
        contactField.setPromptText("contact");
        contactLabel.setMinWidth(100);
        contactLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox contactBox = new HBox(10, contactLabel, contactField);
        contactBox.setAlignment(Pos.CENTER);

        // Insurance ID
        Label insIDLabel = new Label("Insurance ID:");
        TextField insIDField = new TextField();
        insIDField.setPromptText("insurance id");
        insIDLabel.setMinWidth(100);
        insIDLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox insIDBox = new HBox(10, insIDLabel, insIDField);
        insIDBox.setAlignment(Pos.CENTER);

        // Username
        Label userNameLabel = new Label("User Name:");
        TextField userNameField = new TextField();
        userNameField.setPromptText("user name");
        userNameLabel.setMinWidth(100);
        userNameLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox userNameBox = new HBox(10, userNameLabel, userNameField);
        userNameBox.setAlignment(Pos.CENTER);

        // Password
        Label passwordLabel = new Label("Password:");
        TextField passwordField = new PasswordField();
        passwordField.setPromptText("password");
        passwordLabel.setMinWidth(100);
        passwordLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox passwordBox = new HBox(10, passwordLabel, passwordField);
        passwordBox.setAlignment(Pos.CENTER);

        boolean isValid = true;
        // TO-DO: Handle all the errors and update isValid.

        // Submit Button
        Button submitBtn = new Button("SUBMIT");
        submitBtn.setStyle(buttonStyle);

        submitBtn.setOnAction(event -> {
            if(isValid) {
                displayAlert("Account creation successful", "Account created successfully. You can login now.", true);
            }
            else {
                displayAlert("Account creation Failed", "Enter all valid fields.", false);
            }
            primaryStage.close();
        });

        VBox createAccountLayout = new VBox(20);
        createAccountLayout.getChildren().addAll(titleLayout, loginImage);
        createAccountLayout.setAlignment(Pos.CENTER);

        VBox fieldLayout = new VBox(10);
        fieldLayout.getChildren().addAll(patientIDBox, firstNameBox, lastNameBox, dobBox, contactBox, insIDBox, userNameBox, passwordBox, submitBtn);
        fieldLayout.setAlignment(Pos.CENTER);
        fieldLayout.setPadding(new Insets(20, 20, 20, 20));
        VBox.setMargin(firstNameBox, new Insets(30, 0, 0, 0));
        VBox.setMargin(submitBtn, new Insets(30, 0, 0, 0));

        HBox root = new HBox();
        root.getChildren().add(fieldLayout);
        root.setAlignment(Pos.CENTER);

        VBox overallLayout = new VBox(10);
        overallLayout.getChildren().addAll(createAccountLayout, root);
        overallLayout.setStyle(backgroundStyle);

        Scene scene = new Scene(overallLayout, 900, 800);
        //fieldLayout.maxWidthProperty().bind(scene.widthProperty().multiply(0.7));
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

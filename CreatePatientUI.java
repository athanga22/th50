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

public class CreatePatientUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        //Present as part of extending an abstract class(Application)
    }
    public static Scene getScene() {

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
        dobField.setPromptText("yyyy-mm-dd");
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

        // Submit Button
        Button submitBtn = new Button("SUBMIT");
        submitBtn.setStyle(buttonStyle);

        submitBtn.setOnAction(event -> {
            Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            boolean isValid = validateFields(patientIDField.getText(), firstNameField.getText(), lastNameField.getText(), dobField.getText(), contactField.getText(), insIDField.getText(), userNameField.getText(), passwordField.getText());;
            if(isValid) {
                if(!isValidDateFormat(dobField.getText())){
                    displayAlert("Invalid Date of Birth", "Date of birth must be in the format yyyy-mm-dd.", false);
                }
                else {
                    if(validPatient(Integer.parseInt(patientIDField.getText()))){
                        insertDataIntoDatabaseRecords(patientIDField.getText(), firstNameField.getText(), lastNameField.getText(), contactField.getText(), insIDField.getText(), userNameField.getText(), passwordField.getText(), dobField.getText());
                        insertDataIntoDatabase(userNameField.getText(), passwordField.getText());
                        displayAlert("Account creation successful", "Account created successfully. You can login now.", true);
                    }
                    else{
                        displayAlert("Invalid Patient ID","Entry on this patient does not exist.", false);
                    }

                    currentStage.close();
                }
            }
            else{
                displayAlert("Account creation Failed", "Enter all valid fields.", false);
            }
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
        return scene;
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
    private static void insertDataIntoDatabaseRecords(String patientId, String fn, String ln, String contact, String insId, String username, String password, String dob){
        String jdbcURL = "jdbc:mysql://localhost/th50";
        String dbUser = "root";
        String dbPassword = "Frappe22$";

        //Query to execute
        String sql = "INSERT INTO PatientInfo (PatientId, FirstName, LastName, Contact, InsuranceId, Username, Password, DOB) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        //try-catch block to deal with the database connection
        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            //Filling up the details into the query
            statement.setInt(1, Integer.parseInt(patientId));
            statement.setString(2, fn);
            statement.setString(3, ln);
            statement.setString(4, contact);
            statement.setString(5, insId);
            statement.setString(6, username);
            statement.setString(7, password);
            statement.setString(8, dob);

            //Statement execution
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void insertDataIntoDatabase(String username, String password){
        String jdbcURL = "jdbc:mysql://localhost/th50";
        String dbUser = "root";
        String dbPassword = "Frappe22$";

        //Query to execute
        String sql = "INSERT INTO Employee (Username, Password) VALUES (?, ?)";

        //try-catch block to deal with the database connection
        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            //Filling up the details into the query
            statement.setString(1, username);
            statement.setString(2, password);

            //Statement execution
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

    private static boolean validateFields(String... fields) {
        for (String field : fields) {
            if (field.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidDateFormat(String dob) {
        String dateFormat = "\\d{4}-\\d{2}-\\d{2}"; // Expected format: yyyy-mm-dd

        // Check if the date of birth matches the expected format
        if (!dob.matches(dateFormat)) {
            return false;
        }
        return true;
    }

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

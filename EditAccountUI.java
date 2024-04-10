import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.sql.*;
import java.util.Objects;

public class EditAccountUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        //Present as part of extending an abstract class(Application)
    }
    public static Scene getScene(int patientId) {

        Label titleLabel = new Label("EDIT ACCOUNT");
        titleLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

        VBox titleLayout = new VBox();
        titleLayout.setAlignment(Pos.CENTER);
        titleLayout.setPadding(new Insets(30, 20, 20, 20));
        titleLayout.getChildren().add(titleLabel);


        String backgroundStyle = "-fx-background-color: #F0FFF0;";
        String buttonStyle = "-fx-background-color: #98FF98; -fx-text-fill: #005A31; -fx-font-size: 16px; -fx-font-weight: bold;";
        String adjButtonStyle = "-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;";

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


        // Update Button
        Button updateBtn = new Button("UPDATE");
        updateBtn.setStyle(buttonStyle);

        updateBtn.setOnAction(event -> {
            Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            try {
                updateDatabase(patientId, contactField.getText(), insIDField.getText());
                displayAlert("Details updated successfully", "Details updated successfully.", true);
                currentStage.close();
            } catch(Exception e) {
                displayAlert("Update failure","Update unsuccessful",false);
            }

        });


        VBox fieldLayout = new VBox(10);
        fieldLayout.getChildren().addAll( contactBox, insIDBox,updateBtn);
        fieldLayout.setAlignment(Pos.CENTER);
        fieldLayout.setPadding(new Insets(20, 20, 20, 20));
        VBox.setMargin(updateBtn, new Insets(20, 0, 0, 0));


        VBox overallLayout = new VBox(10);
        overallLayout.getChildren().addAll(titleLayout, fieldLayout);
        overallLayout.setStyle(backgroundStyle);

        Scene scene = new Scene(overallLayout, 600, 500);
        return scene;
    }

    private static void updateDatabase(int patientId, String contact, String insId){
        String query = "UPDATE PatientInfo SET Contact = ?, InsuranceId = ? WHERE PatientId = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/th50", "root", "Frappe22$");
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, contact);
            statement.setString(2, insId);
            statement.setInt(3, patientId);

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Update successful, " + affectedRows + " rows affected.");
            } else {
                System.out.println("Update failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            displayAlert("Update failure", "Update unsuccessful due to an error: " + e.getMessage(), false);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void displayAlert(String title, String message, boolean status) {

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

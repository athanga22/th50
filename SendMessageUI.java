
import java.sql.*;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SendMessageUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //Present as part of extending an abstract class(Application)
    }

    public static Scene getScene(String username) {
        Label titleLabel = new Label("SEND MESSAGE");
        titleLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

        String backgroundStyle = "-fx-background-color: #F0FFF0;";

        Label UserLabel = new Label("Username/email:");
        TextField UserField = new TextField();
        UserLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        Button sendButton = new Button("SEND");
        sendButton.setStyle("-fx-background-color: #98FF98;");

        HBox IDBox = new HBox(10, UserLabel, UserField);

        TextArea messageText = new TextArea();


        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleLabel, IDBox, messageText, sendButton);
        layout.setStyle(backgroundStyle);

        sendButton.setOnAction(actionEvent -> {
            Stage currentStage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            boolean isValid = (UserField.getText().isEmpty() || messageText.getText().isEmpty());
            if(isValid){
                displayAlert("Empty error", "Username and text cannot be empty", false);
            }
            sendMessage(username, UserField.getText(), messageText.getText());
            displayAlert("Message sent","Message sent successfully",true);
            UserField.setText("");
            messageText.setText("");
            currentStage.close();
        });


        return new Scene(layout, 1000, 800);
    }

    public static void sendMessage(String senderUsername, String receiverUsername, String messageText) {
        String query = "INSERT INTO Messages (senderUsername, receiverUsername, messageText) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/th50", "root", "Frappe22$");
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, senderUsername);
            statement.setString(2, receiverUsername);
            statement.setString(3, messageText);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
    }
    //Alert popup to display when any validation fails
    private static void displayAlert(String title, String message, boolean status) {

        Alert alert = new Alert(status ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

}


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewMessageUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //Present as part of extending an abstract class(Application)
    }

    public static Scene getScene(String username) {
        Label titleLabel = new Label("YOUR MESSAGES");
        titleLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

        String backgroundStyle = "-fx-background-color: #F0FFF0;";

        Label IDLabel = new Label("PATIENT ID:");
        TextField IDField = new TextField();
        IDLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        Button viewButton = new Button("View");
        viewButton.setStyle("-fx-background-color: #98FF98;");

        HBox IDBox = new HBox(10, IDLabel, IDField, viewButton);
        IDBox.setVisible(username.endsWith("@doc.com") || username.endsWith("@nur.com"));

        TextArea visitSummaries = new TextArea();
        visitSummaries.setEditable(false);
        visitSummaries.setWrapText(true);

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleLabel, IDBox, visitSummaries);
        layout.setStyle(backgroundStyle);

//        if (!IDBox.isVisible()) {
//            // Fetch and display messages for the logged-in patient
//            visitSummaries.setText(fetchMessages(username));
//        } else {
//            // For doctors or nurses, set the action to fetch and display messages for a specified patient ID
//            viewButton.setOnAction(event -> visitSummaries.setText(fetchMessages(IDField.getText())));
//        }
        visitSummaries.setText(fetchMessages(username));
        return new Scene(layout, 1000, 800);
    }

    public static String fetchMessages(String username) {
        StringBuilder messages = new StringBuilder();
        String query = "SELECT * FROM Messages WHERE senderUsername = ? OR receiverUsername = ? ORDER BY timestamp DESC";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/th50", "root", "Frappe22$");
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, username);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String message = String.format("From: %s - To: %s - %s: %s\n",
                        rs.getString("senderUsername"), rs.getString("receiverUsername"), rs.getTimestamp("timestamp").toString(), rs.getString("messageText"));
                messages.append(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
        return messages.toString();
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

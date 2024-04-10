import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PatientUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        //Present as part of extending an abstract class(Application)
        Scene scene = getScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Scene getScene() {
        Label titleLabel = new Label("HELLO PATIENT");
        titleLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

        VBox titleLayout = new VBox();
        titleLayout.setAlignment(Pos.CENTER);
        titleLayout.setPadding(new Insets(10, 20, 20, 20));
        titleLayout.getChildren().add(titleLabel);
        VBox.setMargin(titleLayout, new Insets(0, 0, 0, 30));

        String backgroundStyle = "-fx-background-color: #F0FFF0;";
        String buttonStyle = "-fx-background-color: #98FF98; -fx-text-fill: #005A31; -fx-font-size: 16px; -fx-font-weight: bold;";

        Button viewSumBtn = new Button("CONDUCT EXAM");
        Button editAccBtn = new Button("VIEW RECORDS");
        Button msgBtn = new Button("MESSAGE");
        Button logoutBtn = new Button("LOGOUT");

        configureButtonStyle(viewSumBtn, buttonStyle);
        configureButtonStyle(editAccBtn, buttonStyle);

        HBox btnSet1 = new HBox(30);
        btnSet1.getChildren().addAll(viewSumBtn, editAccBtn);
        btnSet1.setAlignment(Pos.CENTER);

        configureButtonStyle(msgBtn, buttonStyle);
        configureButtonStyle(logoutBtn, buttonStyle);

        HBox btnSet2 = new HBox(30);
        btnSet2.getChildren().addAll(msgBtn, logoutBtn);
        btnSet2.setAlignment(Pos.CENTER);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(titleLayout, btnSet1, btnSet2);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20,20,20,20));
        layout.setStyle(backgroundStyle);

        return new Scene(layout, 1000, 800);
    }

    private static void configureButtonStyle(Button button, String style) {
        //Visual style for the buttons
        button.setMinWidth(300);
        button.setMinHeight(75);
        button.setStyle(style);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
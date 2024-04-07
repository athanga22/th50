import javafx.geometry.Insets;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class NurseUI extends Application {
    @Override
    public void start(Stage primaryStage) {

        Label titleLabel = new Label("PATIENT INTAKE FORM");
        titleLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

        //layout to place the label at the top of the window with proper spacing
        VBox titleLayout = new VBox();
        titleLayout.setAlignment(Pos.CENTER);
        titleLayout.setPadding(new Insets(10, 20, 20, 20));
        titleLayout.getChildren().add(titleLabel);

        //Styles for button and background to look appeal visually
        String backgroundStyle = "-fx-background-color: #F0FFF0;";
        String buttonStyle = "-fx-background-color: #98FF98; -fx-text-fill: #005A31; -fx-font-size: 16px; -fx-font-weight: bold;";

        //Input fields with their labels and styles
        Label weightLabel = new Label("WEIGHT");
        TextField weightField = new TextField();
        weightLabel.setMinWidth(125);
        weightLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox weightBox = new HBox(10, weightLabel, weightField);

        Label heightLabel = new Label("HEIGHT:");
        TextField heightField = new TextField();
        heightLabel.setMinWidth(125);
        heightLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox heightBox = new HBox(10, heightLabel, heightField);

        Label BPLabel = new Label("BLOOD PRESSURE:");
        TextField BPField = new TextField();
        BPLabel.setMinWidth(125);
        BPLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox BPBox = new HBox(10, BPLabel, BPField);

        Label tempLabel = new Label("BODY TEMPERATURE:");
        TextField tempField = new TextField();
        tempLabel.setMinWidth(125);
        tempLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        HBox tempBox = new HBox(10, tempLabel, tempField);

        Button saveButton = new Button("SAVE");
        saveButton.setStyle(buttonStyle);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(titleLayout, weightBox, heightBox, BPBox, tempBox, saveButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20,20,20,20));
        layout.setStyle(backgroundStyle);

        Scene scene = new Scene(layout, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void configureButtonStyle(Button button, String style) {
        button.setMinWidth(300);
        button.setMinHeight(100);
        button.setStyle(style);

    }

    public static void main(String[] args) {
        launch(args);
    }
}

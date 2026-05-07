package cpe223.karlvince.lab4;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.geometry.Insets;
import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import cpe223.karlvince.lab4.util.Toolbox;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        BorderPane root = new BorderPane();
        TextField inField1 = new TextField();
        Label resultLabel = new Label("0.0");
        TextField inField2 = new TextField();

        // Set text field properties
        inField1.setPromptText("Number 1");
        inField2.setPromptText("Number 2");

        // TextField Container
        HBox tfContainer = new HBox();
        tfContainer.setSpacing(10);
        tfContainer.setPadding(new Insets(10));
        tfContainer.getChildren().addAll(inField1, inField2);
        HBox.setHgrow(inField1, Priority.ALWAYS);
        HBox.setHgrow(inField2, Priority.ALWAYS);

        // Button
        HBox btnContainer = new HBox();
        btnContainer.setSpacing(10);
        btnContainer.setPadding(new Insets(10));
        btnContainer.setAlignment(Pos.CENTER);

        Button plus = new Button("+");
        Button minus = new Button("-");
        Button multiply = new Button("*");
        Button divide = new Button("/");

        // Increase button size
        double unifiedBtnSze = 40.0;
        plus.setMinHeight(unifiedBtnSze);
        plus.setMinWidth(unifiedBtnSze);
        minus.setMinHeight(unifiedBtnSze);
        minus.setMinWidth(unifiedBtnSze);
        multiply.setMinHeight(unifiedBtnSze);
        multiply.setMinWidth(unifiedBtnSze);
        divide.setMinHeight(unifiedBtnSze);
        divide.setMinWidth(unifiedBtnSze);

        // Interaction Container
        VBox interContainer = new VBox();
        interContainer.getChildren().addAll(tfContainer, btnContainer);

        HBox.setHgrow(plus, Priority.ALWAYS);
        HBox.setHgrow(minus, Priority.ALWAYS);
        HBox.setHgrow(multiply, Priority.ALWAYS);
        HBox.setHgrow(divide, Priority.ALWAYS);

        btnContainer.getChildren().addAll(plus, minus, multiply, divide);

        root.setTop(interContainer);
        root.setCenter(resultLabel);

        BorderPane.setAlignment(resultLabel, Pos.CENTER);
        BorderPane.setAlignment(btnContainer, Pos.CENTER);

        // Timeline [Test]
        Timeline errorAnim = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(resultLabel.textFillProperty(), Color.WHITE)),
                new KeyFrame(Duration.millis(200),
                        new KeyValue(resultLabel.textFillProperty(), Color.web("#ff2121"))),
                new KeyFrame(Duration.millis(600),
                        new KeyValue(resultLabel.textFillProperty(), Color.WHITE)));

        // Actions
        plus.setOnAction(e -> {
            Double result = KVCalCore.add(inField1, inField2);
            if (Double.isNaN(result)) {
                resultLabel.setText("ERROR: INVALID INPUT");
                errorAnim.stop();
                errorAnim.playFromStart();
            } else {
                errorAnim.stop();
                resultLabel.setTextFill(Color.WHITE);
                resultLabel.setText(Double.toString(result));
            }
        });

        minus.setOnAction(e -> {
            Double result = KVCalCore.minus(inField1, inField2);
            if (Double.isNaN(result)) {
                resultLabel.setText("ERROR: INVALID INPUT");
                errorAnim.stop();
                errorAnim.playFromStart();
            } else {
                errorAnim.stop();
                resultLabel.setTextFill(Color.WHITE);
                resultLabel.setText(Double.toString(result));
            }
        });

        multiply.setOnAction(e -> {
            Double result = KVCalCore.multiply(inField1, inField2);
            if (Double.isNaN(result)) {
                resultLabel.setText("ERROR: INVALID INPUT");
                errorAnim.stop();
                errorAnim.playFromStart();
            } else {
                errorAnim.stop();
                resultLabel.setTextFill(Color.WHITE);
                resultLabel.setText(Double.toString(result));
            }
        });

        divide.setOnAction(e -> {
            Double result = KVCalCore.divide(inField1, inField2);
            if (Double.isNaN(result)) {
                resultLabel.setText("ERROR: INVALID INPUT");
                errorAnim.stop();
                errorAnim.playFromStart();
            } else {
                errorAnim.stop();
                resultLabel.setTextFill(Color.WHITE);
                resultLabel.setText(Double.toString(result));
            }
        });

        // Scene
        double SCX = Toolbox.Dynamic.screenX(45);
        double SCY = Toolbox.Dynamic.screenY(35);

        scene = new Scene(root, SCX, SCY);
        scene.getStylesheets().add(Toolbox.respath("/cpe223/karlvince/lab4/styles.css"));

        resultLabel.getStyleClass().add("result");
        root.getStyleClass().add("bg");

        stage.setTitle("Lab 4 - Ultra Basic Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
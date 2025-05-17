package transport.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class Main extends Application {
    public void start(Stage primaryStage ) {
        primaryStage.setTitle("My First JavaFX App");

        // Set up a simple root node and scene
        StackPane root = new StackPane();
        Label label = new Label("Hello, JavaFX!");
        root.getChildren().add(label);

        Scene scene = new javafx.scene.Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show(); // Very important: shows the window   
    }
    public static void main(String[] args ) {
        launch ( args );
    }
}
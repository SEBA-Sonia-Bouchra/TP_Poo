package transport.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        Parent root = loader.load();
        
        primaryStage.setTitle("Transport Application");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.setResizable(true); // Allow resizing
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
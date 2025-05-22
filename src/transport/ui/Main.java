package transport.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import transport.core.DataStorage;
import transport.core.GuichetStation;
import javafx.scene.*;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        String saveFile = "State.ser";
        GuichetStation guichet = DataStorage.loadState(saveFile);
        
        // Load the initial Home scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        Parent root = loader.load();
        
        primaryStage.setTitle("ESI-RUN");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.setResizable(true); // Allow resizing
        primaryStage.show();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> { // saves the guichet state when the app is closed
          DataStorage.saveState(guichet, saveFile);
        }));

    }

    public static void main(String[] args) {
        launch(args);
    }
}
package transport.ui;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import transport.core.CartePersonnelle;
import transport.core.DataStorage;
import transport.core.Fonction;
import transport.core.GuichetStation;
import transport.core.Personne;
import transport.core.ReductionImpossibleException;
import transport.core.Ticket;
import transport.core.TitreTransport;
import transport.core.Usager;
import javafx.scene.*;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        String saveFile = "State.ser";
        GuichetStation guichet = DataStorage.loadState(saveFile);
        // -----------------------------------------------
        // Usager usager = new Usager("Lee", "Sonia", LocalDate.of(2006, 3, 1), true);
        // guichet.ajouterUsager(usager);
        // TitreTransport ticket = new Ticket();
        // guichet.acheterTitreTransport(usager, ticket);
        // System.out.println(TitreTransport.getCurrentIdInc());

        // try {
        //     TitreTransport carte = new CartePersonnelle(usager);
        //     guichet.acheterTitreTransport(usager, carte);
        // } catch (ReductionImpossibleException e) {
        //     System.out.println("Erreur lors de la création de la carte personnelle : " + e.getMessage());
        // }
        // DataStorage.saveState(guichet, saveFile);
        // -----------------------------------------------

        // Load the initial Home scene
        // tessssssssssssst
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherTitreTransport.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        Parent root = loader.load();
        
        primaryStage.setTitle("Transport Application");
        primaryStage.setScene(new Scene(root, 800, 500)); // Set initial size
        primaryStage.setResizable(true); // Allow resizing
        primaryStage.show();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {}));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
package transport.ui;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import transport.core.CartePersonnelle;
import transport.core.DataStorage;
import transport.core.Fonction;
import transport.core.GuichetStation;
import transport.core.MoyenTransport;
import transport.core.Personne;
import transport.core.ReductionImpossibleException;
import transport.core.Station;
import transport.core.Suspendable;
import transport.core.Ticket;
import transport.core.TitreTransport;
import transport.core.TypeReclamation;
import transport.core.Usager;
import javafx.scene.*;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        String saveFile = "State.ser";
        GuichetStation guichet = DataStorage.loadState(saveFile);
        // ----------------------------------------------- these are tesssssssssts
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
        
        // Suspendable station = new Station("El Harrach"); 
        // Suspendable bus = new MoyenTransport("AOF8"); 
        // Personne reclamateur = new Usager("Ben", "Ali", LocalDate.of(1990, 5, 10), false);
        
        // guichet.ajouterReclamation(reclamateur, TypeReclamation.SERVICE, station, "Le bus est en retard.");
        // guichet.ajouterReclamation(reclamateur, TypeReclamation.SERVICE, station, "La station est sale.");
        // guichet.ajouterReclamation(reclamateur, TypeReclamation.TECHNIQUE, bus, "Bus tambe en panne.");
        // guichet.ajouterReclamation(reclamateur, TypeReclamation.SERVICE, station, "Comportement inacceptable.");
        // DataStorage.saveState(guichet, saveFile);
        // -----------------------------------------------

        // tessssssssssssst
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReclamations.fxml"));
        
        // Load the initial Home scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        Parent root = loader.load();
        
        primaryStage.setTitle("ESI-RUN");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.setResizable(true); // Allow resizing
        primaryStage.show();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {}));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
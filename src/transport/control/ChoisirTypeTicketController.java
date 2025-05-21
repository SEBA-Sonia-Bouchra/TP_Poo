package transport.control;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import transport.core.CartePersonnelle;
import transport.core.DataStorage;
import transport.core.GuichetStation;
import transport.core.Personne;
import transport.core.Ticket;
import transport.core.TitreTransport;
import transport.core.ReductionImpossibleException;

public class ChoisirTypeTicketController {
    private Personne personne;
    private TitreTransport titre;

    String saveFile = "State.ser";
    GuichetStation guichet = DataStorage.loadState(saveFile);
    @FXML 
    private ChoiceBox<String> choiceBox3;
    @FXML
    private Button backButton;
    @FXML
    private Button ValidateButton;

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }
    public void initialize() {
        setPersonne(personne);
        choiceBox3.getItems().addAll("Ticket", "Carte de navigation personnelle");
        choiceBox3.setValue("Ticket");
        Image img = new Image(getClass().getResourceAsStream("/transport/ui/back-arrow-3095.png"));
        ImageView view = new ImageView(img);
        view.setFitWidth(16);
        view.setFitHeight(16);
        backButton.setGraphic(view);
    }
    @FXML
    private void handleGoBack(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("/transport/ui/AjouterTitreTransport.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 500));
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    @FXML
    private void acheterTitre() {
        String choix = choiceBox3.getValue(); // Récupère le choix sélectionné

        if (choix == null || personne == null) {
            System.out.println("Erreur : choix ou personne non défini.");
            return;
        }

        guichet.ajouterPersonne(personne);

        if (choix.equals("Ticket")) {
        titre = new Ticket();
        } else {
            try {
                titre = new CartePersonnelle(personne);
            } catch (ReductionImpossibleException e) {
                // afficher une alerte si la réduction est impossible
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                alert.setTitle("Erreur de réduction");
                alert.setHeaderText("Réduction non applicable");
                alert.setContentText("Impossible de créer une carte personnelle ");
                alert.showAndWait();
                return; 
            }
        }

        guichet.acheterTitreTransport(personne, titre); 
        DataStorage.saveState(guichet, saveFile);

        try {
            // Naviguer vers la page de confirmation
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/transport/ui/Confirmation.fxml"));
            Parent confirmationRoot = loader.load();

            ConfirmationController controller = loader.getController();
            controller.setTitre(titre);
            System.out.println(titre.getPrix());

            Stage stage = (Stage) ValidateButton.getScene().getWindow();
            stage.setScene(new Scene(confirmationRoot, 600, 400));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

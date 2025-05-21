package transport.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import transport.core.CartePersonnelle;
import transport.core.TitreTransport;

public class ConfirmationController {

    private TitreTransport titre;

    @FXML
    private Label PrixLabel;
    @FXML
    private Button BackButton;
    @FXML
    private GridPane infoGrid;  

    public void setTitre(TitreTransport titre) {
        this.titre = titre;
        updateView(); 
    }

    @FXML
    private void initialize() {
        setTitre(titre);
        if (titre != null) {
            updateView();
        }
    }


    private void updateView() {
        if (titre != null) {
            PrixLabel.setText(String.format("%.2f DA", titre.getPrix()));

            if (titre instanceof CartePersonnelle carte) {
                // Label de gauche (colonne 0) : texte statique
                Label labelGauche = new Label("Réduction:");
                labelGauche.setStyle("-fx-font-size: 18px; -fx-font-family: Verdana;");
                GridPane.setHalignment(labelGauche, javafx.geometry.HPos.RIGHT);

                // Label de droite (colonne 1) : type de carte
                Label labelDroite = new Label(carte.getType().name());
                labelDroite.setStyle("-fx-font-size: 18px; -fx-font-family: Verdana;");

                // Ajouter à la 2e ligne (index 1)
                infoGrid.add(labelGauche, 0, 1);
                infoGrid.add(labelDroite, 1, 1);
            }
        }
    }


    @FXML
    public void HandleBackButton(ActionEvent event) {
        try {
            Parent homeView = FXMLLoader.load(getClass().getResource("/transport/ui/Home.fxml"));
            Scene homeScene = new Scene(homeView, 800, 500); 
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(homeScene);
            stage.setResizable(true); 
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

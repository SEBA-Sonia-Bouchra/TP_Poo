package transport.control;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import transport.core.DataStorage;
import transport.core.GuichetStation;
import transport.core.MoyenTransport;
import transport.core.Personne;
import transport.core.Station;
import transport.core.Suspendable;
import transport.core.TypeReclamation;

public class RemplirInformationsReclamationController {
  String saveFile = "State.ser";
  GuichetStation guichet = DataStorage.loadState(saveFile);
  private Personne personne;
    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox<String> choiceBox;
    
    @FXML 
    private Label dateErrorLabel;
    @FXML
    private DatePicker dateField;
    
    @FXML
    private Button validerReclamationButton;
    
    @FXML
    private TextArea DescriptionBox;
    
    @FXML
    private ChoiceBox<String> choiceBox2;
    
    @FXML
    private TextField NomField;
    @FXML
    private Label NomErreurLabel;

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    @FXML
    public void initialize() {
      choiceBox.getItems().addAll("Technique", "Service");
      choiceBox.setValue("Technique"); 
      choiceBox2.getItems().addAll("Station", "Moyen de transport");
      choiceBox2.setValue("Station"); 
      validerReclamationButton.setOnAction(e -> validateForm());
      Image img = new Image(getClass().getResourceAsStream("/transport/ui/back-arrow-3095.png"));
      ImageView view = new ImageView(img);
      view.setFitWidth(16);
      view.setFitHeight(16);
      backButton.setGraphic(view);
    }

    @FXML
    private void handleGoBack(ActionEvent event) { // the method is private because it's only used internally
    try {
        Parent root = FXMLLoader.load(getClass().getResource("/transport/ui/AjouterReclamation.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 500));
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    private void validateForm() {
    boolean valid = true;
    LocalDate selectedDate = dateField.getValue();
    String nomCible = NomField.getText();
    String typeCibleStr = choiceBox2.getValue();

    // Validate date
    if (selectedDate == null) {
        dateErrorLabel.setText("Veuillez sélectionner une date.");
        dateErrorLabel.setVisible(true);
        dateField.setStyle("-fx-border-color: red;");
        valid = false;
      } else {
        dateErrorLabel.setVisible(false);
        dateField.setStyle("");
      }
      
      // Validate target name
      if (nomCible == null || nomCible.trim().isEmpty()) {
        NomErreurLabel.setText("Veuillez entrer le nom ou l'identifiant de la cible.");
        NomErreurLabel.setVisible(true);
        NomField.setStyle("-fx-border-color: red;");
        valid = false;
      }

    if (!valid) return; // Stop here if invalid

    // Get description and type
    String description = DescriptionBox.getText();
    String typeStr = choiceBox.getValue();
    TypeReclamation type = typeStr.equals("Technique") ? TypeReclamation.TECHNIQUE : TypeReclamation.SERVICE;

    Suspendable cible = null;

if (typeCibleStr.equals("Station")) {
    List<Station> stations = guichet.getStations();
    if (stations != null) {
        for (Station s : stations) {
            if (s.getNom().equalsIgnoreCase(nomCible)) {
                cible = s;
                break;
            }
        }
    }

    if (cible == null) {
        cible = new Station(nomCible);
        guichet.ajouterStation((Station) cible);
    }

} else if (typeCibleStr.equals("Moyen de transport")) {
    List<MoyenTransport> moyens = guichet.getMoyensTransport();
    if (moyens != null) {
        for (MoyenTransport m : moyens) {
            if (m.getIdentifiant().equalsIgnoreCase(nomCible)) {
                cible = m;
                break;
            }
        }
    }

    if (cible == null) {
        cible = new MoyenTransport(nomCible);
        guichet.ajouterMoyenTransport((MoyenTransport) cible);
    }
}

if(cible.estSuspendu()){
  javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
  alert.setTitle("Suspension");
  alert.setHeaderText(null);
  alert.setContentText("⚠ Attention : la cible est actuellement suspendue.");
  alert.showAndWait();
}else{
  // Add reclamation
    guichet.ajouterReclamation(personne, type, (transport.core.Suspendable) cible, description);
    DataStorage.saveState(guichet, "State.ser");
  
  
  // Affiche une alerte de succès
  if (cible.estSuspendu()) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Suspension");
        alert.setHeaderText(null);
        alert.setContentText("Réclamation ajoutée avec succès.\n⚠ Attention : la cible est actuellement suspendue.");
        alert.showAndWait();
      } else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Réclamation ajoutée avec succès !");
        alert.showAndWait();
      }

}
    

    try {
      Parent root = FXMLLoader.load(getClass().getResource("/transport/ui/Home.fxml"));
      Stage stage = (Stage) validerReclamationButton.getScene().getWindow();
      stage.setScene(new Scene(root, 800, 500));
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}



}

package transport.control;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import transport.core.DataStorage;
import transport.core.Employe;
import transport.core.Fonction;
import transport.core.GuichetStation;
import transport.core.Personne;
import transport.core.Usager;

public class AjouterReclamationController {
  String saveFile = "State.ser";
    GuichetStation guichet = DataStorage.loadState(saveFile);
    private Fonction fonction;
    private Personne personne;
    @FXML
    private ChoiceBox<String> choiceBox1;
    @FXML 
    private ChoiceBox<String> choiceBox2;
    @FXML
    private VBox contentBox;
    @FXML
    private Button ajouterReclamationButton;
    @FXML
    private Label nomErrorLabel;
    @FXML
    private TextField nomField;
    @FXML 
    private Label prenomErrorLabel;
    @FXML
    private TextField prenomField;
    @FXML 
    private Label dateErrorLabel;
    @FXML
    private DatePicker dateField;
    @FXML 
    private Label matriculeErrorLabel;
    @FXML
    private TextField matriculeField;
    @FXML
    private Button backButton;
    @FXML
    private CheckBox HandicapBox;

    @FXML
    public void initialize() {
      choiceBox1.getItems().addAll("Usager", "Employe");
      choiceBox1.setValue("Usager"); 
      choiceBox2.getItems().addAll("Agent", "Conducteur");
      choiceBox2.setValue("Agent");
      choiceBox1.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
        contentBox.setVisible("Employe".equals(newVal));});
      contentBox.managedProperty().bind(contentBox.visibleProperty());
      ajouterReclamationButton.setOnAction(e -> validateForm());
      Image img = new Image(getClass().getResourceAsStream("/transport/ui/back-arrow-3095.png"));
      ImageView view = new ImageView(img);
      view.setFitWidth(16);
      view.setFitHeight(16);
      backButton.setGraphic(view);
    }

    @FXML
    private void ajouterReclamation(){ 
    try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/transport/ui/RemplirInformationsReclamation.fxml"));
            Parent destinationRoot = loader.load();
            // Get controller and pass the person
            RemplirInformationsReclamationController controller = loader.getController();
            controller.setPersonne(personne);
            Stage stage = (Stage) ajouterReclamationButton.getScene().getWindow();
            stage.setScene(new Scene(destinationRoot, 700, 500));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleGoBack(ActionEvent event) { // the method is private because it's only used internally
    try {
        Parent root = FXMLLoader.load(getClass().getResource("/transport/ui/Home.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 500));
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    @FXML
    private void handleFunctionChoice(){
      String selectedValue = choiceBox2.getValue();
      if (selectedValue.equals("Conducteur")){
        fonction = Fonction.CONDUCTEUR;
      }else{
        fonction = Fonction.AGENT;
      }
    }

    private void validateForm() {
    boolean valid = true;
    String name = nomField.getText().trim();
    String firstName = prenomField.getText().trim();
    LocalDate selectedDate = dateField.getValue();
    String matricule = matriculeField.getText().trim();
    // validation du nom 
    if (name.isEmpty()) {
      nomErrorLabel.setText("Le nom est requis.");
      nomErrorLabel.setVisible(true);
      nomField.setStyle("-fx-border-color: red; -fx-border-width: 1;");
      valid = false; }
    else if (!name.matches("^[A-Za-zÀ-ÖØ-öø-ÿ\\s'-]+$")) {
      nomErrorLabel.setText("Le nom ne doit contenir que des lettres.");
      nomErrorLabel.setVisible(true);
      nomField.setStyle("-fx-border-color: red;");
      valid = false;
    } else {
        nomErrorLabel.setVisible(false);
        nomField.setStyle(""); 
    }
    // validation du prenom
    if (firstName.isEmpty()) {
      prenomErrorLabel.setText("Le prénom est requis.");
      prenomErrorLabel.setVisible(true);
      prenomField.setStyle("-fx-border-color: red; -fx-border-width: 1;");
      valid = false; }
    else if (!firstName.matches("^[A-Za-zÀ-ÖØ-öø-ÿ\\s'-]+$")) {
      prenomErrorLabel.setText("Le prénom ne doit contenir que des lettres.");
      prenomErrorLabel.setVisible(true);
      prenomField.setStyle("-fx-border-color: red;");
      valid = false;
    } else {
        prenomErrorLabel.setVisible(false);
        prenomField.setStyle(""); 
    }
    // validation de la date de naissance
    if (selectedDate == null) {
    dateErrorLabel.setText("Veuillez sélectionner une date.");
    dateErrorLabel.setVisible(true);
    dateField.setStyle("-fx-border-color: red;");
    valid = false;
    } else {
    dateErrorLabel.setVisible(false);
    dateField.setStyle("");
    }
    // validation du matricule
    if (matricule.isEmpty() && choiceBox1.getValue().equals("Employe")) {
      matriculeErrorLabel.setText("Le matricule est requis.");
      matriculeErrorLabel.setVisible(true);
      matriculeField.setStyle("-fx-border-color: red; -fx-border-width: 1;");
      valid = false;
    } else {
        matriculeErrorLabel.setVisible(false);
        matriculeField.setStyle(""); 
    }
    if (valid) {
      boolean handicap = HandicapBox.isSelected();
      boolean typeEmp = choiceBox1.getValue().equals("Employe");
      if(typeEmp){
        handleFunctionChoice();
        Employe employe = new Employe(name, firstName, selectedDate, handicap, matricule, fonction);
        personne = employe;
        ajouterReclamation();
      }else{
        Usager usager = new Usager(name, firstName, selectedDate, handicap);
        personne = usager;
        ajouterReclamation();
      }
    }
}

}


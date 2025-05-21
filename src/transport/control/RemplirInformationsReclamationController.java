package transport.control;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class RemplirInformationsReclamationController {
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
    public void initialize() {
      choiceBox.getItems().addAll("Technique", "Service");
      choiceBox.setValue("Technique"); 
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
        boolean valid=true;
        LocalDate selectedDate = dateField.getValue();
        if (selectedDate == null) {
        dateErrorLabel.setText("Veuillez sélectionner une date.");
        dateErrorLabel.setVisible(true);
        dateField.setStyle("-fx-border-color: red;");
        valid = false;
        } else {
        dateErrorLabel.setVisible(false);
        dateField.setStyle("");
        }
        if (valid) {
          System.out.println("reclamation ajoutee");
        }
    }
}

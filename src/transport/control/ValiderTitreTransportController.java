package transport.control;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import transport.core.GuichetStation;
import transport.core.TitreNonTrouverException;
import transport.core.TitreNonValideException;
import transport.core.TitreTransport;

public class ValiderTitreTransportController {
    @FXML
    private Button backButton;
    @FXML
    private TextField idField;
    @FXML
    private Label idErrorLabel;
    @FXML
    private Button validerTitreTransportButton;


    @FXML
    public void initialize() {
      Image img = new Image(getClass().getResourceAsStream("/transport/ui/back-arrow-3095.png"));
      ImageView view = new ImageView(img);
      view.setFitWidth(16);
      view.setFitHeight(16);
      backButton.setGraphic(view);
      validerTitreTransportButton.setOnAction(e -> validateForm());
    }

    @FXML
    private void handleGoBack(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("/transport/ui/Home.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 500));
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    private void goToScene(String fxmlPath) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage stage = (Stage) validerTitreTransportButton.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 500));
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    private void validateForm() {
    String id = idField.getText().trim();
    boolean valid = true;

    if (id.isEmpty()) {
        idErrorLabel.setText("L'ID est requis.");
        idErrorLabel.setVisible(true);
        idField.setStyle("-fx-border-color: red;");
        valid = false;
    } else if (!id.matches("\\d+")) {
        idErrorLabel.setText("L'ID doit contenir uniquement des chiffres.");
        idErrorLabel.setVisible(true);
        idField.setStyle("-fx-border-color: red;");
        valid = false;
    } else {
        idErrorLabel.setVisible(false);
        idField.setStyle(""); // reset style
    }

    if (valid) {
        try {
            int ticketId = Integer.parseInt(id);
            TitreTransport titre = GuichetStation.getInstance().chercherTitreParId(ticketId);

            if (titre.estValide(LocalDate.now())) {
                goToScene("/transport/ui/TitreValide.fxml");
            } else {
                goToScene("/transport/ui/TitreNonValide.fxml");
            }
        } catch (TitreNonTrouverException e) {
            goToScene("/transport/ui/TitreNonValide.fxml");
        } catch (TitreNonValideException e) {
            goToScene("/transport/ui/TitreNonValide.fxml");
        }
    }
    }
}

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

public class ChoisirTypeTicketController {
    @FXML 
    private ChoiceBox<String> choiceBox3;
    @FXML
    private Button backButton;
    @FXML
    public void initialize() {
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
}

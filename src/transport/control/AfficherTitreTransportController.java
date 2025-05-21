package transport.control;

import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import transport.core.DataStorage;
import transport.core.GuichetStation;
import transport.core.TitreTransport;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class AfficherTitreTransportController {
    String saveFile = "State.ser";
    GuichetStation guichet = DataStorage.loadState(saveFile);

    @FXML
    private VBox ListeTitres; 

    @FXML
    private Button BackButton;

    public void initialize() {
        List<TitreTransport> titres = guichet.afficherTitreTransport(); 

        for (TitreTransport titre : titres) {
            HBox row = new HBox(70);
            row.setPadding(new Insets(10, 0, 10, 20));

            Label idLabel = new Label(String.valueOf(titre.getId()));
            idLabel.setFont(Font.font("Verdana", 16));
            idLabel.setPrefWidth(120);
            Label dateLabel = new Label(titre.getDateAchat().toString());
            dateLabel.setFont(Font.font("Verdana", 16));
            dateLabel.setPrefWidth(120);
            Label prixLabel = new Label(String.valueOf(titre.getPrix()));
            prixLabel.setFont(Font.font("Verdana", 16));
            prixLabel.setPrefWidth(120);
            Label typeLabel = new Label(titre.getClass().getSimpleName()); 
            typeLabel.setFont(Font.font("Verdana", 16));
            typeLabel.setPrefWidth(140);

            row.getChildren().addAll(idLabel, dateLabel, prixLabel, typeLabel);
            ListeTitres.getChildren().add(row);
        }
    }
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

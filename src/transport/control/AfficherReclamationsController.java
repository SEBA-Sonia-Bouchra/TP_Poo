package transport.control;

import java.util.TreeSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import transport.core.DataStorage;
import transport.core.GuichetStation;
import transport.core.Reclamation;

public class AfficherReclamationsController {
    String saveFile = "State.ser";
    GuichetStation guichet = DataStorage.loadState(saveFile);

    @FXML
    private VBox ListeReclamations; 

    @FXML
    private Button BackButton;

    public void initialize() {
        TreeSet<Reclamation> reclamations = guichet.afficherReclamations(); 

        for (Reclamation reclamation : reclamations) {
            HBox row = new HBox(10);
            row.setPadding(new Insets(10, 0, 10, 20));

            Label idLabel = new Label(String.valueOf(reclamation.getNumero()));
            idLabel.setFont(Font.font("Verdana", 16));
            idLabel.setPrefWidth(80);
            Label dateLabel = new Label(reclamation.getDate().toString());
            dateLabel.setFont(Font.font("Verdana", 16));
            dateLabel.setPrefWidth(120);
            Label entiteLabel = new Label(String.valueOf(reclamation.getCible().getClass().getSimpleName()));
            entiteLabel.setFont(Font.font("Verdana", 16));
            entiteLabel.setPrefWidth(150);
            Label typeLabel = new Label(String.valueOf(reclamation.getType())); 
            typeLabel.setFont(Font.font("Verdana", 16));
            typeLabel.setPrefWidth(130);
            Button viewDescButton = new Button("Voir Description");
            viewDescButton.setFont(Font.font("Verdana", 16));
            viewDescButton.setOnAction(e -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Voir Description");
                alert.setHeaderText("Description pour la Reclamation #" + reclamation.getNumero());
                alert.setContentText(reclamation.getDescription());
                alert.setResizable(false);
                alert.getDialogPane().setPrefSize(400, 300); 
                 alert.getDialogPane().setStyle(
                    "-fx-font-family: 'Verdana';" +
                    "-fx-font-size: 14px;" 
                );
                alert.showAndWait();
            });

            row.getChildren().addAll(idLabel, dateLabel, entiteLabel, typeLabel, viewDescButton);
            ListeReclamations.getChildren().add(row);
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

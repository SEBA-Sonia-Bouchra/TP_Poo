package transport.control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.event.ActionEvent;

public class HomeController {
    @FXML
    private Button AjouterTitreTransportButton;
    @FXML
    private Button AfficherTitresTransportButton;
    @FXML
    private Button AfficherReclamationButton;
    @FXML
    private Button ValiderTitreTransportButton;
    @FXML
    private Button AjouterReclamationButton;
    @FXML
    private void AjouterTitreTransport(ActionEvent event) throws IOException {
         try {
            // Load the destination scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/transport/ui/AjouterTitreTransport.fxml"));
            Parent destinationRoot = loader.load();
            
            // Get the current stage from the button
            Stage stage = (Stage) AjouterTitreTransportButton.getScene().getWindow();
            stage.setScene(new Scene(destinationRoot, 800, 500));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void AfficherTitresTransport(ActionEvent event) throws IOException {
         try {
            // Load the destination scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/transport/ui/AfficherTitreTransport.fxml"));
            Parent destinationRoot = loader.load();
            
            // Get the current stage from the button
            Stage stage = (Stage) AjouterTitreTransportButton.getScene().getWindow();
            stage.setScene(new Scene(destinationRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void AfficherReclamations(ActionEvent event) throws IOException {
         try {
            // Load the destination scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/transport/ui/AfficherReclamations.fxml"));
            Parent destinationRoot = loader.load();
            
            // Get the current stage from the button
            Stage stage = (Stage) AjouterTitreTransportButton.getScene().getWindow();
            stage.setScene(new Scene(destinationRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void ValiderTitreTransport(ActionEvent event) throws IOException {
         try {
            // Load the destination scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/transport/ui/ValiderTitreTransport.fxml"));
            Parent destinationRoot = loader.load();
            
            // Get the current stage from the button
            Stage stage = (Stage) ValiderTitreTransportButton.getScene().getWindow();
            stage.setScene(new Scene(destinationRoot, 800, 500));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void AjouterReclamation(ActionEvent event) throws IOException {
         try {
            // Load the destination scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/transport/ui/AjouterReclamation.fxml"));
            Parent destinationRoot = loader.load();
            
            // Get the current stage from the button
            Stage stage = (Stage) AjouterReclamationButton.getScene().getWindow();
            stage.setScene(new Scene(destinationRoot, 800, 500));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

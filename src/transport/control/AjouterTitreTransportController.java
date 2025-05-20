package transport.control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AjouterTitreTransportController {
    @FXML
    private Label destinationLabel;

    @FXML
    public void initialize() {
        destinationLabel.setText("You have navigated to the destination!");
    }
}

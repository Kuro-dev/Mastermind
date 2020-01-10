package mastermind.game.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class WindowController implements Initializable {
    @FXML
    private ListView listTurnCount;
    @FXML
    private ListView listSubmissions;
    @FXML
    private ListView listResults;
    @FXML
    private HBox mainColourBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

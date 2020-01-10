package mastermind.game.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import mastermind.game.color.ColorField;
import mastermind.game.logic.Mastermind;

import java.net.URL;
import java.util.ResourceBundle;

public class WindowController implements Initializable {
    private final int columns = 15;
    private final int rows = 4;
    private final Mastermind game = new Mastermind(rows, columns);
    @FXML
    private Button buttonSubmit;
    @FXML
    private TableView<ImageView> tableSubmissions;
    @FXML
    private HBox masterColourBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SubmissionHandler.buildDialog(rows);
        buttonSubmit.setOnAction(new SubmissionHandler(rows));
        game.generateNew();
        masterColourBox.getChildren().addAll(ColorField.parseFields(game.getCombination()));
        prepareTableView();
    }

    private void prepareTableView() {
        tableSubmissions.getColumns().add(new TableColumn<>("Turn"));
        for (int i = 0; i < rows; i++) {
            tableSubmissions.getColumns().add(new TableColumn<ImageView, String>("color " + i));
        }
        tableSubmissions.getColumns().add(new TableColumn<>("result"));
    }
}

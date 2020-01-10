package mastermind.game.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import mastermind.game.color.ColorField;
import mastermind.game.gui.resulthandling.ResultConverter;
import mastermind.game.logic.Mastermind;
import mastermind.game.logic.check.Result;

import java.net.URL;
import java.util.ResourceBundle;

public class WindowController implements Initializable {
    private final int columns = 15;
    private final int rows = 4;
    private final Mastermind game = new Mastermind(rows, columns);
    @FXML
    private Label turnLabel;
    @FXML
    private Button buttonSubmit;
    @FXML
    private TableView<ColorField> tableSubmissions;
    @FXML
    private TableView<ResultConverter> tableResults;
    @FXML
    private HBox masterColourBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SubmissionHandler.buildDialog(rows);
        buttonSubmit.setOnAction(new SubmissionHandler(rows, game));
        game.generateNew();
        masterColourBox.getChildren().addAll(ColorField.parseFields(game.getCombination()));
        prepareTableView();
    }

    private void prepareTableView() {
        for (int i = 0; i < rows; i++) {
            TableColumn<ColorField, ImageView> column = new TableColumn<>();
            column.setCellValueFactory(new PropertyValueFactory<>("imageView"));
            tableSubmissions.getColumns().add(column);
        }
        TableColumn<ResultConverter, ImageView> column = new TableColumn<>();
        tableResults.getColumns().add(new TableColumn<>("result"));
    }

    public void update(Result result, ColorField[] fields) {
        buttonSubmit.setDisable(game.isGameOver());
        turnLabel.setText("Turn " + game.getTurn() + " of " + columns);
        ResultConverter converter = new ResultConverter(result);
        converter.write();
        tableSubmissions.getItems().addAll(fields);
        tableResults.getItems().add(converter);
    }
}

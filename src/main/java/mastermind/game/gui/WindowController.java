package mastermind.game.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import mastermind.game.color.ColorCell;
import mastermind.game.color.ColorField;
import mastermind.game.gui.resulthandling.ReflectiveImage;
import mastermind.game.gui.resulthandling.ResultConverter;
import mastermind.game.logic.Mastermind;
import mastermind.game.logic.check.Result;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class WindowController implements Initializable {
    private final int columns = 15;
    private final int rows = 4;
    private final Mastermind game = new Mastermind(rows, columns);
    private final LinkedList<ListView<ReflectiveImage>> colorTables = new LinkedList<>();
    private final ListView<ReflectiveImage> resultTable = new ListView<>();
    @FXML
    private Label turnLabel;
    @FXML
    private Button buttonSubmit;
    @FXML
    private HBox masterColourBox;
    @FXML
    private HBox tableSubmissions;

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
            final ListView<ReflectiveImage> outputView = new ListView<>();
            outputView.setCellFactory(new ColorCell());
            colorTables.add(outputView);
        }
        resultTable.setCellFactory(new ColorCell());
        tableSubmissions.getChildren().addAll(colorTables);
        tableSubmissions.getChildren().add(resultTable);
    }

    public void update(Result result, ColorField[] fields) {
        buttonSubmit.setDisable(game.isGameOver());
        turnLabel.setText("Turn " + game.getTurn() + " of " + columns);
        ResultConverter converter = new ResultConverter(result);
        converter.write();
        for (int i = 0; i < colorTables.size(); i++) {
            colorTables.get(i).getItems().add(fields[i]);
        }
        resultTable.getItems().add(converter);
    }
}

package mastermind.game.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
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

/**
 * Controller class of the main window of the Application.
 */
public class WindowController implements Initializable {
    private static final int LIST_ITEM_HEIGHT = 25;
    private static final int LIST_WIDTH = 40;
    private static final int COLUMNS = 15;
    private static final int ROWS = 4;

    private final Mastermind game = new Mastermind(ROWS, COLUMNS);
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
        initGame();
        buttonSubmit.setOnAction(new SubmissionHandler(game));
        tableSubmissions.getParent().getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                buttonSubmit.fire();
            }
        });
    }

    private void initGame() {
        masterColourBox.setVisible(game.isGameOver());
        SubmissionHandler.buildDialog(ROWS);
        game.generateNew();
        masterColourBox.getChildren().setAll(ColorField.parseFields(game.getCombination()));
        prepareTableView();
    }

    private void prepareTableView() {
        for (int i = 0; i < ROWS; i++) {
            final ListView<ReflectiveImage> outputView = new ListView<>();
            outputView.setPrefWidth(LIST_WIDTH);
            basicSetup(outputView);
            colorTables.add(outputView);
        }
        resultTable.setPrefWidth((LIST_WIDTH * 3));
        basicSetup(resultTable);
        tableSubmissions.getChildren().addAll(colorTables);
        tableSubmissions.getChildren().add(resultTable);
    }

    private void basicSetup(ListView<ReflectiveImage> view) {
        view.setFixedCellSize((LIST_ITEM_HEIGHT));
        view.setCellFactory(new ColorCell());
    }

    /**
     * Updates the GUI for one turn cycle.
     *
     * @param result The result of the submission.
     * @param fields The colorFields of the submission.
     */
    public void update(Result result, ColorField[] fields) {
        masterColourBox.setVisible(game.isGameOver());
        buttonSubmit.setDisable(game.isGameOver());
        turnLabel.setText("Turn " + game.getTurn() + " of " + COLUMNS);
        ResultConverter converter = new ResultConverter(result);
        converter.write();
        for (int i = 0; i < colorTables.size(); i++) {
            colorTables.get(i).getItems().add(fields[i]);
            colorTables.get(i).autosize();
        }
        resultTable.getItems().add(converter);
        resultTable.autosize();
    }

    @FXML
    private void buttonRestartOnAction() {
        buttonSubmit.setDisable(false);
        colorTables.clear();
        tableSubmissions.getChildren().clear();
        resultTable.getItems().clear();
        initGame();
    }
}

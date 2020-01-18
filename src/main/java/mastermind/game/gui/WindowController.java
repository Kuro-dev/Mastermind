package mastermind.game.gui;

import javafx.application.Application;
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
import mastermind.game.logic.pin.Color;
import mastermind.game.logic.pin.Pin;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
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
    }

    private void initGame() {
        masterColourBox.setVisible(game.isGameOver());
        SubmissionHandler.buildDialog(ROWS);
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
        game.generateNew();
        initGame();
    }

    /**
     * Used to parse the given arguments into a fixed combination for debugging.
     * Example: <code>RED RED BLUE BLUE</code>
     * <p>If no arguments are set the game will start with a random combination.</p>
     * <p>If not a matching amount of arguments is set the game will start with a random combination</p>
     *
     * @param params Arguments
     * @implNote <b>Not</b> case sensitive
     */
    public void setCombination(Application.Parameters params) {
        final List<String> args = params.getUnnamed();
        if (args.size() == ROWS) {
            final LinkedList<Pin> list = new LinkedList<>();
            for (String string : args) {
                list.add(new Pin(Color.parseColor(string)));
            }
            game.generateNew(list);
        } else {
            System.out.println("Generating random combination");
            game.generateNew();
        }
    }
}

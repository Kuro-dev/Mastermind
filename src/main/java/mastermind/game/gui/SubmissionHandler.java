package mastermind.game.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import mastermind.Main;
import mastermind.game.color.ColorCell;
import mastermind.game.color.ColorField;
import mastermind.game.gui.dialog.ButtonCell;
import mastermind.game.gui.resulthandling.ReflectiveImage;
import mastermind.game.logic.Mastermind;
import mastermind.game.logic.check.Result;
import mastermind.game.logic.pin.Color;
import mastermind.game.logic.pin.Pin;

import java.util.ArrayList;

/**
 * Gets invoked whenever the submission button is pressed.
 */
public class SubmissionHandler implements EventHandler<ActionEvent> {
    private final Mastermind game;
    private final HBox submissionField;
    private final int rows;
    private final ArrayList<ComboBox<ReflectiveImage>> comboBoxes = new ArrayList<>();

    public SubmissionHandler(Mastermind game, HBox submissionField, int rows) {
        this.game = game;
        this.submissionField = submissionField;
        this.rows = rows;
    }

    private static ArrayList<Pin> getPins() {
        ArrayList<Pin> list = new ArrayList<>();
        for (Color color : Color.values()) {
            list.add(new Pin(color));
        }
        return list;
    }

    public void prepare() {
        for (int i = 0; i < rows; i++) {
            final ComboBox<ReflectiveImage> comboBox = new ComboBox<>();
            final ColorField[] fields = ColorField.parseFields(getPins(), 20, 20);
            final ColorCell factory = new ColorCell();
            comboBox.setCellFactory(factory);
            comboBox.getItems().addAll(fields);
            comboBox.getSelectionModel().selectFirst();
            comboBox.setButtonCell(new ButtonCell());
            submissionField.getChildren().add(comboBox);
            comboBoxes.add(comboBox);
        }
    }

    public void clear() {
        submissionField.getChildren().clear();
        comboBoxes.clear();
    }

    public void reset() {
        clear();
        prepare();
    }

    @SuppressWarnings("SuspiciousToArrayCall")
    public ColorField[] getFields() {
        return comboBoxes.stream().map(box -> box.getSelectionModel().getSelectedItem()).toArray(ColorField[]::new);
    }

    @Override
    public void handle(ActionEvent event) {
        final ColorField[] fields = this.getFields();
        Result result = game.submit(fields);
        result.compare();
        if (result.isTotalMatch()) {
            game.setGameOver(true, "you won!");
        }
        Main.getMainWindow().update(result, fields);
    }
}

package mastermind.game.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Dialog;
import mastermind.game.color.ColorField;
import mastermind.game.gui.dialog.DialogFactory;

import java.util.Arrays;
import java.util.Optional;

/**
 * Gets invoked whenever the submission button is pressed.
 */
public class SubmissionHandler implements EventHandler<ActionEvent> {
    private static final Dialog<ColorField[]> dialog = new Dialog<>();
    private int rows;

    public SubmissionHandler(int rows) {
        this.rows = rows;
    }

    static void buildDialog(int rows) {
        DialogFactory factory = new DialogFactory(rows);
        dialog.setDialogPane(factory.createDialog());
        dialog.setResultConverter(factory);
    }

    @Override
    public void handle(ActionEvent event) {
        final Optional<ColorField[]> result = dialog.showAndWait();
        result.ifPresent(colorFields -> System.err.println("RESULT FOUND " + Arrays.toString(colorFields)));
    }
}

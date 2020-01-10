package mastermind.game.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Dialog;
import mastermind.Main;
import mastermind.game.color.ColorField;
import mastermind.game.gui.dialog.DialogFactory;
import mastermind.game.logic.Mastermind;
import mastermind.game.logic.check.Result;

import java.util.Optional;

/**
 * Gets invoked whenever the submission button is pressed.
 */
public class SubmissionHandler implements EventHandler<ActionEvent> {
    private static final Dialog<ColorField[]> dialog = new Dialog<>();
    private int rows;
    private final Mastermind game;

    public SubmissionHandler(int rows, Mastermind game) {
        this.rows = rows;
        this.game = game;
    }

    static void buildDialog(int rows) {
        DialogFactory factory = new DialogFactory(rows);
        dialog.setDialogPane(factory.createDialog());
        dialog.setResultConverter(factory);
    }

    @Override
    public void handle(ActionEvent event) {
        final Optional<ColorField[]> optional = dialog.showAndWait();
        optional.ifPresent(colorFields -> {
            final ColorField[] fields = optional.get();
            Result result = game.submit(fields);
            result.compare();
            if (result.isTotalMatch()) {
                game.setGameOver(true);
            } else {
                Main.getMainWindow().update(result,fields);
            }
        });
    }
}

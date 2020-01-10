package mastermind.game.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Gets invoked whenever the submission button is pressed.
 */
public class SubmissionHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
        //TODO actually implement this
        System.err.println(event.getEventType());
        System.err.println("Creating a new submission");
    }
}

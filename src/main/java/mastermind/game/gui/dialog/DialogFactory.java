package mastermind.game.gui.dialog;

import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import mastermind.game.color.ColorCell;
import mastermind.game.color.ColorField;
import mastermind.game.gui.resulthandling.ReflectiveImage;
import mastermind.game.logic.pin.Color;
import mastermind.game.logic.pin.Pin;

import java.util.ArrayList;

/**
 * This is the Factory object of the Color picker popup
 * for submitting possible solutions to the game.
 */
@Deprecated
public class DialogFactory implements Callback<ButtonType, ColorField[]> {
    private final int rows;
    private final ArrayList<ComboBox<ReflectiveImage>> comboBoxes = new ArrayList<>();

    public DialogFactory(int rows) {

        this.rows = rows;
    }

    private static ArrayList<Pin> getPins() {
        ArrayList<Pin> list = new ArrayList<>();
        for (Color color : Color.values()) {
            list.add(new Pin(color));
        }
        return list;
    }

    /**
     * @return A prebuilt DialogPane for use in an {@link javafx.scene.control.Dialog Dialog} instance.
     * @implNote Make sure to set the object as the Dialogs
     * {@link javafx.scene.control.Dialog#setResultConverter(Callback) resultConverter}
     */
    public DialogPane createDialog() {
        final DialogPane dialogPane = new DialogPane();
        dialogPane.getButtonTypes().add(ButtonType.CANCEL);
        dialogPane.getButtonTypes().add(ButtonType.APPLY);
        final VBox wrapper = new VBox();
        final HBox box = new HBox();
        wrapper.getChildren().add(box);
        dialogPane.setContent(wrapper);

        for (int i = 0; i < rows; i++) {
            final ComboBox<ReflectiveImage> comboBox = new ComboBox<>();
            final ColorField[] fields = ColorField.parseFields(getPins(), 20, 20);
            final ColorCell factory = new ColorCell();
            comboBox.setCellFactory(factory);
            comboBox.getItems().addAll(fields);
            comboBox.getSelectionModel().selectFirst();
            comboBox.setButtonCell(new ButtonCell());
            box.getChildren().add(comboBox);
            comboBoxes.add(comboBox);
        }
        return dialogPane;
    }


    @Override
    public ColorField[] call(ButtonType buttonType) {
        if (buttonType == ButtonType.APPLY) {
            final ArrayList<ColorField> list = new ArrayList<>();
            for (ComboBox<ReflectiveImage> box : comboBoxes) {
                list.add((ColorField) box.getSelectionModel().getSelectedItem());
            }
            return list.toArray(new ColorField[0]);
        } else {
            return null;
        }
    }
}

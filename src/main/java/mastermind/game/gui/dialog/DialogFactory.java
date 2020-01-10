package mastermind.game.gui.dialog;

import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import mastermind.game.color.ColorCell;
import mastermind.game.color.ColorField;
import mastermind.game.logic.pin.Color;
import mastermind.game.logic.pin.Pin;

import java.util.ArrayList;

public class DialogFactory implements Callback<ButtonType, ColorField[]> {
    private final int rows;
    private final ArrayList<ComboBox<ColorField>> comboBoxes = new ArrayList<>();

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

    public DialogPane createDialog() {
        final DialogPane dialogPane = new DialogPane();
        dialogPane.getButtonTypes().add(ButtonType.CANCEL);
        dialogPane.getButtonTypes().add(ButtonType.APPLY);
        final VBox wrapper = new VBox();
        final HBox box = new HBox();
        wrapper.getChildren().add(box);
        dialogPane.setContent(wrapper);

        for (int i = 0; i < rows; i++) {
            final ComboBox<ColorField> comboBox = new ComboBox<>();
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
            for (ComboBox<ColorField> box : comboBoxes) {
                list.add(box.getSelectionModel().getSelectedItem());
            }
            return list.toArray(new ColorField[0]);
        } else {
            return null;
        }
    }
}

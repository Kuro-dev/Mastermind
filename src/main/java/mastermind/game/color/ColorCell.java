package mastermind.game.color;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class ColorCell extends ListCell<ColorField> implements Callback<ListView<ColorField>, ListCell<ColorField>> {

    @Override
    public ListCell<ColorField> call(ListView<ColorField> colorFieldListView) {
        return new ListCell<>() {
            @Override
            protected void updateItem(ColorField item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setGraphic(null);
                } else {

                    setGraphic(new ImageView(item.getImage()));
                }
            }
        };
    }
}


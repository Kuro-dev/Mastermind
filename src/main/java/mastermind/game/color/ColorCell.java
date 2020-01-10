package mastermind.game.color;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import mastermind.game.gui.resulthandling.ReflectiveImage;

public class ColorCell extends ListCell<ReflectiveImage> implements Callback<ListView<ReflectiveImage>, ListCell<ReflectiveImage>> {

    @Override
    public ListCell<ReflectiveImage> call(ListView<ReflectiveImage> colorFieldListView) {
        return new ListCell<>() {
            @Override
            protected void updateItem(ReflectiveImage item, boolean empty) {
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


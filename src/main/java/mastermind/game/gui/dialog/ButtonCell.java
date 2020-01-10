package mastermind.game.gui.dialog;

import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mastermind.game.color.ColorField;
import mastermind.game.gui.resulthandling.ReflectiveImage;

public class ButtonCell extends ListCell<ReflectiveImage> {
    private ColorField selectedItem;

    @Override
    public Node getStyleableNode() {
        return new ImageView(selectedItem.getImage());
    }

    @Override
    protected void updateItem(ReflectiveImage item, boolean btl) {
        super.updateItem(item, btl);
        if (item != null) {
            Image img = item.getImage();
            ImageView imgView = new ImageView(img);
            setGraphic(imgView);
        }
    }
}

package mastermind.game.gui.dialog;

import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mastermind.game.color.ColorField;

public class ButtonCell extends ListCell<ColorField> {
    private ColorField selectedItem;

    @Override
    public Node getStyleableNode() {
        return new ImageView(selectedItem.getImage());
    }

    public void setNode(ColorField selectedItem) {

        this.selectedItem = selectedItem;
    }

    @Override
    protected void updateItem(ColorField item, boolean btl) {
        super.updateItem(item, btl);
        if (item != null) {
            Image img = item.getImage();
            ImageView imgView = new ImageView(img);
            setGraphic(imgView);
        }
    }
}

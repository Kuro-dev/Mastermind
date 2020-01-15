package mastermind.game.gui.dialog;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mastermind.game.gui.resulthandling.ReflectiveImage;

/**
 * ButtonCell for showing the chosen colour on the comboBox.
 */
public class ButtonCell extends ListCell<ReflectiveImage> {

    @Override
    protected void updateItem(ReflectiveImage item, boolean btl) {
        super.updateItem(item, btl);
        if (item != null) {
            final Image img = item.getImage();
            final ImageView imgView = new ImageView(img);
            setGraphic(imgView);
        }
    }
}

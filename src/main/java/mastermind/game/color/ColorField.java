package mastermind.game.color;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import mastermind.game.logic.pin.Pin;

import java.util.ArrayList;
import java.util.LinkedList;

public class ColorField extends Node {
    private final Pin pin;
    private final int width = 50;
    private final int height = 50;
    private final WritableImage image = new WritableImage(width, height);

    public ColorField(Pin pin) {

        this.pin = pin;
    }

    public static ImageView[] parseFields(ArrayList<Pin> pins) {
        final LinkedList<ImageView> images = new LinkedList<>();
        for (Pin pin : pins) {
            images.add(new ImageView(new ColorField(pin).getImage()));
        }
        return images.toArray(new ImageView[0]);
    }


    public Image getImage() {
        final Color color = Color.valueOf(pin.getColor());
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                //The actual fill
                image.getPixelWriter().setColor(x, y, color);
                //The border
                image.getPixelWriter().setColor(0, y, Color.BLACK);
                image.getPixelWriter().setColor((width - 1), y, Color.BLACK);
                image.getPixelWriter().setColor(x, 0, Color.BLACK);
                image.getPixelWriter().setColor(x, (height - 1), Color.BLACK);
            }
        }
        return image;
    }

}

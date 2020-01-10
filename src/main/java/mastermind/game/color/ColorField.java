package mastermind.game.color;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import mastermind.game.gui.resulthandling.ReflectiveImage;
import mastermind.game.logic.pin.Pin;

import java.util.ArrayList;
import java.util.LinkedList;

public class ColorField implements ReflectiveImage {
    private final Pin pin;
    private final int width;
    private final int height;
    private final WritableImage image;

    public ColorField(Pin pin) {
        this(pin, 50, 50);
    }

    public ColorField(Pin pin, int width, int height) {
        this.pin = pin;
        this.width = width;
        this.height = height;
        image = new WritableImage(width, height);
    }

    public static ImageView[] parseFields(ArrayList<Pin> pins) {
        final LinkedList<ImageView> images = new LinkedList<>();
        for (Pin pin : pins) {
            images.add(new ImageView(new ColorField(pin).getImage()));
        }
        return images.toArray(new ImageView[0]);
    }

    public static ColorField[] parseFields(ArrayList<Pin> pins, int width, int height) {
        final LinkedList<ColorField> images = new LinkedList<>();
        for (Pin pin : pins) {
            images.add(new ColorField(pin, width, height));
        }
        return images.toArray(new ColorField[0]);
    }

    public Pin getPin() {
        return pin;
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

    @Override
    public String toString() {
        return pin.toString();
    }
}

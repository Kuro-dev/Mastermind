package mastermind.game.gui.resulthandling;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import mastermind.game.color.ColorCell;
import mastermind.game.gui.resulthandling.exceptions.NoEmptySpaceException;
import mastermind.game.logic.check.Result;

/**
 * Used to convert the return values of the {@link Result}
 * to visible gui elements.
 *
 * @implNote invoke {@link #write()} before using {@link #getImage()}.
 * Otherwise the image will be blank.
 */
public class ResultConverter extends ColorCell implements ReflectiveImage {
    private static final int SQUARE_SIZE = 20;
    private final int matchingPins;
    private final int matchingColours;

    private WritableImage image = new WritableImage(100, SQUARE_SIZE);

    public ResultConverter(Result result) {
        matchingColours = result.getMatchingColours();
        matchingPins = result.getMatchingPins();
    }

    /**
     * Must be invoked before getting the image.
     * Writes the actual images pixels.
     */
    public void write() {
        final Color colorMatch = Color.BLACK;
        final Color pinMatch = Color.GREEN;
        for (int i = 0; i < matchingPins; i++) {
            writePixel(pinMatch);
        }
        for (int i = 0; i < matchingColours; i++) {
            writePixel(colorMatch);
        }
    }

    private void writePixel(Color color) {
        final Coordinate emptyPixel = findEmptyPixel();
        PixelWriter writer = image.getPixelWriter();
        final int xStart = emptyPixel.getX();
        final int yStart = emptyPixel.getY();
        for (int x = 0; x < SQUARE_SIZE; x++) {
            for (int y = 0; y < SQUARE_SIZE; y++) {
                writer.setColor((xStart + x), (yStart + y), color);
            }
        }
    }

    /**
     * Checks the image for 3 consecutive white pixels and returns the respective coordinate;
     *
     * @return The coordinate of the top left pixel of the free space.
     */
    private Coordinate findEmptyPixel() {
        int skipCount = 0;
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                if (image.getPixelReader().getColor(x, y).equals(Color.TRANSPARENT)) {
                    if ((skipCount) < 2) {
                        skipCount++;
                        continue;
                    }
                    return new Coordinate(x, y);
                }
                skipCount = 0;
            }
        }
        throw new NoEmptySpaceException("cant find empty pixels");
    }

    @Override
    public Image getImage() {
        return image;
    }
}

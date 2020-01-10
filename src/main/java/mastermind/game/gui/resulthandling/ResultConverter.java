package mastermind.game.gui.resulthandling;

import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import mastermind.game.gui.resulthandling.exceptions.NoEmptySpaceException;
import mastermind.game.logic.check.Result;

public class ResultConverter {
    private static final int SQUARE_SIZE = 2;
    private final int matchingPins;
    private final int matchingColours;

    private WritableImage image = new WritableImage(20, 20);

    public ResultConverter(Result result) {
        matchingColours = result.getMatchingColours();
        matchingPins = result.getMatchingPins();
    }

    /**
     * Must be invoked before getting the image.
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
        final int[][] emptyPixel = findEmptyPixel();
        PixelWriter writer = image.getPixelWriter();
        final int x = emptyPixel[1][0];
        final int y = emptyPixel[0][1];
        for (int i = 0; i < SQUARE_SIZE; i++) {
            writer.setColor((x + i), (y + i), color);
        }
    }

    /**
     * Checks the image for 3 consecutive white pixels and returns the respective coordinate;
     *
     * @return The coordinate of the top left pixel of the free space.
     */
    private int[][] findEmptyPixel() {
        int skipCount = 0;
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                if (image.getPixelReader().getColor(x, y).equals(Color.WHITE)) {
                    if ((skipCount) < 2) {
                        skipCount++;
                        continue;
                    }
                    return new int[x][y];
                }
                skipCount = 0;
            }
        }
        throw new NoEmptySpaceException("cant find empty pixels");
    }

    public ImageView getImageView() {
        return new ImageView(image);
    }
}

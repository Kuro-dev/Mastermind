package mastermind.game.gui.resulthandling;

import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import mastermind.game.gui.resulthandling.exceptions.NoEmptySpaceException;
import mastermind.game.logic.check.Result;

public class ResultConverter {
    private final int matchingPins;
    private final int matchingColours;

    private WritableImage image = new WritableImage(20, 20);

    public ResultConverter(Result result) {
        matchingColours = result.getMatchingColours();
        matchingPins = result.getMatchingPins();
    }

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
        final PixelWriter writer = image.getPixelWriter();
        final PixelReader reader = image.getPixelReader();
        int[][] emptyPixel = findEmptyPixel();

    }

    private int[][] findEmptyPixel() {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                if (image.getPixelReader().getColor(x, y).equals(Color.WHITE)) {
                    return new int[x][y];
                }
            }
        }
        throw new NoEmptySpaceException("cant find empty pixels");
    }

    public ImageView getImageView() {
        return new ImageView(image);
    }
}

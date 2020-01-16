package pintest;

import mastermind.game.color.ColorField;
import mastermind.game.logic.Mastermind;
import mastermind.game.logic.check.Result;
import mastermind.game.logic.pin.Color;
import mastermind.game.logic.pin.Pin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class PinTestGuiImpl {
    @Test
    public void testPinMatchingWithColorFieldsInInitializedGame() {
        final Pin[] originalPins = new Pin[]{
                new Pin(Color.RED),
                new Pin(Color.RED),
                new Pin(Color.BLUE),
                new Pin(Color.BLUE)};
        final Pin[] comparePins = new Pin[]{
                new Pin(Color.WHITE),
                new Pin(Color.RED),
                new Pin(Color.WHITE),
                new Pin(Color.WHITE)};
        final ColorField[] field = ColorField.parseFields(Arrays.asList(comparePins), 20, 20);
        final Mastermind game = new Mastermind(4, 15);
        game.generateNew(Arrays.asList(originalPins));
        final Result result = game.submit(field);
        result.compare();
        Assertions.assertEquals(1, result.getMatchingPins());
        Assertions.assertEquals(0, result.getMatchingColours());
    }
}

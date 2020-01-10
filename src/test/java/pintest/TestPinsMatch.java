package pintest;

import mastermind.game.logic.check.Result;
import mastermind.game.logic.pin.Color;
import mastermind.game.logic.pin.Pin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestPinsMatch {
    private static Result getResult() {
        final Pin blue = new Pin(Color.BLUE);
        final Pin black = new Pin(Color.BLACK);
        final Pin red = new Pin(Color.RED);
        final Pin white = new Pin(Color.WHITE);
        final Pin[] originalPins = new Pin[]{blue, red, red, white};
        final Pin[] comparePins = new Pin[]{black, red, red, blue};
        return new Result(originalPins, comparePins);
    }

    @Test
    public void pinsOfSameColourWillMatch() {
        final Pin pin1 = new Pin(Color.BLACK);
        final Pin pin2 = new Pin(Color.BLACK);
        Assertions.assertTrue(pin1.equals(pin2));
    }

    @Test
    public void pinsOfDifferentColourWillNotMatch() {
        final Pin pin1 = new Pin(Color.BLACK);
        final Pin pin2 = new Pin(Color.BLUE);
        Assertions.assertFalse(pin1.equals(pin2));
    }

    @Test
    public void resultTestMatchingColours() {
        final Result result = getResult();
        result.compare();
        Assertions.assertEquals(1, result.getMatchingColours());
    }

    @Test
    public void resultTestMatchingPins() {
        final Result result = getResult();
        result.compare();
        Assertions.assertEquals(2, result.getMatchingPins());
    }
}

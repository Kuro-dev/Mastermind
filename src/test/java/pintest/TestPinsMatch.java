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
    public void testPinsOfSameColourWillMatch() {
        final Pin pin1 = new Pin(Color.BLACK);
        final Pin pin2 = new Pin(Color.BLACK);
        Assertions.assertTrue(pin1.equals(pin2));
    }

    @Test
    public void testPinsOfDifferentColourWillNotMatch() {
        final Pin pin1 = new Pin(Color.BLACK);
        final Pin pin2 = new Pin(Color.BLUE);
        Assertions.assertFalse(pin1.equals(pin2));
    }

    @Test
    public void testResultTestMatchingColours() {
        final Result result = getResult();
        result.compare();
        Assertions.assertEquals(1, result.getMatchingColours());
    }

    @Test
    public void testResultTestMatchingPins() {
        final Result result = getResult();
        result.compare();
        Assertions.assertEquals(2, result.getMatchingPins());
    }

    @Test
    public void testResultIsTotalMatch() {
        final Pin blue = new Pin(Color.BLUE);
        final Pin black = new Pin(Color.BLACK);
        final Pin red = new Pin(Color.RED);
        final Pin white = new Pin(Color.WHITE);
        final Pin[] originalPins = new Pin[]{blue, red, red, white};
        final Pin[] comparePins = new Pin[]{blue, red, red, white};
        final Result result = new Result(originalPins, comparePins);
        result.compare();
        Assertions.assertTrue(result.isTotalMatch());
    }

    @Test
    public void testResultIsNotATotalMatch() {
        final Result result = getResult();
        result.compare();
        Assertions.assertFalse(result.isTotalMatch());
    }

    @Test
    public void testResultEdgeCaseWhenMismatchedPositionComesBeforeMatchingPin() {
        final Pin blue = new Pin(Color.BLUE);
        final Pin black = new Pin(Color.BLACK);
        final Pin red = new Pin(Color.RED);
        final Pin white = new Pin(Color.WHITE);
        final Pin[] originalPins = new Pin[]{black, red, white, white};
        final Pin[] comparePins = new Pin[]{red, red, blue, blue};
        final Result result = new Result(originalPins, comparePins);
        result.compare();
        Assertions.assertEquals(1, result.getMatchingPins());
        Assertions.assertEquals(0, result.getMatchingColours());
    }

    //TODO this test case will not be solved right when used in GUI
    @Test
    public void testEdgeCaseRedGreenGreenYellow() {
        final Pin blue = new Pin(Color.BLUE);
        final Pin black = new Pin(Color.BLACK);
        final Pin red = new Pin(Color.RED);
        final Pin white = new Pin(Color.WHITE);
        final Pin[] originalPins = new Pin[]{blue, blue, red, black};
        final Pin[] comparePins = new Pin[]{white, blue, white, white};
        final Result result = new Result(originalPins, comparePins);
        result.compare();
        Assertions.assertEquals(1, result.getMatchingPins());
        Assertions.assertEquals(0, result.getMatchingColours());
    }
}

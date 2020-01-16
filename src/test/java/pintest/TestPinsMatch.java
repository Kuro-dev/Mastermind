package pintest;

import mastermind.game.logic.check.Result;
import mastermind.game.logic.pin.Color;
import mastermind.game.logic.pin.Pin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestPinsMatch {
    private static Result getResult() {
        final Pin[] originalPins = new Pin[]{
                new Pin(Color.BLUE),
                new Pin(Color.RED),
                new Pin(Color.RED),
                new Pin(Color.WHITE)};
        final Pin[] comparePins = new Pin[]{
                new Pin(Color.BLACK),
                new Pin(Color.RED),
                new Pin(Color.RED),
                new Pin(Color.BLUE)};
        return new Result(originalPins, comparePins);
    }

    @Test
    public void testPinsOfSameColourWillMatch() {
        final Pin pin1 = new Pin(Color.BLACK);
        final Pin pin2 = new Pin(Color.BLACK);
        Assertions.assertTrue(pin1.sameColor(pin2));
    }

    @Test
    public void testPinsOfDifferentColourWillNotMatch() {
        final Pin pin1 = new Pin(Color.BLACK);
        final Pin pin2 = new Pin(Color.BLUE);
        Assertions.assertFalse(pin1.sameColor(pin2));
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
        final Pin[] originalPins = new Pin[]{
                new Pin(Color.BLUE),
                new Pin(Color.RED),
                new Pin(Color.RED),
                new Pin(Color.WHITE)};
        final Pin[] comparePins = new Pin[]{
                new Pin(Color.BLUE),
                new Pin(Color.RED),
                new Pin(Color.RED),
                new Pin(Color.WHITE)};
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
    // FIXME: 16/01/2020 this test case will not be solved right when used in GUI. Matching colors will be "1" in Gui
    public void testResultEdgeCaseWhenMismatchedPositionComesBeforeMatchingPin() {
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
        final Result result = new Result(originalPins, comparePins);
        result.compare();
        Assertions.assertEquals(1, result.getMatchingPins());
        Assertions.assertEquals(0, result.getMatchingColours());
    }

}

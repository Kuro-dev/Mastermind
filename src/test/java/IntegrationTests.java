import org.kurodev.game.mastermind.Mastermind;
import org.kurodev.game.mastermind.pin.Color;
import org.kurodev.game.mastermind.pin.Pin;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTests {
    @Test
    public void testMatchingPins() {
        var game = new Mastermind(4, 2, Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE);
        List<Pin> solution = Arrays.asList(
                new Pin(Color.RED),
                new Pin(Color.ORANGE),
                new Pin(Color.BLUE),
                new Pin(Color.BLUE));
        game.generateNew(solution);
        assertTrue(game.submit(solution).isTotalMatch());
        assertTrue(game.isGameOver());
        assertTrue(game.isGameWon());
        assertEquals(1, game.getTurn());
        //NEW GAME
        game.generateNew(solution);
        assertFalse(game.isGameOver());
        Collections.shuffle(solution);
        assertFalse(game.submit(solution).isTotalMatch());
        assertFalse(game.submit(solution).isTotalMatch());
        assertTrue(game.isGameOver());
        assertFalse(game.isGameWon());

    }
}

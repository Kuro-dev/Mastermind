package mastermind.game.logic.pin;

import java.util.Random;

/**
 * Collection of all possible colours used within the game and shown in the GUI
 */
public enum Color {
    WHITE,
    BLACK,
    BLUE,
    YELLOW,
    RED;

    static Color getRandom() {
        final Random random = new Random();
        final Color[] colors = Color.values();
        int pick = random.nextInt(colors.length);
        return colors[pick];
    }
}

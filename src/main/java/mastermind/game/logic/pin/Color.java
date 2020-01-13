package mastermind.game.logic.pin;

import java.util.Random;

public enum Color {
    WHITE,
    BLACK,
    BLUE,
    YELLOW,
    RED;

    static Color getRandom() {
        final Random random = new Random();
        Color[] colors = Color.values();
        int pick = random.nextInt(colors.length);
        return colors[pick];
    }
}

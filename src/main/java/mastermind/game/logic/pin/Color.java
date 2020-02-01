package mastermind.game.logic.pin;

import java.util.Random;

/**
 * Collection of all possible colours used within the game and shown in the GUI
 */
public enum Color {
    WHITE,
    BLACK,
    BLUE,
    CYAN,
    PINK,
    GREEN,
    PURPLE,
    GREY,
    BROWN,
    ORANGE,
    YELLOW,
    RED;

    static Color getRandom() {
        final Random random = new Random();
        final Color[] colors = Color.values();
        int pick = random.nextInt(colors.length);
        return colors[pick];
    }

    public static Color parseColor(String string) {
        for (Color color : Color.values()) {
            if (string.toUpperCase().equals(color.name())) {
                return color;
            }
        }
        throw new IllegalArgumentException("Could not find color");
    }
}

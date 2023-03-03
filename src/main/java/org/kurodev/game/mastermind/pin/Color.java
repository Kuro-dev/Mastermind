package org.kurodev.game.mastermind.pin;

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
    private static Random random;

    static Color getRandom(Color[] possibleColors) {
        if (random == null) random = new Random();
        int pick = random.nextInt(possibleColors.length);
        return possibleColors[pick];
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

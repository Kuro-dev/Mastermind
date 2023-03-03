package org.kurodev.game.mastermind;

import org.kurodev.game.mastermind.check.Result;
import org.kurodev.game.mastermind.pin.Color;
import org.kurodev.game.mastermind.pin.Pin;

import java.util.*;

/**
 * The actual game. Instantiate this and invoke the {@link #generateNew()} to initialize a new game
 * with randomized pins.
 */
public class Mastermind {
    private final int rows;
    private final int tries;
    private final Color[] colorScheme;
    private final ArrayList<Pin> combination;
    private final ArrayList<Pin[]> previousSubmissions = new ArrayList<>();
    private Boolean won;

    /**
     * @param rows   Determines how many different colors will be used
     * @param tries Determines how many tries a player has
     */
    public Mastermind(int rows, int tries) {
        this(rows, tries, Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE);
    }

    /**
     * @param rows   Determines how many different colors will be used
     * @param tries Determines how many tries a player has
     * @param color   The colorscheme to be used in the game
     */
    public Mastermind(int rows, int tries, Color color, Color... otherColors) {
        combination = new ArrayList<>(rows);
        this.rows = rows;
        this.tries = tries;
        var temp = new ArrayList<Color>(otherColors.length + 1);
        temp.add(color);
        Collections.addAll(temp, otherColors);
        this.colorScheme = temp.toArray(new Color[0]);
    }

    public ArrayList<Pin[]> getPreviousSubmissions() {
        return previousSubmissions;
    }

    public Color[] getColorScheme() {
        return colorScheme;
    }

    public ArrayList<Pin> getCombination() {
        return combination;
    }

    /**
     * Generates a new game with the given combination
     *
     * @param combination The new combination
     */
    public void generateNew(List<Pin> combination) {
        won = null;
        previousSubmissions.clear();
        this.combination.clear();
        this.combination.addAll(combination);
    }

    /**
     * Generates a new random order
     */
    public void generateNew() {
        LinkedList<Pin> combination = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            combination.add(i, new Pin(colorScheme));
        }
        generateNew(combination);
    }

    /**
     * used to submit a possible solution to the secret combination
     *
     * @return A result object containing all evaluated information.
     * @apiNote to actually get usable results the {@link Result#compare()}
     * Method must be invoked before getting the values.
     */
    public Result submit(Collection<Pin> pins) {
        return submit(pins.toArray(Pin[]::new));
    }

    /**
     * used to submit a possible solution to the secret combination
     *
     * @param pins The pins as array for possible combination.
     * @return A result object containing all evaluated information.
     * @implNote Will return {@code null} if the game is over
     */
    public Result submit(Pin... pins) {
        if (isGameOver()) return null;
        previousSubmissions.add(pins);
        var result = new Result(combination.toArray(new Pin[0]), pins).compare();
        if (result.isTotalMatch()) {
            setGameWon();
        } else if (getTurn() >= tries) {
            setGameLost();
        }
        return result;
    }

    private void setGameWon() {
        this.won = true;
    }

    private void setGameLost() {
        this.won = false;
    }

    public boolean isGameOver() {
        return this.won != null;
    }

    public boolean isGameWon() {
        return isGameOver() && this.won;
    }

    public int getTurn() {
        return previousSubmissions.size();
    }
}

package mastermind.game.logic;

import mastermind.game.color.ColorField;
import mastermind.game.logic.check.Result;
import mastermind.game.logic.pin.Pin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The actual game. Instantiate this and invoke the {@link #generateNew()} to initialize a new game
 * with randomized pins.
 */
public class Mastermind {
    private final int rows;
    private final int columns;
    private ArrayList<Pin> combination;
    private ArrayList<Pin[]> previousSubmissions = new ArrayList<>();
    private boolean gameOver;

    public Mastermind(int rows, int columns) {
        combination = new ArrayList<>(rows);
        this.rows = rows;
        this.columns = columns;
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
        gameOver = false;
        previousSubmissions.clear();
        this.combination.clear();
        this.combination.addAll(combination);
        System.out.println("Creating new game with combination: " + combination);
    }

    /**
     * Generates a new random order
     */
    public void generateNew() {
        LinkedList<Pin> combination = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            combination.add(i, new Pin());
        }
        generateNew(combination);
    }

    /**
     * used to submit a possible solution to the secret combination
     *
     * @param pins The pins as array for possible combination.
     * @return A result object containing all evaluated information.
     * @apiNote to actually get usable results the {@link Result#compare()}
     * Method must be invoked before getting the values.
     */
    public Result submit(Pin... pins) {
        previousSubmissions.add(pins);
        return new Result(combination.toArray(new Pin[0]), pins);
    }

    /**
     * @see #submit(Pin...)
     */
    public Result submit(ColorField... submission) {
        List<Pin> list = new LinkedList<>();
        for (ColorField field : submission) {
            list.add(field.getPin());
        }
        return submit(list.toArray(new Pin[0]));
    }

    public boolean isGameOver() {
        if (!gameOver) {
            gameOver = columns == previousSubmissions.size();
        }
        return gameOver;
    }

    public void setGameOver(boolean gameOver, String message) {
        this.gameOver = gameOver;
        System.out.println(message);
    }

    public int getTurn() {
        return previousSubmissions.size();
    }
}

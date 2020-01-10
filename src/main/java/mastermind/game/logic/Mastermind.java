package mastermind.game.logic;

import mastermind.game.color.ColorField;
import mastermind.game.logic.check.Result;
import mastermind.game.logic.pin.Pin;

import java.util.ArrayList;

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
     * Generates a new random order
     */
    public void generateNew() {
        previousSubmissions.clear();
        combination.clear();
        for (int i = 0; i < rows; i++) {
            combination.add(i, new Pin());
        }
        System.err.println("Creating new game with combination: " + combination);
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

    public Result submit(ColorField... fields) {
        ArrayList<Pin> list = new ArrayList<>();
        for (ColorField field : fields) {
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

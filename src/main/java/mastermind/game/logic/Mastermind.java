package mastermind.game.logic;

import mastermind.game.logic.check.Result;
import mastermind.game.logic.pin.Pin;

import java.util.ArrayList;

public class Mastermind {
    private final int rows;
    private final int columns;
    private ArrayList<Pin> combination;
    private ArrayList<Pin[]> previousSubmissions = new ArrayList<>();

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

    public Result submit(Pin... pins) {
        previousSubmissions.add(pins);
        return new Result(combination.toArray(new Pin[0]), pins);
    }

    public boolean isGameOver() {
        return columns == previousSubmissions.size();
    }
}

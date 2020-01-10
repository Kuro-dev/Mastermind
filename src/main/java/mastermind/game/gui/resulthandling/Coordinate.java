package mastermind.game.gui.resulthandling;

public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int x, int y){

        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}

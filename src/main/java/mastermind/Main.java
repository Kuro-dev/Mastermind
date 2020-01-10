package mastermind;

import mastermind.game.logic.Mastermind;

public class Main {
    public static void main(String[] args) {
        final Mastermind game = new Mastermind(4, 15);
        for (int i = 0; i < 10; i++) {
            game.generateNew();
        }

    }
}

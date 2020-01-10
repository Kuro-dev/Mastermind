package mastermind.game.logic.check;

import mastermind.game.logic.pin.Pin;

public class Result {
    private final Pin[] originalPins;
    private final Pin[] comparePins;
    private int matchingColours = 0;
    private int matchingPins = 0;
    private boolean totalMatch;

    /**
     * @param originalPins The original Pins of the masterMind
     * @param comparePins  The guess of the pins.
     * @throws IllegalArgumentException if the original pins and the compare pins arrays are not of matching sizes.
     */
    public Result(Pin[] originalPins, Pin[] comparePins) {
        if (originalPins.length != comparePins.length) {
            throw new IllegalArgumentException("Not matching amounts of pins");
        }
        this.originalPins = originalPins;
        this.comparePins = comparePins;
    }

    public void compare() {
        totalMatch = true;
        for (int i = 0; i < originalPins.length; i++) {
            if (originalPins[i].equals(comparePins[i])) {
                matchingPins++;
            } else {
                for (Pin pin : originalPins) {
                    if (comparePins[i].equals(pin)) {
                        matchingColours++;
                        totalMatch = false;
                        break;
                    }
                }
            }
        }
    }

    public boolean isTotalMatch() {
        return totalMatch;
    }

    public int getMatchingPins() {
        return matchingPins;
    }

    public int getMatchingColours() {
        return matchingColours;
    }
}

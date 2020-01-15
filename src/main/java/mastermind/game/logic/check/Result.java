package mastermind.game.logic.check;

import mastermind.game.logic.pin.Pin;

import java.util.ArrayList;

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
        final ArrayList<Pin> matchedPins = new ArrayList<>();
        final ArrayList<Pin> matchedColors = new ArrayList<>();
        totalMatch = true;
        for (int i = 0; i < originalPins.length; i++) {
            if (originalPins[i].equals(comparePins[i])) {
                matchedPins.add(originalPins[i]);
                matchingPins++;
            }
            totalMatch = false;
        }
        for (int i = 0; i < originalPins.length; i++) {
            for (Pin pin : originalPins) {
                if (comparePins[i].equals(pin) && colorWasNotMatchedBefore(comparePins[i], matchedColors) && !matchedPins.contains(pin)) {
                    matchedColors.add(pin);
                    matchingColours++;
                    break;
                }
            }
        }
    }
    
    private boolean colorWasNotMatchedBefore(Pin comparePin, ArrayList<Pin> matchedColors) {
        return matchedColors.stream().noneMatch(pin -> pin.getColor().matches(comparePin.getColor()));
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

package mastermind.game.logic.check;

import mastermind.game.logic.pin.Pin;

import java.util.ArrayList;

/**
 * A Result is used to compare whether two given pin arrays match or not.
 * use {@link #compare()} to evaluate before using any getter method.
 */
public class Result {
    private final Pin[] masterPins;
    private final Pin[] submittedPins;
    private int matchingColours = 0;
    private int matchingPins = 0;
    private boolean totalMatch;

    /**
     * @param masterPins    The original Pins of the masterMind
     * @param submittedPins The guess of the pins.
     * @throws IllegalArgumentException if the original pins and the compare pins arrays are not of matching sizes.
     */
    public Result(Pin[] masterPins, Pin[] submittedPins) {
        if (masterPins.length != submittedPins.length) {
            throw new IllegalArgumentException("Not matching amounts of pins");
        }
        this.masterPins = masterPins;
        this.submittedPins = submittedPins;
    }

    /**
     * Evaluates the given pin arrays and stores the result.
     */
    public void compare() {
        final ArrayList<Pin> matchedPins = new ArrayList<>();
        totalMatch = true;
        for (int i = 0; i < masterPins.length; i++) {
            if (masterPins[i].sameColor(submittedPins[i])) {
                matchedPins.add(submittedPins[i]);
                matchedPins.add(masterPins[i]);
                matchingPins++;
            } else {
                totalMatch = false;
            }
        }
        final ArrayList<Pin> matchedColors = new ArrayList<>();
        for (Pin master : masterPins) {
            for (Pin compare : submittedPins) {
                if (compare.sameColor(master) &&
                        !matchedPins.contains(compare) &&
                        !matchedPins.contains(master) &&
                        pinWasNotMatchedBefore(compare, matchedColors)) {
                    matchedColors.add(compare);
                    matchingColours++;
                    break;
                }
            }
        }
    }

    private boolean pinWasNotMatchedBefore(Pin comparePin, ArrayList<Pin> matchedColors) {
        return matchedColors.stream().noneMatch(pin -> pin.equals(comparePin));
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

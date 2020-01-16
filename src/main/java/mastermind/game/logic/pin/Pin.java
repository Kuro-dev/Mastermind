package mastermind.game.logic.pin;

public class Pin {
    private final Color color;

    public Pin(Color color) {

        this.color = color;
    }

    public Pin() {
        this(Color.getRandom());
    }

    public String getColor() {
        return color.name().toLowerCase();
    }

    @Override
    public String toString() {
        return "Pin." + color;
    }

    public boolean equals(Pin anotherPin) {
        return color == anotherPin.color;
    }
}

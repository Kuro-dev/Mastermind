package mastermind.game.logic.settings;

public enum Setting {
    HARDMODE(false),
    ROWS(4),
    COLUMNS(15);

    private final String defaultValue;

    Setting(int defaultValue) {
        this(String.valueOf(defaultValue));
    }

    Setting(boolean defaultValue) {
        this(String.valueOf(defaultValue));
    }

    Setting(String defaultValue) {

        this.defaultValue = defaultValue;
    }

    public String get() {
        return this.name().toLowerCase();
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}

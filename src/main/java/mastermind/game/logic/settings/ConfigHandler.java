package mastermind.game.logic.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigHandler extends Properties {
    private File file;

    public ConfigHandler(File file) {
        this.file = file;
    }

    public void read() throws IOException {
        this.load(new FileInputStream(file));
    }

    public void write() throws IOException {
        for (Setting setting : Setting.values()) {
            this.setProperty(setting.get(), setting.getDefaultValue());
        }
        super.store(new FileOutputStream(file), "Mastermind game config");
    }

    public String get(Setting setting) {
        final String result = getProperty(setting.get());
        if (result == null) {
            this.setProperty(setting.get(), setting.getDefaultValue());
            return setting.getDefaultValue();
        }
        return result;
    }
}

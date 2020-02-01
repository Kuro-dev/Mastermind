package mastermind;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mastermind.game.gui.WindowController;
import mastermind.game.logic.settings.ConfigHandler;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    final static File configFile = new File(System.getProperty("user.dir") + "/config.properties");
    private static final ConfigHandler config = new ConfigHandler(configFile);
    private static WindowController mainWindow;

    public static void main(String[] args) {
        try {
            if (!configFile.exists()) {
                config.write();
            } else {
                config.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        launch(args);
    }

    public static WindowController getMainWindow() {
        return mainWindow;
    }

    public static ConfigHandler getConfig() {
        return config;
    }

    @Override
    public void start(Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/mainwindow.fxml"));
        final Scene scene = new Scene(loader.load());
        mainWindow = loader.getController();
        mainWindow.setCombination(getParameters());
        stage.setScene(scene);
        stage.setTitle("Mastermind");
        stage.show();
    }
}

package mastermind;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mastermind.game.gui.WindowController;

public class Main extends Application {
    private static WindowController mainWindow;

    public static void main(String[] args) {
        launch(args);
    }

    public static WindowController getMainWindow() {
        return mainWindow;
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

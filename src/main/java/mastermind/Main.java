package mastermind;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/mainwindow.fxml"));
        final Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }


}

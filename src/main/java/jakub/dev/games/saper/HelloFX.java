package jakub.dev.games.saper;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloFX extends Application {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage stage) throws IOException {
        URL url = getClass().getResource("board_view.fxml");
        ResourceBundle rb = ResourceBundle
                .getBundle("jakub.dev.games.saper.message.board_view");
        Parent root = FXMLLoader.load(url, rb);
        Scene scene = new Scene(root, 500, 400, Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }

}

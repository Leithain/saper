package jakub.dev.games.saper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardView implements Initializable {

    @FXML
    private Button helloButton;

    private ResourceBundle rb;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rb = resourceBundle;
    }
    @FXML
    public void hello(ActionEvent event) {
        System.out.println(rb.getString("hello"));
    }

}

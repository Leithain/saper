package jakub.dev.games.saper;

import jakub.dev.games.saper.nodes.ButtonUtils;
import jakub.dev.games.saper.nodes.SquareButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BoardView implements Initializable {

    @FXML
    public BorderPane borderPane;

    private GridPane gridPane;

    private ResourceBundle rb;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rb = resourceBundle;
    }

    @FXML
    public void newGame(ActionEvent actionEvent) {

        List<SquareButton> squareButtonList = ButtonUtils.squareButtonList(20,20,37);
        gridPane = ButtonUtils.gridPaneWithButtons(squareButtonList);

        borderPane.setCenter(gridPane);
        System.out.println("hello");

    }


}

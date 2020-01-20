package jakub.dev.games.saper;

import jakub.dev.games.saper.nodes.ButtonUtils;
import jakub.dev.games.saper.nodes.SquareButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BoardView implements Initializable {

    @FXML
    public BorderPane borderPane;

    @FXML
    public Button newGameA;

    @FXML
    public Button newGameB;

    @FXML
    public TextField xB;

    @FXML
    public TextField yB;

    @FXML
    public TextField bombsB;


    public final Map<String, int[]> map = Map.of(
            "GameA", new int[]{10, 10, 15},
            "GameB", new int[]{20, 20, 50}
    );

    private int x;
    private int y;
    private int bombs;

    public int getBombs() {
        return bombs;
    }

    private GridPane gridPane;

    private List<SquareButton> squareButtonList;

    private ResourceBundle rb;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rb = resourceBundle;
    }

    @FXML
    public void newGame(ActionEvent actionEvent) {

        int[] prop = null;
        Button button = null;
        if (actionEvent != null && actionEvent.getSource() instanceof Button) {
            button = (Button) actionEvent.getSource();
            prop = map.get(button.getId());
        }
        if (prop != null) {
            x = prop[0];
            y = prop[1];
            bombs = prop[2];
        } else {
            x = 20;
            y = 20;
            bombs = 50;
        }

        startGame(x, y, bombs);

    }

    public void reset(){

    }

    public void startGame(int x, int y, int bombs) {
        squareButtonList = ButtonUtils.squareButtonList(x, y, bombs, this);
        gridPane = ButtonUtils.gridPaneWithButtons(squareButtonList);

        borderPane.setCenter(gridPane);
        System.out.println("hello");
    }

    public void lose() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("You've lost, do you want to play again?");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setHeaderText("Lost");
        ButtonType buttonType = alert.showAndWait().orElse(ButtonType.NO);
        if (buttonType.equals(ButtonType.OK)) {
            startGame(x, y, bombs);
        } else {
            this.squareButtonList.forEach(b -> b.setOnAction(null));
        }
    }

    public void win() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("You've won, do you want to play again?");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setHeaderText("Won");
        ButtonType buttonType = alert.showAndWait().orElse(ButtonType.NO);
        if (buttonType.equals(ButtonType.OK)) {
            startGame(x, y, bombs);
        } else {
            this.squareButtonList.forEach(b -> b.setOnAction(null));
        }
    }

    public void newCustomGame(ActionEvent event) {

        try {
            x = Integer.parseInt(xB.getText());
            y = Integer.parseInt(yB.getText());
            bombs = Integer.parseInt(bombsB.getText());
            startGame(x, y, bombs);
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Warning");
            alert.setContentText("Please specify x, y, bombs");
            alert.show();
        }
    }

}

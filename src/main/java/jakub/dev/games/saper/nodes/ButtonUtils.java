package jakub.dev.games.saper.nodes;

import jakub.dev.games.saper.BoardView;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.util.*;
import java.util.stream.Collectors;

public class ButtonUtils {

    public static GridPane gridPaneWithButtons(List<SquareButton> squareButtons){


        GridPane gridPane = new GridPane();

        squareButtons.forEach(sB -> gridPane.add(sB, sB.getPositionX(), sB.getPositionY()));

        return gridPane;
    }


    public static List<SquareButton> squareButtonList(int x, int y, int numberOfBombs, BoardView controller) {

        List<SquareButton> squareButtons = new ArrayList<>();

        Random random = new Random();

        Set<Coordinates> bombsCoordinatesSet = new HashSet<>();
        while (bombsCoordinatesSet.size() < numberOfBombs){
            bombsCoordinatesSet.add(new Coordinates(random.nextInt(x), random.nextInt(y)));
        }

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                boolean hasBomb = bombsCoordinatesSet.contains(new Coordinates(i, j));
                squareButtons.add(new SquareButton(i, j, hasBomb, squareButtons, controller));
            }
        }

        return squareButtons;
    }






}

class Coordinates {

    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
package jakub.dev.games.saper.nodes;

import jakub.dev.games.saper.BoardView;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;

import javafx.scene.input.MouseEvent;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SquareButton extends Button {

    public static final Object globalLock = new Object();

    private int positionX;
    private int positionY;
    private boolean hasBomb;

    private boolean open = false;

    private List<SquareButton> parentList;
    private BoardView controler;


    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public boolean isHasBomb() {
        return hasBomb;
    }

    public void setHasBomb(boolean hasBomb) {
        this.hasBomb = hasBomb;
    }

    public SquareButton(int positionX, int positionY, boolean hasBomb, List<SquareButton> parentList, BoardView controller) {
        super("");
        this.positionX = positionX;
        this.positionY = positionY;
        this.hasBomb = hasBomb;
        this.parentList = parentList;
        this.controler = controller;


//        this.setText(hasBomb ? "B" : "");

        this.setMinSize(SaperConstants.minButtonSize, SaperConstants.minButtonSize);
        this.setMaxSize(SaperConstants.maxButtonSize, SaperConstants.maxButtonSize);
        this.setPrefSize(SaperConstants.prefButtonSize, SaperConstants.prefButtonSize);

        this.setStyle(SaperConstants.normalButtonStyle);
        this.setOnMouseClicked(this::click);
    }

    public void click(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            this.pop(event);
        } else {
            this.flag(event);
        }
    }

    public void flag(MouseEvent event) {
        this.setStyle(SaperConstants.flagedButtonStyle);
        this.setOnMouseClicked(this::unFlag);
    }

    public void unFlag(MouseEvent event) {
        this.setStyle(SaperConstants.normalButtonStyle);
        this.setOnMouseClicked(this::click);
    }

    public void pop(MouseEvent event) {
        if (this.hasBomb) {
            this.setText("X");
            this.setStyle(SaperConstants.boomButtonStyle);
            controler.lose();
        } else {
            this.printNumberOfBombsAround();
            this.open = true;
            if (this.getNumberOfBombsAround() == 0) {
                for (SquareButton neighbourButton : this.getNeighbourButtons()) {
                    if (!neighbourButton.open) neighbourButton.pop(null);
                }
            }
            this.setStyle(SaperConstants.clickedButtonStyle);
            this.setOnMouseClicked(null);
            if (event != null){
                this.checkWinCondition();
            }
        }
    }

    public void checkWinCondition() {
        long toGo = this.parentList.stream()
                .filter((SquareButton sq) -> !sq.open).filter((SquareButton sq) -> !sq.hasBomb)
                .count();
        if (toGo == 0) {
            controler.win();
        }
        System.out.println(toGo);
    }

    public int getNumberOfBombsAround() {
        return (int) this.getNeighbourButtons()
                .stream()
                .filter(SquareButton::isHasBomb)
                .count();
    }

    public void printNumberOfBombsAround() {
        if (!this.isHasBomb()) {
            int number = getNumberOfBombsAround();
            this.setText(number == 0 ? "" : Integer.toString(number));
        }
    }

    public List<SquareButton> getNeighbourButtons() {

        List<Integer> xList = List.of(this.getPositionX() - 1, this.getPositionX(), this.getPositionX() + 1);
        List<Integer> yList = List.of(this.getPositionY() - 1, this.getPositionY(), this.getPositionY() + 1);

        return this.parentList.stream()
                .filter(sq -> xList.contains(sq.getPositionX()))
                .filter(sq -> yList.contains(sq.getPositionY()))
                .collect(Collectors.toList());

    }

}

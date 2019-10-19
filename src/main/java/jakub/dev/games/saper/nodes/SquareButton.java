package jakub.dev.games.saper.nodes;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.util.Arrays;

public class SquareButton extends Button {

    private int positionX;
    private int positionY;
    private boolean hasBomb;


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

    public SquareButton(int positionX, int positionY, boolean hasBomb) {
        super("");
        this.positionX = positionX;
        this.positionY = positionY;
        this.hasBomb = hasBomb;

        this.setText(hasBomb ? "B" : "");

        this.setMinHeight(20);
        this.setMinWidth(20);
        this.setMaxHeight(30);
        this.setMaxWidth(30);
        this.setPrefSize(30,30);

        this.setOnAction(this::pop);
    }




    public int getBombsAround(SquareButton... squareButton) {
        return (int)Arrays.stream(squareButton).filter(a -> a.hasBomb).count();
    }

    public void pop(ActionEvent event) {
        if (this.hasBomb) {
            this.setText("X");
        } else {
            this.setText("O");
        }
    }

}

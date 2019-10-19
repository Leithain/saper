package jakub.dev.games.saper.nodes;

import javafx.scene.control.Button;

public class SquareButton extends Button {

    private int positionX;
    private int positionY;

    private boolean hasBomb;

    public SquareButton(int positionX, int positionY, boolean hasBomb) {
        super("");
        this.positionX = positionX;
        this.positionY = positionY;
        this.hasBomb = hasBomb;
    }



}

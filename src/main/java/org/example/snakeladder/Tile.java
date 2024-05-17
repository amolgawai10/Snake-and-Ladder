package org.example.snakeladder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle { // inherit 'rectangle' class
    public Tile(int tilesSize){ //take tileSize as a parameter
        setWidth(tilesSize); //'setWidth'
        setHeight(tilesSize); //'setHeight'
        setFill(Color.AZURE); //'setFill' --> for Adding Color
        setStroke(Color.BLACK); //'setStroke --> for giving stroke to border lines
    }
}

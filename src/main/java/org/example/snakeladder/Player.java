package org.example.snakeladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
    private Circle coin;
    private int currentPosition;
    private String name;

    private static Board gameBoard = new Board(); // We have made 'Object' of Board.

    public Player(int tileSize, Color coinColor, String playerName){ // Constructor of 'Player' class
        coin = new Circle(tileSize/2); // created new circle with size - take as a 'radius'
        coin.setFill(coinColor); // giving color to coin
        currentPosition = 0; //start from 1st position
        movePlayer(1);
        name = playerName;
    }

    public void movePlayer(int diceValue){
        //winning condition is if <=100
        if(currentPosition + diceValue <= 100) {
            currentPosition += diceValue;

            //Translate Transition -> Player's coin will run smoothly
            TranslateTransition secondMove = null, firstMove = translateAnimation(diceValue);

            int newPosition = gameBoard.getNewPosition(currentPosition);
            if(newPosition != currentPosition && newPosition != -1){
                currentPosition = newPosition;
                secondMove = translateAnimation(6);//ladder came
            }

            if(secondMove == null){
                firstMove.play();
            }
            else{ //Sequential Transition -> create smooth transition during firstMove and secondMove with some pause.
                SequentialTransition sequentialTransition = new SequentialTransition(firstMove,new PauseTransition(Duration.millis(1000)),secondMove);
                sequentialTransition.play();
            }
        }
//            int x = gameBoard.getXCoordinate(currentPosition);
//            int y = gameBoard.getYCoordinate(currentPosition);
//            //lets move coin at that coordinates
//            coin.setTranslateX(x);
//            coin.setTranslateY(y);
    }

    private TranslateTransition translateAnimation(int diceValue){ // WE did animation during movement of coins here
        TranslateTransition anime = new TranslateTransition(Duration.millis(200*diceValue),coin);
        anime.setToX(gameBoard.getXCoordinate(currentPosition));//x-axis
        anime.setToY(gameBoard.getYCoordinate(currentPosition));//y-axis
        anime.setAutoReverse(false);
        return anime;
    }

    public void startingPosition(){
        currentPosition = 0;
        movePlayer(1);
    }
    boolean isWinner(){
        if(currentPosition == 100){
            return true;
        }
        else{
            return false;
        }
    }

    public Circle getCoin() {
        return coin;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public String getName() {
        return name;
    }
}

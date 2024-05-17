package org.example.snakeladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {

    ArrayList<Pair<Integer,Integer>> positionCoordinates; // Pair<Key,Value> --> key is 'x' and Value is 'y' coordinate

    ArrayList<Integer> snakeLadderPosition;

    public Board(){
        positionCoordinates = new ArrayList<>();
        populatePositionCordinate();
        populateSnakeLadder();
    }

    private void populatePositionCordinate(){
        positionCoordinates.add(new Pair<>(0,0)); //dummy value
        for (int i = 0; i < SnakeLadder.height; i++) {
            for (int j = 0; j < SnakeLadder.width; j++) {
                //x-cordinate
                int xCord = 0;
                if( i % 2 == 0){ //height is even
                    xCord = j*SnakeLadder.tilesSize + SnakeLadder.tilesSize/2;
                }
                else{
                    //when height is odd
                    xCord = SnakeLadder.tilesSize*SnakeLadder.height - (j*SnakeLadder.tilesSize) - SnakeLadder.tilesSize/2;
                }
                //y-cordinate
                int yCord = SnakeLadder.tilesSize*SnakeLadder.height - (i*SnakeLadder.tilesSize) - SnakeLadder.tilesSize/2;
                positionCoordinates.add(new Pair<>(xCord,yCord));
            }
        }
    }

    private void populateSnakeLadder(){
        snakeLadderPosition = new ArrayList<>(); // init ArrayList
        for (int i = 0; i < 101; i++) {
            snakeLadderPosition.add(i);
        }
        snakeLadderPosition.set(4,25);//ladder //4 is index and 25 is value
        snakeLadderPosition.set(13,46);//ladder
        snakeLadderPosition.set(27,5);//snake
        snakeLadderPosition.set(33,49);//ladder
        snakeLadderPosition.set(40,3);//snake
        snakeLadderPosition.set(42,63);//ladder
        snakeLadderPosition.set(43,18);//ladder
        snakeLadderPosition.set(50,69);//ladder
        snakeLadderPosition.set(54,31);//snake
        snakeLadderPosition.set(62,81);//ladder
        snakeLadderPosition.set(66,45);//snake
        snakeLadderPosition.set(74,92);//ladder
        snakeLadderPosition.set(76,58);//snake
        snakeLadderPosition.set(89,53);//snake
        snakeLadderPosition.set(99,41);//snake

    }

    public int getNewPosition(int currentPosition){ // will run if snake was beaten or ladder came
        if(currentPosition > 0 && currentPosition <= 100){
            return snakeLadderPosition.get(currentPosition);
        }
        return -1;
    }
    int getXCoordinate(int position){
        if(position >= 1 && position <= 100){
            return positionCoordinates.get(position).getKey(); // Pair<K,V> and we bring 'K' as 'x' coordinate
        }
        else{
            return -1;
        }
    }

    int getYCoordinate(int position){
        if(position >= 1 && position <= 100){
            return positionCoordinates.get(position).getValue(); // Pair<K,V> and we bring 'V' as 'y' coordinate
        }
        else{
            return -1;
        }
    }

//    public static void main(String[] args) {
//        //I have make main function just to check above "populatePositionCordinate" functionality
//        Board board = new Board();
//        for(int i=0; i<board.positionCoordinates.size(); i++){
//            System.out.println(i+" $ X :" + board.positionCoordinates.get(i).getKey() + " y :" + board.positionCoordinates.get(i).getValue());
//        }
//    }
}

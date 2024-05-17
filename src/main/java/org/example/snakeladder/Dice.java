package org.example.snakeladder;

public class Dice {
    public int getRolledDiceValue(){
        return (int)(Math.random()*6+1); // just return the random value --> range from 1 to 6
    }
}

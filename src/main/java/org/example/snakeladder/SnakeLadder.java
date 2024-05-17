package org.example.snakeladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {

    public static final int tilesSize=40, width=10, height=10; // 10 rows and 10 cols
    public static final int buttonLine = height*tilesSize + 50, infoLine = buttonLine - 30;
    private static Dice dice = new Dice(); // we have made 'Object' of 'Dice' class.
    private Player playerOne,playerTwo;
    private boolean gameStarted = false, playerOneTurn = false, playerTwoTurn = false;
    private Pane createContent(){ // This is a starting point for our application
        Pane root = new Pane();
        root.setPrefSize(width*tilesSize, height*tilesSize + 100);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tilesSize); // created Obj of Tile class
                tile.setTranslateX(j*tilesSize);
                tile.setTranslateY(i*tilesSize);
                root.getChildren().add(tile);//whatever we added in pane, it is like a child for pane
            }
        }

        Image img = new Image("C:\\My Folder\\Projects in java\\SnakeLadder\\SnakeLadder\\src\\main\\snake and ladder image.png"); ///upload img from pc
        ImageView board = new ImageView(); //set image by obj of ImageView
        board.setImage(img);
        board.setFitHeight(height*tilesSize); // height is defined
        board.setFitWidth(width*tilesSize); // width is defined
        //root.getChildren().add(board); //add in pane as a children -> use .add() to add single component in 'pane'

        //Button
        Button playerOneButton = new Button("Player One"); // button for 1st player
        Button playerTwoButton = new Button("Player Two"); // button for 2nd player
        Button startButton = new Button("Start"); // button to start the game

        //Set the Buttons Location according to 'x' and 'y' axis
        playerOneButton.setTranslateY(buttonLine);
        playerOneButton.setTranslateX(20);
        playerOneButton.setDisable(true);//initially player1 button is OFF
        playerTwoButton.setTranslateY(buttonLine);
        playerTwoButton.setTranslateX(300);
        playerTwoButton.setDisable(true);//initially player2 button is OFF - means only 'start' button is ON
        startButton.setTranslateY(buttonLine);
        startButton.setTranslateX(160);

        //Create Label for playerOne, playerTwo and for dice.
        Label playerOneLabel = new Label("");
        Label playerTwoLabel = new Label("");
        Label diceLabel = new Label("Start the Game");

        //Alignment to Labels
        playerOneLabel.setTranslateY(infoLine);
        playerOneLabel.setTranslateX(20);
        playerTwoLabel.setTranslateY(infoLine);
        playerTwoLabel.setTranslateX(300);
        diceLabel.setTranslateY(infoLine);
        diceLabel.setTranslateX(160);

        //Create PlayerOne and PlayerTwo by making object of "Player Class"
        playerOne = new Player(tilesSize-3, Color.BLACK,"Amol.G");
        playerTwo = new Player(tilesSize-5,Color.WHITE,"Jay");

        //1st Player in Action after Button is pressed.
        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){ // if 'start' button is activated.
                    if(playerOneTurn){
                        int diceValue = dice.getRolledDiceValue(); // get the RolledDice value
                        diceLabel.setText("Dice Value : " + diceValue); // display the dice value
                        playerOne.movePlayer(diceValue); //move the player1

                        //winning condition
                        if(playerOne.isWinner()){ //player two reached at 100th block
                            diceLabel.setText("Winner is "+playerOne.getName());

                            //Game is Over - impact on Winner player
                            playerOneTurn = false; // Turn is over
                            playerOneButton.setDisable(true); // button is OFF
                            playerOneLabel.setText(""); // label is empty

                            //Game is Over - impact on Second player
                            playerTwoTurn = false;//Second player turn is coming to an end.
                            playerTwoButton.setDisable(true); // button is OFF
                            playerTwoLabel.setText(""); //label is empty

                            //Game is Over - impact on Start Button
                            startButton.setDisable(false); // start button is again enable.
                            startButton.setText("Restart");

                            gameStarted = false; //Game is Over

                        }
                        else{
                            playerOneTurn = false;//now playerOne Turn is over, give chance to other
                            playerOneButton.setDisable(true); // now first player button is disable
                            playerOneLabel.setText(""); //first player label is blank

                            //Let's give chance to Second player
                            playerTwoTurn = true; // 2nd player turn is activate
                            playerTwoButton.setDisable(false); // 2nd player button is ON
                            playerTwoLabel.setText("Your Turn : " + playerTwo.getName());
                        }
                    }
                }
            }
        });

        //Player Second - On Action
        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerTwoTurn){
                        int diceValue = dice.getRolledDiceValue(); // get the dice value
                        diceLabel.setText("Dice Value : " + diceValue); // display the dice value
                        playerTwo.movePlayer(diceValue); //move the player1

                        //winning condition
                        if(playerTwo.isWinner()){ //player two reached at 100th block
                            diceLabel.setText("Winner is "+playerTwo.getName());

                            //Game is Over - impact on first player
                            playerOneTurn = false; // Turn is over
                            playerOneButton.setDisable(true); // button is OFF
                            playerOneLabel.setText(""); // label is empty

                            //Game is Over - impact on winner player
                            playerTwoTurn = false;//winner turn is over
                            playerTwoButton.setDisable(true); // button is OFF
                            playerTwoLabel.setText(""); //label is empty

                            //Game is Over - impact on Start Button
                            startButton.setDisable(false); // start button is again enable.
                            startButton.setText("Restart");
                            gameStarted = false; //Game is Over
                        }
                        else{
                            //Let's give chance to first player
                            playerOneTurn = true; // 2nd player turn is activate
                            playerOneButton.setDisable(false); // 2nd player button is ON
                            playerOneLabel.setText("Your Turn : " + playerOne.getName());

                            playerTwoTurn = false;//now playerTwo Turn is over, give chance to other
                            playerTwoButton.setDisable(true); // now Second player button is disable
                            playerTwoLabel.setText(""); //first player label is blank
                        }
                    }
                }
            }
        });

        //Start Button in Action
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //make gameStarted as true
                gameStarted = true;
                diceLabel.setText("Game Started");
                startButton.setDisable(true);//now, start button is disable till game not over
                playerOneTurn = true; // first player turn came
                playerOneLabel.setText("Your Turn : "+ playerOne.getName()); //label will show to player1
                playerOneButton.setDisable(false); // player1 button is ON

                playerOne.startingPosition();//After game end, player move to home location

                playerTwoTurn = false;
                playerTwoLabel.setText("");
                playerTwoButton.setDisable(true);

                playerTwo.startingPosition();//After game end, player move to home location

            }
        });

        //Add components in 'pane' -> board,player1Button,playerTwoButton,startButton,Labels,playerOneCoin,playerTwoCoin.
        root.getChildren().addAll(board,playerOneButton,playerTwoButton,startButton,playerOneLabel,playerTwoLabel,diceLabel,
                playerOne.getCoin(),playerTwo.getCoin());//getChildren() -> use to add components in 'pane'

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder !");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
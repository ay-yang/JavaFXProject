/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import static PackGrid.NumberInterface.FIVE_TWENTY;
import static PackGrid.NumberInterface.HUNDRED_FIFTY;
import static PackGrid.NumberInterface.SEVENTY_FIVE;
import static PackGrid.NumberInterface.THREE_HUNDRED;
import java.io.FileInputStream;
import java.io.FileNotFoundException; 
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public interface MainFrameInterface {
    
    String WINNER = "Winner: ";
    String RETURN = "Return to menu";
    String START_RACE = "Start Race";
    String CAR_RACE = "Car Race";
    String START_ANOTHER = "Start Another race";
    
    String WELCOME_MSG = "Welcome, please select an option"; 
    String TBA = "TBA";
    String MSG = "Message";
    
    String SUDOKU_GAME = "Sudoku Game";
    String SUDOKU_SAMPLE = "Display sample sudoku";
    String SUDOKU_SOLUTION = "Show sudoku solution";
    String EXIT = "Exit"; 
    String IS_SOLUTION = "The solution grid is a solution to the sample grid";
    String IS_NOT_SOLUTION = "The solution grid is NOT a solution to the sample grid";
    String VERIFY_SOLUTION = "Verify sudoku solution";
    String TBA_MSG = "This choice will be implemented soon";
    
    Media sadSound = new Media("http://www.soundjay.com/misc/sounds/fail-trombone-01.mp3");
//    Media sadSound = new Media("/Sound/Darth_vader.mp3");
    MediaPlayer sadPlayer = new MediaPlayer(sadSound);
    Media happySound = new Media("http://www.soundjay.com/human/sounds/applause-2.mp3");
//    Media happySound = new Media("/Sound/Forbidden_Friendship.mp3");
    MediaPlayer happyPlayer = new MediaPlayer(happySound);
    GridPane result = new GridPane();
    StackPane centerPane = new StackPane();
    
    GridPane menuGrid = new GridPane(); //contains all the buttons available for the user to choose
    GridPane splitWindow = new GridPane(); //for splitting display
    GridPane winningCar = new GridPane(); //contains all components displaying winning car
    StackPane root = new StackPane();
    GridPane rectangles = new GridPane(); //contains race tracks for assignment#4
    GridPane carRace = new GridPane(); //contains the ImageView of all 4 cars for asg4
    
    Button ExitBtn = new Button(EXIT);
    Button menuButton = new Button(RETURN);
    Text labelText = new Text(WELCOME_MSG);
    Button TBABtn = new Button();
    
    //Below are components pertaining to asg4
    Label winnerLbl = new Label(WINNER);
    ImageView winner = new ImageView();
    Button startBtn = new Button (START_RACE);
    Button carRaceBtn = new Button(CAR_RACE);
//these rectangles make up the race tracks
    Rectangle recCar1 = new Rectangle(FIVE_TWENTY, SEVENTY_FIVE, Color.TRANSPARENT);
    Rectangle recCar2 = new Rectangle(FIVE_TWENTY, SEVENTY_FIVE, Color.TRANSPARENT);
    Rectangle recCar3 = new Rectangle(FIVE_TWENTY, SEVENTY_FIVE, Color.TRANSPARENT);
    Rectangle recCar4 = new Rectangle(FIVE_TWENTY, SEVENTY_FIVE, Color.TRANSPARENT);
    
    Button SudokuBtn = new Button(SUDOKU_GAME);
    Button sampleBtn = new Button(SUDOKU_SAMPLE);
    Button solutionBtn = new Button(SUDOKU_SOLUTION);
    Button verifyBtn = new Button(VERIFY_SOLUTION);
    Label successLbl = new Label(IS_SOLUTION);
    Label failLbl = new Label(IS_NOT_SOLUTION);
    
}

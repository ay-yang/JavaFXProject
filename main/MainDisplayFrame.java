package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.GridPane;

import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import carracepackage.CarRace;
import PackGrid.clsPackGrid;
import PackGrid.NumberInterface;
import static PackGrid.NumberInterface.HUNDRED_FIFTY;
import static PackGrid.NumberInterface.THREE_HUNDRED;
import java.io.FileInputStream;
import javafx.scene.image.Image;
import packSubGrid.clsPackSubGrid;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import java.io.FileNotFoundException; 
import java.util.logging.Level;
import java.util.logging.Logger;

/*
* @author Yue Yang
*/
public class MainDisplayFrame extends Application implements MainFrameInterface, NumberInterface{
    private GridPane sudokuSampleGrid = new GridPane();
    private GridPane sudokuSolutionGrid = new GridPane();
    private GridPane carRace; //contains the ImageView of all 4 cars for asg4
    
    private int[] solution = new int[EIGHTY_ONE];
    private int[] sample = new int[EIGHTY_ONE];
    
    private clsPackGrid packGrid = new clsPackGrid();

    public static void main(String [] args){
        launch(args);
    }

    public void start(Stage menu){
        
        recCar1.setStroke(Color.BLACK);
        recCar2.setStroke(Color.BLACK);
        recCar3.setStroke(Color.BLACK);
        recCar4.setStroke(Color.BLACK);       

        ExitBtn.setPrefSize(SIXTY, TWENTY);
        TBABtn.setPrefSize(SIXTY,TWENTY);
        TBABtn.setText(TBA);

        //for populating sudoku grids(asg 3)
        sudokuSolutionGrid = packGrid.getSolution();
        solution = packGrid.getSolutionArray();
        sudokuSampleGrid = packGrid.getSample();
        sample = packGrid.getSampleArray();
        setConstraints();

        //This is the menu that appears in area 3
        labelText.setFont(Font.font("Arial", SIXTEEN));
        menuGrid.setPadding(new Insets(THREE_FIFTY,THIRTEEN,TWELVE,TWENTY));
        menuGrid.setHgap(EIGHT);
        menuGrid.setVgap(TWENTY);
        
        //These are buttons of the main menu
        menuGrid.add(SudokuBtn, ZERO,ONE);
        menuGrid.add(TBABtn, ZERO,TWO);
        menuGrid.add(ExitBtn, ZERO,FOUR);
        menuGrid.add(carRaceBtn, ZERO,THREE);
        
        //These are buttons of submenu of the sudoku menu(assignment #3),
        //they are not visible at first
        menuGrid.add(sampleBtn, ONE,ONE);
        sampleBtn.setVisible(false);
        menuGrid.add(solutionBtn, ONE,TWO);
        solutionBtn.setVisible(false);
        menuGrid.add(verifyBtn, ONE, THREE);
        
        //This is the button for submenu pertaining to assignment #4
        //invisible at first
        menuGrid.add(startBtn, ONE, FOUR);
        startBtn.setVisible(false);
        verifyBtn.setVisible(false);
        
        //This button will appear on both submenus 
        //invisible at first
        menuGrid.add(menuButton, ONE, FIVE);
        menuButton.setVisible(false);
        
        splitWindow.add(labelText, ZERO ,ONE);
        splitWindow.add(menuGrid, ZERO,ONE);

        //root.setStyle("-fx-background-color: #336699;" );

        //for car race(asg 4)
        CarRace race = new CarRace(this);
        carRace = race.getRace();
        try {
            race.createRaceCars();
        } catch (FileNotFoundException ex) {
            Logger.getLogger("Image(s) of car(s) not found!");
        }
        carRace.setVgap(THIRTY_FIVE);
        carRace.setPadding(new Insets(FIFTY, ZERO, ZERO, TWENTY));
        
        rectangles.add(recCar1, ONE, ZERO);
        rectangles.add(recCar2, ONE, ONE);
        rectangles.add(recCar3, ONE, TWO);
        rectangles.add(recCar4, ONE, THREE);
        rectangles.setPadding(new Insets(TWENTY, ONE, ONE , HUNDRED));
        
        winningCar.add(winnerLbl, ZERO, ZERO);
        winningCar.add(winner, ZERO, ONE);  
        winningCar.setVgap(TWENTY);
        winningCar.setPadding(new Insets(FOUR_THIRTY_FIVE, ZERO, ZERO, FOUR_THIRTY_FIVE));

        TBABtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                JOptionPane.showMessageDialog(null, TBA_MSG, MSG, JOptionPane.PLAIN_MESSAGE);
            }
        });

        //When sudokuBtn is clicked, will disable the buttons of the main menu, and enable buttons pertaining to 
        //sudoku assignment #3
        SudokuBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                sampleBtn.setVisible(true);
                SudokuBtn.setDisable(true);
                TBABtn.setDisable(true);
                carRaceBtn.setDisable(true);
                ExitBtn.setDisable(true);
                menuButton.setVisible(true);
            }
        });

        sampleBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                root.getChildren().clear();
                splitWindow.getChildren().remove(labelText);
                sampleBtn.setDisable(true);
                sudokuSampleGrid.setPadding(new Insets(TWENTY, ZERO, TWO_FIFTY, TWENTY));
                sudokuSampleGrid.setGridLinesVisible(true);
                solutionBtn.setVisible(true);
                 root.getChildren().addAll(sudokuSampleGrid, splitWindow);
            }
        });

        carRaceBtn.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                root.getChildren().clear();
		splitWindow.getChildren().remove(labelText);

		startBtn.setVisible(true);
		SudokuBtn.setDisable(true);
	        TBABtn.setDisable(true);
		carRaceBtn.setDisable(true);
		ExitBtn.setDisable(true);
		menuButton.setVisible(true);

		root.getChildren().addAll(rectangles, carRace, splitWindow);
            }
        });

        menuButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                root.getChildren().clear();
                
                startBtn.setVisible(false);
                SudokuBtn.setDisable(false);
                TBABtn.setDisable(false);
                carRaceBtn.setDisable(true);
                ExitBtn.setDisable(false);
                carRaceBtn.setDisable(false);
                sampleBtn.setDisable(false);

                sampleBtn.setVisible(false);
                solutionBtn.setVisible(false);
                verifyBtn.setVisible(false);
                menuButton.setVisible(false);
                
                sadPlayer.stop();
                
                root.getChildren().addAll(splitWindow);
                

            }
        });

        solutionBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                root.getChildren().clear();
                splitWindow.getChildren().remove(labelText);
                verifyBtn.setVisible(true);
                sudokuSolutionGrid.setPadding(new Insets(TWENTY, ZERO, TWO_FIFTY, FOUR_HUNDRED));
                sudokuSolutionGrid.setGridLinesVisible(true);
                root.getChildren().addAll(sudokuSampleGrid, sudokuSolutionGrid, splitWindow);
            }
        });

        ExitBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                System.exit(0);
            }
        });

        startBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){           
                root.getChildren().clear();
                race.generateRandomSpeed();
                splitWindow.getChildren().remove(labelText);
                startBtn.setText(START_ANOTHER);
                startBtn.setDisable(true);
                menuButton.setDisable(true);
                
                race.startRace();
                race.determineWinner();
                winner.setImage(race.getWinnerImg());
                root.getChildren().addAll(rectangles, carRace, splitWindow);
            }
        });
        
        verifyBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                result.getChildren().clear();
                try {
                    verifySolution();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger("Image not found");
                }
            }
        });

        root.getChildren().addAll(splitWindow);

        Scene scene = new Scene(root, SEVEN_HUNDRED, SIX_FIFTY);

        menu.setTitle("Display Window");
        menu.setScene(scene);
        menu.show();
        menu.setResizable(false);
    }

    /**This class sets the constraints of both sudokuSampleGrid and sudokuSolutionGrid, so that they span a reasonable 
     * part of the display frame
     */
    public void setConstraints(){
        ColumnConstraints colConstraint = new ColumnConstraints();
        colConstraint.setPercentWidth(FOUR_POINT_FIVE);
        ColumnConstraints colConstraint2 = new ColumnConstraints();
        colConstraint2.setPercentWidth(TEN);
        RowConstraints rowConstraint = new RowConstraints();
        rowConstraint.setPercentHeight(EIGHT_POINT_FIVE); 

        //adds columns constraints for all 9 columns
        sudokuSampleGrid.getColumnConstraints().addAll(colConstraint, colConstraint, colConstraint,
                colConstraint, colConstraint, colConstraint, colConstraint, colConstraint, colConstraint);

        sudokuSampleGrid.getRowConstraints().addAll(rowConstraint, rowConstraint, rowConstraint,
               rowConstraint, rowConstraint, rowConstraint, rowConstraint, rowConstraint, rowConstraint);

        sudokuSolutionGrid.getColumnConstraints().addAll(colConstraint2, colConstraint2, colConstraint2,
                colConstraint2, colConstraint2, colConstraint2, colConstraint2, colConstraint2, colConstraint2);

        sudokuSolutionGrid.getRowConstraints().addAll(rowConstraint, rowConstraint, rowConstraint,
                rowConstraint, rowConstraint, rowConstraint, rowConstraint, rowConstraint, rowConstraint);
    }
    
    /**This class will be called once the parallel animation in class CarRace ends,
     * it will re-enable startBtn and menuBtn
     * as well as display the winning car*/
    
    public void displayWinner(){
        root.getChildren().clear();
        startBtn.setDisable(false);
        menuButton.setDisable(false);    
        root.getChildren().addAll(rectangles, carRace, winningCar, splitWindow);
    }

    /**Class used when the user clicks on verifyBtn, which verifies if the solution grid shown is a solution to the sample sudoku grid shown
     * if so, returns true,
     * else returns false.
     */
    public void verifySolution() throws FileNotFoundException{
        Image sad = new Image("/images/sad_face.jpg", THREE_HUNDRED, HUNDRED_FIFTY, true, false);
        Image happy = new Image("/images/JackSmile.jpg", THREE_HUNDRED, HUNDRED_FIFTY, true, false);
       
        ImageView happyView = new ImageView();
        happyView.setImage(happy);

        ImageView sadView = new ImageView();
        sadView.setImage(sad);
        
        clsPackSubGrid subGrid = new clsPackSubGrid();
        result.setVgap(TEN);
        
        result.setPadding(new Insets(FOUR_HUNDRED, TWENTY, ZERO, FOUR_HUNDRED));
        
        if((packGrid.DigRepetInRow(solution)) || (packGrid.DigRepetInColumn(solution)) || !(packGrid.isSolution(solution, sample))
                || subGrid.DigRepetInSubGrid(solution)){
            root.getChildren().clear();
            sadPlayer.play();
            centerPane.getChildren().add(sadView);
            result.add(centerPane, ONE, ONE);
            result.add(failLbl, ONE, ZERO);
            root.getChildren().addAll(sudokuSampleGrid, sudokuSolutionGrid, result, splitWindow);
        }
        else {
            root.getChildren().clear();
            happyPlayer.play();
            centerPane.getChildren().add(happyView);
            result.add(centerPane, ONE, ONE);
            result.add(successLbl, ONE, ZERO);
            root.getChildren().addAll(sudokuSampleGrid, sudokuSolutionGrid, result, splitWindow);
        }

    }

}
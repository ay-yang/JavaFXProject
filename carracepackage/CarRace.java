package carracepackage;

import java.util.Random;
import javafx.animation.TranslateTransition;
import javafx.animation.ParallelTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.MainDisplayFrame;
import PackGrid.NumberInterface;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
/**
 *
 * @author Yue Yang
 */
public class CarRace implements NumberInterface{
    
/**Constants-----------------------------*/
    private final int MAX_DURATION = 8000;
    private final int MIN_DURATION = 3000;
    private final int  SIZE = 80;
    private final int SIX_HUNDRED = 600;

/**Fields--------------------------------*/

    private MainDisplayFrame displayFrame;
    
    private int speedCar1;
    private int speedCar2;
    private int speedCar3;
    private int speedCar4;

    Image car1;
    Image car2;
    Image car3;
    Image car4;

    ImageView imgCar1 = new ImageView();
    ImageView imgCar2 = new ImageView();
    ImageView imgCar3 = new ImageView();
    ImageView imgCar4 = new ImageView();
    Image winnerImg;//contains image of winning car
  
/**A constructor that accepts an object of type MainDisplayFrame as argument
*@param frame stored in displayFrame */   
    public CarRace (MainDisplayFrame frame){
        displayFrame = frame;
    }
 
/**Adds all the ImageView containing all 4 cars without any animation to carPane
*@return carPane */  
    public GridPane getRace(){      
        GridPane carPane = new GridPane();
        
        carPane.add(imgCar1, ZERO, ZERO); 
        carPane.add(imgCar2, ZERO, ONE); 
        carPane.add(imgCar3, ZERO, TWO);
        carPane.add(imgCar4, ZERO, THREE);
        
        return carPane;     
    }  

  
/**This method will associate a specific speed(determined at random) to each car
*and animates them using TranslateTransition. Each Translate Transition is then stored in 
*a ParallelTransition called parallelRace. Once the race is over, it will call a method from 
*class MainDisplayFrame to re-enable certain buttons and display the winning car*/  
    public void startRace(){
        
        TranslateTransition translateCar1 = new TranslateTransition(Duration.millis(speedCar1), imgCar1);
        translateCar1.setFromX(ZERO);
        translateCar1.setToX(SIX_HUNDRED);
        
        TranslateTransition translateCar2 = new TranslateTransition(Duration.millis(speedCar2), imgCar2);
        translateCar2.setFromX(ZERO);
        translateCar2.setToX(SIX_HUNDRED);
        
        TranslateTransition translateCar3 = new TranslateTransition(Duration.millis(speedCar3), imgCar3);
        translateCar3.setFromX(ZERO);
        translateCar3.setToX(SIX_HUNDRED);
        
        TranslateTransition translateCar4 = new TranslateTransition(Duration.millis(speedCar4), imgCar4);
        translateCar4.setFromX(ZERO);
        translateCar4.setToX(SIX_HUNDRED);
        
        ParallelTransition parallelRace = new ParallelTransition();       
        parallelRace.getChildren().addAll(translateCar1, translateCar2, translateCar3, translateCar4);
        parallelRace.play();
        
        parallelRace.setOnFinished(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                displayFrame.displayWinner();
            }
        });   
    }
  
/**Compares all car speed with one another and determines the one with the shortest duration.
*Then, stores the image of car corresponding to the said speed to winnerImg*/ 
    public void determineWinner(){
        
        if(speedCar1< speedCar2 && speedCar1 < speedCar3 && speedCar1 < speedCar4){  
            winnerImg = car1;
        }
        
        else if(speedCar2< speedCar1 && speedCar2 < speedCar3 && speedCar2 < speedCar4){ 
            winnerImg = car2;
        }
        
        else if(speedCar3< speedCar2 && speedCar3 < speedCar1 && speedCar3 < speedCar4){ 
            winnerImg = car3 ;
        }
        
        else if(speedCar4< speedCar2 && speedCar4 < speedCar3 && speedCar4 < speedCar1){
            winnerImg = car4;
        }
        
    }
    /**A getter method that returns the image of winning car, winnerImg*/
    public Image getWinnerImg(){
        return winnerImg;
    }

    /** Generates a number between the minimum and maximum duration for each speed*/
    public void generateRandomSpeed(){
           Random random = new Random();
           speedCar1 = random.nextInt((MAX_DURATION-MIN_DURATION))+ MIN_DURATION;
           speedCar2 = random.nextInt((MAX_DURATION-MIN_DURATION))+ MIN_DURATION;
           speedCar3 = random.nextInt((MAX_DURATION-MIN_DURATION))+ MIN_DURATION;
           speedCar4 = random.nextInt((MAX_DURATION-MIN_DURATION))+ MIN_DURATION;
    }

    /**sets each image to the image of a corresponding car found in package 'images'
    *then these images are stored in their corresponding ImageView*/
    public void createRaceCars() throws FileNotFoundException{
        car1 = new Image("/images/car1.png", SIZE, SIZE, true, false);
        imgCar1.setImage(car1);
        car2 = new Image("/images/car2.png", SIZE, SIZE, true, false);
        imgCar2.setImage(car2);
        car3 = new Image("/images/car3.png", SIZE, SIZE, true, false);
        imgCar3.setImage(car3);
        car4 = new Image("/images/car4.png", SIZE, SIZE, true, false);
        imgCar4.setImage(car4);
    }
    
    
}

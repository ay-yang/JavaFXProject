package PackGrid;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 *
 * @author Yue Yang
 */
public class clsPackGrid implements NumberInterface{

    private int[] solution = new int [EIGHTY_ONE]; //array for storing integers in the solution sodoku
    private int[] sample = new int [EIGHTY_ONE];
    GridPane sudokuSolutionGrid = new GridPane();
    GridPane sudokuSampleGrid = new GridPane();
    private int[] subSol = new int[NINE];
    private int index = 0;


    /**This method will read the text file "Solution.txt"
     * put the integers in a GridPane called sudokuSolutionGrid
     * also populates array solution with the same integers
     * @return sudokuSolutionGrid
     */
    public GridPane getSolution(){
        Scanner scanner;
        try {
            scanner = new Scanner(new File("Solution.txt"));

        while(scanner.hasNextInt()){

            for(int col = ZERO; col< NINE; col++){
               for (int row = ZERO; row < NINE; row++){
                    Text digit = new Text();
                    StackPane centerPane = new StackPane(digit);
                    digit.setText(String.valueOf(scanner.nextInt()));
                    sudokuSolutionGrid.add(centerPane, row, col);
                    
                    solution[row + col*NINE] = Integer.parseInt(digit.getText());
                }
            }
        }
        } catch (FileNotFoundException ex){
            System.out.println("File not found");
        }

        return sudokuSolutionGrid;
    }

     /**This method will read the text file "Sample.txt"
     * put the integers in a GridPane called sampleSolutionGrid
     * also populates array sample with same integers
     * @return sampleSolutionGrid
     */
    public GridPane getSample(){
        Scanner scanner;
        try {
            scanner = new Scanner(new File("Sample.txt"));

        while(scanner.hasNextInt()){
                for(int col = ZERO; col< NINE; col++){
                    for (int row = ZERO; row < NINE; row++){
                        Text digit = new Text();
                        digit.setText(String.valueOf(scanner.nextInt()));

                       if(!(digit.getText().equals("0"))){
                            StackPane centerPane = new StackPane(digit);
                            sudokuSampleGrid.add(centerPane, row, col);
                       }

                       else{
                           Text empty = new Text("");
                           StackPane centerPane2 = new StackPane(empty);
                           sudokuSampleGrid.add(centerPane2, row, col);
                       }

                        sample[row + col*NINE] = Integer.parseInt(digit.getText());
                    }
               }
        }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
          }

        return sudokuSampleGrid;
    }
    
    public int[] getSampleArray(){
        return sample;
    }
    
    public int[] getSolutionArray(){
        return solution;
    }
    
    public boolean DigRepetInSubSol(int[] sub){
        
        subSol = sub;
        
        for (int n = ZERO; n<EIGHT; n++){
            for (int s = ONE+n; s< NINE; s++){
                if (sub[n] == sub[s]){
                    return true;
                }
            }
        }
        return false;
        
    }
     /**This method verifies if there are any digit repetition in any row of the solution sudoku
     * @returns true if repetition is found
     * else returns false
     */
    public boolean DigRepetInRow(int[] sol){

        solution =sol;
        
        for(int i = ZERO; i <NINE; i++){
            for (int r = ZERO; r< NINE; r++){
                    subSol[index] = solution[r+i*NINE];
                    index++;                                     
	    }
            
            if(DigRepetInSubSol(subSol)){
                        return true;
            }
            
            //else continue;
            
            index = ZERO;
       }

        return false;
    }

    /**This method verifies if there are any digit repetition in any column of the solution sudoku
     * it puts every column in a temporary array(subSol), and verifies if there are repetitions in that array.
     * returns true if repetition is found
     * else returns false
     * @return
     */
    public boolean DigRepetInColumn(int[] sol){
        
        solution = sol;

        for(int i = ZERO; i <NINE; i++){
            for (int r = ZERO; r< NINE; r++){
                subSol[i] = solution[i+r*NINE];
                index++;            
            }         
            if(DigRepetInSubSol(subSol)){
                        return true;
            }
            
            //else continue;
            index = ZERO;
        }
            return false;

    }

    /**This method verifies if the solution sudoku corresponds to
     * a solution of the sample sudoku
     * @return
     */
    public boolean isSolution(int sol[], int[] samp){
        
        solution =sol;
        sample = samp;

        for(int i = ZERO; i < EIGHTY_ONE; i ++){
            if(!(sample[i]==ZERO)){
                if(!(sample[i]==solution[i])){
                    return false;
                }
            }
        }
        return true;
    }

}
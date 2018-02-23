
package packSubGrid;
import PackGrid.NumberInterface;
import PackGrid.clsPackGrid;

/*
 * Part of the code that verifies the sudoku
*/
public class clsPackSubGrid implements NumberInterface{

    private int[] solution = new int[EIGHTY_ONE];
    private int[] subSol = new int [NINE];
    private clsPackGrid pack = new clsPackGrid();
    private int index = ZERO;
    private int counter = ZERO;
    
    public boolean DigRepetInSubGrid(int[] solu){
        
        solution = solu;
        
        for(int sol = ZERO; sol< SIXTY_ONE; sol = sol+THREE){
            for (; index < NINE; index ++){    
                if(index ==ZERO){
                    subSol[index] = solution[sol];
                }
                
                else if(index ==ONE){
                    subSol[index] = solution[sol+ONE];
                }
                
                else if(index ==TWO){
                    subSol[index] = solution[sol+TWO];
                }
                
                else if(index ==THREE){
                    subSol[index] = solution[sol+NINE];
                }
                
                else if(index ==FOUR){
                    subSol[index] = solution[sol+TEN];
                }
                
                else if(index ==FIVE){
                    subSol[index] = solution[sol+ELEVEN];
                }
                
                else if(index ==SIX){
                    subSol[index] = solution[sol+EIGHTEEN];
                }
                
                else if(index ==SEVEN){
                    subSol[index] = solution[sol+NINETEEN];
                }
                
                else if(index ==EIGHT){
                    subSol[index] = solution[sol+TWENTY];
                }
            }
                
                if(counter == TWO){
                    sol = sol+TWENTY_ONE;
                    counter = ZERO;
                }

                if(pack.DigRepetInSubSol(subSol)){
                        return true;
                }
                 counter++;
                 index = ZERO;
            }
        return false;
       } 

}


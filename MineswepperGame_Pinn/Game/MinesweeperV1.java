/**
 * Write a description of class MinesweeperV1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
public class MinesweeperV1
{   
    static String[][] board = new String[10][10];
    //create empty board
    static void table(){
        //String[][] board = new String[10][10]; 
        for  (int x=0; x<10; x++){
            for (int y=0; y<10; y++){
                board[x][y] = "o";
            }
        }
    
    }
    //planting bomb  into random board
    static void tableAndBomb(){
        table();
        Random random = new Random();
        // not mvp; make sure that no same locatoin  genarated.
        for (int j=0; j<10; j++){
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            board[x][y] = "x";
        }
        // print the oard out
        String[] coor= {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        System.out.println("+ 1 2 3 4 5 6 7 8 9");
        for  (int x=0; x<10; x++){
            System.out.print(coor[x] + " " );
            for (int y=0; y<10; y++){
                //if there is a bomb re place with "o".
                if (board[x][y].equals("x")){
                    System.out.print("o ");
                }
                else {
                    System.out.print(board[x][y] + " ");
                }
            }
            System.out.println();
        }
        
    }
    public static void main(String[] atgs){
        tableAndBomb();
 
    }
}

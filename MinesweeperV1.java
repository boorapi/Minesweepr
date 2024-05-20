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
    static String[][] board = new String[12][12];
    //create board with bomb planted.
    static void table(){
        //String[][] board = new String[10][10]; 
        for  (int x=0; x<12; x++){
            for (int y=0; y<12; y++){
                board[x][y] = "□";
            }
        }
        //planting bomb 10 into random grid on the board
        Random random = new Random();
        // not mvp; make sure that no same locatoin  genarated.
        for (int j=0; j<15; j++){
            int x = 1+random.nextInt(10);
            int y = 1+random.nextInt(10);
            board[x][y] = "x";
        }
    }
    // counting the bomb aroungd a grid.
    static void CountBomb(){
        table ();
        for (int x=1; x<11; x++){
            for (int y=1; y<11; y++){
                int BombCount = 0;
                // if that grid is not a bomb then check it.
                if (board[x][y] != "x"){
                    //check the surrounding.
                    if(board[x-1][y-1].equals("x")){
                        BombCount += 1;
                    }
                    if(board[x][y-1].equals("x")){
                        BombCount += 1;
                    }
                    if(board[x+1][y-1].equals("x")){
                        BombCount += 1;
                    }
                    if(board[x-1][y].equals("x")){
                        BombCount += 1;
                    }
                    if(board[x+1][y].equals("x")){
                        BombCount += 1;
                    }
                    if(board[x-1][y+1].equals("x")){
                        BombCount += 1;
                    }
                    if(board[x][y+1].equals("x")){
                        BombCount += 1;
                    }
                    if(board[x+1][y+1].equals("x")){
                        BombCount += 1;
                    }
                    String BombNum = Integer.toString(BombCount);
                    board[x][y] = BombNum;
                }
            }
         }
        // print the board out
        String[] coor= {" ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", " "};
        System.out.println("   1 2 3 4 5 6 7 8 9 10" + "\n");
        for  (int x=1; x<11; x++){
            System.out.print(coor[x] + "  " );
            for (int y=1; y<11; y++){
                 //if there is a bomb re place with "□".
                 if (board[x][y].equals("x")){
                      System.out.print("□ ");
                 }
                 else {
                     System.out.print(board[x][y] + " ");
                 }
             }
            System.out.println();
        }
    }
    
    public static void main(String[] atgs){
        CountBomb();
    }
}

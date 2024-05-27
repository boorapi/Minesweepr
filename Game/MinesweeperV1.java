package Game;

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
    static String[][] display = new String[12][12];
    //create board with bomb planted.
    static void table(){
        //String[][] board = new String[10][10]; 
        for  (int x=0; x<12; x++){
            for (int y=0; y<12; y++){
                board[x][y] = "□";
            }
        }
        //planting bomb 15 into random grid on the board
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
                // if that cell is not a bomb then check it.
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
    }
    //print the display out.
    static void printDisplay(){
        String[] coor= {" ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", " "};
        System.out.println("   1 2 3 4 5 6 7 8 9 10\n");
        for  (int x=1; x<11; x++){
            System.out.print(coor[x] + "  " );
            for (int y=1; y<11; y++){
                //display[x][y] = "o";
                System.out.print(display[x][y] + " ");
            }
            System.out.println();
        }    
    }
    public static void main(String args[]){
        Scanner kb = new Scanner(System.in);
        CountBomb();
        
        
        for  (int x=1; x<11; x++){
            for (int y=1; y<11; y++){
                display[x][y] = "o";
            }
        }
        printDisplay();

        boolean game = true;
        int flag = 0;
        System.out.println("wellcome  to Minesweeper game!!");
        while (game){
            System.out.print("Type flag to flaged, dig to dig or undo to undo the flag: ");
            String choice = kb.nextLine();
            
            System.out.print("Type in the coordinate \nfor X axis: ");
            int x = kb.nextInt();
            kb.nextLine();
            System.out.print("for Y axis: ");
            String y = kb.nextLine();
            y = y.toUpperCase();
            //get the upper case y vriable convert it in to char 
            char Yaxis = y.charAt(0);
            
            if  (choice.equals("flag")){
                // Yaxis - 64 to get the correct location for y axis (A = 65 in char).
                display[Yaxis-64][x] = "f";
                flag ++;
                if (flag == 14){
                    game = false;
                }
            }else if(choice.equals("undo")){
                flag --;
            }else if(choice.equals("dig")){
                if (board[Yaxis-64][x].equals("x")){
                    game = false;
                }
                else{
                    display[Yaxis-64][x] = board[Yaxis-64][x];
                }
                //board[Yaxis-64][x] = "⚒";
            }
            System.out.println('\u000c');
            printDisplay();
        }
        System.out.println("You dig a bomb. You lose!!!");
        
    }
}
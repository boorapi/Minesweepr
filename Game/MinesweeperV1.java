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
    static String[][] board = new String[10][10];
    static String[][] display = new String[10][10];
    
    static void plantBomb(){
        Random random = new Random();
        // not mvp; make sure that no same locatoin  genarated.
        for (int j=0; j<15; j++){
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            board[x][y] = "B";
        }
    }
    //check the status on that particular cell.
    static int checkbomb(int x, int y){
        //if it out of board rage return 0.
        if ( x < 0 || x > 10 || y < 0 || y > 10){
            return 0;
        }
        else if (board[x][y].equals("B")){
            return 1;
        }
        else{
            return 0;
        }
    }
    
    // set up the table.
    static void CountBomb(){
        plantBomb();
        //couting the surounding bomb.
        for (int x=0; x<10; x++){
            for (int y=0; y<10; y++){
                int BombCount = 0;
                // if that cell is not a bomb then check it.
                if (board[x][y] != "x"){
                    //top three
                    BombCount += checkbomb(x-1, y-1);
                    BombCount += checkbomb(x, y-1);
                    BombCount += checkbomb(x+1, y-1); 
                    //left
                    BombCount += checkbomb(x-1, y);
                    //right
                    BombCount += checkbomb(x+1, y);
                    //bottom three
                    BombCount += checkbomb(x-1, y+1);
                    BombCount += checkbomb(x, y+1);
                    BombCount += checkbomb(x+1, y+1);
                    
                    String BombNum = Integer.toString(BombCount);
                    board[x][y] = BombNum;
                }
            }
         }
    }
    //print the display out.
    static void printDisplay(){
        String[] coor= {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",};
        System.out.println("   1 2 3 4 5 6 7 8 9 10\n");
        for  (int x=0; x<10; x++){
            System.out.print(coor[x] + "  " );
            for (int y=0; y<10; y++){
                //display[x][y] = "o";
                System.out.print(display[x][y] + " ");
            }
            System.out.println();
        }    
    }
    public static void main(String args[]){
        Scanner kb = new Scanner(System.in);
        //set up the table
        CountBomb();
        
        
        for  (int x=0; x<10; x++){
            for (int y=0; y<10; y++){
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
            x -= 1;
            
            if  (choice.equals("flag")){
                // Yaxis - 64 to get the correct location for y axis (A = 65 in char).
                display[Yaxis-65][x] = "f";
                flag ++;
                if (flag == 14){
                    game = false;
                }
            }else if(choice.equals("undo")){
                display[Yaxis-65][x] = "o";
                flag --;
            }else if(choice.equals("dig")){
                if (board[Yaxis-65][x].equals("B")){
                    game = false;
                }
                else{
                    display[Yaxis-65][x] = board[Yaxis-65][x];
                }
            }
            System.out.println('\u000c');
            printDisplay();
        }
        System.out.println("You dig a bomb. You lose!!!");
        
    }
}
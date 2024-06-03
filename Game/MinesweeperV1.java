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
        for (int x=0; x<10; x++){
            for (int y=0; y<10; y++){
                board[x][y] = "o";
            }
        }
        // not mvp; make sure that no same locatoin  genarated.
        for (int j=0; j<11; j++){
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            board[x][y] = "b";
        }
    }
    //check the status on that particular cell.
    static int countbomb(int x, int y){
        //if it out of board rage return 0.
        if ( x < 0 || x >= 10 || y < 0 || y >= 10){
            return 0;
        }
        else if (board[x][y].equals("b")){
            return 1;
        }
        else{
            return 0;
        }
    }
    
    // set up the table.
    static void CheckBomb(int x, int y){
        //couting the surounding bomb.
        int BombCount = 0;
        
        if ( x < 0 || x >= 10 || y < 0 || y >= 10 || board[x][y].equals("0")){
            return;
        }
        

        //top three
        BombCount += countbomb(x-1, y-1);
        BombCount += countbomb(x, y-1); 
        BombCount += countbomb(x+1, y-1); 
        //left
        BombCount += countbomb(x-1, y);
        //right
        BombCount += countbomb(x+1, y);
        //bottom three
        BombCount += countbomb(x-1, y+1);
            
        BombCount += countbomb(x, y+1);
        BombCount += countbomb(x+1, y+1);
            
        String BombNum = Integer.toString(BombCount);
        if (BombCount > 0){
            board[x][y] = BombNum;
        }
        else {
            board[x][y] = "0";    
            //recursive call to check surounding zero.
            CheckBomb(x-1, y-1);
            CheckBomb(x, y-1);
            CheckBomb(x+1, y-1);
                
            CheckBomb(x-1, y);
            CheckBomb(x+1, y);
            
            CheckBomb(x-1, y+1);
            CheckBomb(x, y+1);
            CheckBomb(x+1, y+1);
        }
    }
    //print the display out.
    static void printDisplay(){
        String[] coor= {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",};
        System.out.println("   1 2 3 4 5 6 7 8 9 10\n");
        for  (int x=0; x<10; x++){
            System.out.print(coor[x] + "  " );
            for (int y=0; y<10; y++){
        System.out.print(board[x][y] + " ");
            }
            System.out.println();
        }    
    }
    //error checking for int 1 - 10 for x axis.
    static int numCheck(String msg){
        Scanner kb  = new Scanner(System.in);
        System.out.print(msg + " For X axis: ");
        while(!kb.hasNextInt()){
            kb.nextLine();
            System.out.print("Input error! " + msg);
        }
        return(kb.nextInt());
    }
    //error checking for commands.
    static String comCheck(String[] commands, String msg){
        Scanner kb = new Scanner(System.in);
        String choice = "";
        
        while (!Arrays.asList(commands).contains(choice)){
            System.out.print(msg);
            choice = kb.nextLine();
            choice = choice.toLowerCase();
        }
        return (choice);
    }
    
    public static void main(String[] args){
        Scanner kb = new Scanner(System.in);
        
        //set up the table and the display.
        plantBomb();
        int bombAmount = 0;
        for  (int x=0; x<10; x++){
            for (int y=0; y<10; y++){
                display[x][y] = "o";
                if (board[x][y].equals("b")){
                    bombAmount ++;
                }
            }
        }
        printDisplay();
        
        boolean game = true;
        int flag = 0;
        System.out.println("wellcome  to Minesweeper game!!");
        while (game){
            System.out.println('\u000c');
            printDisplay();
            
            System.out.println("What would you like to do? ");
            
            //get and error checking the command input.
            String[] commands = {"flag", "undo", "dig"};
            String choice = comCheck(commands, "Pleas type flag to flaged, dig to dig or undo to undo the flag: ");
            
            System.out.println("Type in the coordinate.");
            
            //get and error ckecking the input for x coordinate.
            int x = 0;
            while(x < 1 || x > 10){
                x = numCheck("Please type a number between 1 - 10:  ");
            }
            x -= 1;
            
            //get and error checking the input for y axis
            String[] comY = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
            String y = comCheck( comY, "Pleas type a alphabet from a - j for Y axis: ");
            
            y = y.toUpperCase();
            //get the upper case y vriable convert it in to char 
            char Yaxis = y.charAt(0);
            
             if (choice.equals("flag")){
                // Yaxis - 64 to get the correct location for y axis (A = 65 in char).
                display[Yaxis-65][x] = "f";
                flag ++;
                if (flag == bombAmount){
                    game = false;
                }
            }else if(choice.equals("undo")){
                display[Yaxis-65][x] = "o";
                flag --;
            }else if(choice.equals("dig")){
                if (board[Yaxis-65][x].equals("b")){
                    System.out.println("You dig a bomb. You lose!!!");
                    game = false;
                }
                else{
                    CheckBomb(x, Yaxis-65);
                }
            }
        }
    }
}
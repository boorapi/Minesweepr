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
        for (int j=0; j<10; j++){
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
        //                                             a base case
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
            //recursive call to check surounding if it zero.
            
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
                System.out.print(display[x][y] + " ");
            }
            System.out.println();
        }   
    }
    //transfer data from board to display.
    static int reveal(String command){
        // to keep track of how many bomb did not been flaged.
        int bombleft = 0;
        for  (int x=0; x<10; x++){
            for (int y=0; y<10; y++){
                if (command.equals("revealed")){
                    //if board x, y still did not reviewed  or it contain a bomb or the flag don't reviewed.
                    if (!board[x][y].equals("o") && !board[x][y].equals("b") && !display[x][y].equals("f")){
                        display[x][y] = board[x][y];
                    }
                }
                else if (command.equals("check")){
                    // if there is flag but same position on board does not contain bomb
                    if (display[x][y].equals("F") && !board[x][y].equals("b")){
                        bombleft ++;
                    }
                }
                //else if (command.equals("showbomb")){
                    //if (board[x][y].equals("b")){
                        //display[x][y] = board[x][y];
                    //}
                }
            }
        
        return bombleft;
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
        //set up the display and count the bomb in the table.
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
            System.out.println("  You got " + bombAmount + " 🎌 left.\n");
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
                if (!display[Yaxis-65][x].equals("o")){
                    System.out.println("You can't place flag here. Hit enetr to continue.");
                    kb.nextLine();
                }
                else{
                    display[Yaxis-65][x] = "F";
                    bombAmount --;
                    if (bombAmount == 0){
                        int numleft = reveal("check");
                        if ( numleft > 0){
                            System.out.println("You did not clear the mine. There is " + numleft + " mine that you place the flag wrong\n You lose. 💥💥");
                            game = false;
                        }
                        else{
                            System.out.println("Great job! you have clear all the mine\n you win.🙂");
                            game = false;
                        }
                    }
                }
            }else if(choice.equals("undo")){
                if (!display[Yaxis-65][x].equals("F")){
                    System.out.println("You can not undo this cell because here is no flag here.\nHit enter to continue.");
                    kb.nextLine();
                }
                else {
                    display[Yaxis-65][x] = "o";
                    bombAmount ++;
                }
            }else if(choice.equals("dig")){
                if (board[Yaxis-65][x].equals("b")){
                    System.out.println("You dig a bomb. You lose!!!💥💥");
                    game = false;
                }
                else if (board[Yaxis-65][x].equals("F")){
                    System.out.println("You have is cell flaged please remove the flag before you dig the cell.\nHit enter to comtinue.");
                    kb.nextLine();
                }
                else{
                    CheckBomb(Yaxis-65, x);
                    reveal("revealed");
                }
            }
        }
    }
}
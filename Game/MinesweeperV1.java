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
    static int  BOARDLENGTH = 10;
    static String[][] board = new String[BOARDLENGTH][BOARDLENGTH];
    static String[][] display = new String[BOARDLENGTH][BOARDLENGTH];
    /**
     * This method asign variable "o" to a epmty board array
     * The secound part the method genagrate random coordinate for x and y
     * then chage the cell to "b". It run 10 times for 10 bombs
     */
    static void plantBomb(){
        Random random = new Random();
        for (int x=0; x<10; x++){
            for (int y=0; y<10; y++){
                board[x][y] = "o";
            }
        }
        for (int j=0; j<10; j++){
            int x = random.nextInt(BOARDLENGTH);
            int y = random.nextInt(BOARDLENGTH);
            board[x][y] = "b";
        }
    }
    /**
     * This method chekc if a cell contian a bomb
     * It takes coordiante x and y as in int and check that cell
     * If there is a bomb it will return 1, else return 0.
     */
    static int checkBomb(int x, int y){
        if ( x < 0 || x >= BOARDLENGTH || y < 0 || y >= BOARDLENGTH){ /* if that cell is out of range just return 0 */
            return 0;
        }
        else if (board[x][y].equals("b")){
            return 1;
        }
        else{
            return 0;
        }
    }
    /**
     *This method count how many bomb a around a cell 
     *The argument will be the x and y axis od the cell
     *If the cell is not 0 the amount of bomb will be assign to the board
     *If the cell is 0 the method will run a recurcive call and assing the value to the baord
     */
    static void countBomb(int x, int y){
        int bombCount = 0;                        
        if ( x < 0 || x >= BOARDLENGTH || y < 0 || y >= BOARDLENGTH || board[x][y].equals("0")){ /* This is for the recursive call if the cell is out of ranhe or already been check*/
            return;
        }
        
        bombCount += checkBomb(x-1, y-1);/*top three*/
        bombCount += checkBomb(x, y-1); 
        bombCount += checkBomb(x+1, y-1); 
        
        bombCount += checkBomb(x-1, y);/*left*/
        
        bombCount += checkBomb(x+1, y);/*right*/
        
        bombCount += checkBomb(x-1, y+1);/*bottom three*/
        bombCount += checkBomb(x, y+1);
        bombCount += checkBomb(x+1, y+1);
            
        String BombNum = Integer.toString(bombCount);
        if (bombCount > 0){
            board[x][y] = BombNum;
        }
        else {
            board[x][y] = "0";/*assign the cell to 0*/
            
            countBomb(x-1, y-1);
            countBomb(x, y-1);
            countBomb(x+1, y-1);
                
            countBomb(x-1, y);
            countBomb(x+1, y);

            countBomb(x-1, y+1);
            countBomb(x, y+1);
            countBomb(x+1, y+1);
        }
    }
    /**
     * This method loop through display array and print it out
     */
    static void printDisplay(){
        String[] coor= {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",};
        System.out.println("   1 2 3 4 5 6 7 8 9 10\n");
        for  (int x=0; x<BOARDLENGTH; x++){
            System.out.print(coor[x] + "  " );
            for (int y=0; y<BOARDLENGTH; y++){
                System.out.print(display[x][y] + " ");
            }
            System.out.println();
        }   
    }
    /**
     * This method is for reveal the display and check the win loes condition
     * It takes a command "revealed" or "check"
     * revealed will chage display of o to number if the board is already been revealed.
     * check will loop through and check if flag is at a right position. If the flag is at wrong position bombLeft will increas
     * return bomb left.
     */
    static int reveal(String command){
        int bombLeft = 0;
        for  (int x=0; x<BOARDLENGTH; x++){
            for (int y=0; y<BOARDLENGTH; y++){
                if (command.equals("revealed")){
                    if (!board[x][y].equals("o") && !board[x][y].equals("b") && !display[x][y].equals("f")){/*f board x, y still did not reviewed  and it contain a bomb or the flag don't reviewed.*/
                        display[x][y] = board[x][y];
                    }
                }
                else if (command.equals("check")){
                    if (display[x][y].equals("F") && !board[x][y].equals("b")){/*if the flag is not at a right position*/
                        bombLeft ++;
                    }
                }
                }
            }
        
        return bombLeft;
    }
    /**
     * This method will do error ckecking to x axis input. It will loop through until gets a valid input.
     * The argument is the massage that we want to tell the user.
     * return the valid input
     */
    static int numCheck(String msg){
        Scanner kb  = new Scanner(System.in);
        System.out.print(msg + " For X axis: ");
        while(!kb.hasNextInt()){
            kb.nextLine();
            System.out.print("Input error! " + msg);       
        }
        return(kb.nextInt());
    }
    /**
     * This method will do error checking for the command and y axis. It will loop through until gets a valid input.
     * The argument is the massage that we want to tell the user and the arrays of the valid input
     * return the valid input
     */
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
        plantBomb();
        int bombAmount = 0;
        for  (int x=0; x<BOARDLENGTH; x++){
            for (int y=0; y<BOARDLENGTH; y++){
                display[x][y] = "o"; /*set up the display so evry cell = o*/
                if (board[x][y].equals("b")){/*count how many bomb there is*/ 
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
            String[] commands = {"flag", "undo", "dig"};
            String choice = comCheck(commands, "Please type flag to flaged,dig to dig or undo to undo the flag: ");
            System.out.println("Type in the coordinate.");
            int x = 0;
            while(x < 1 || x > 10){ /*loop through until get a valid input*/
                x = numCheck("Please type a number between 1 - 10:  ");
            }
            x -= 1;
            String[] comY = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
            String y = comCheck( comY, "Pleas type a alphabet from a - j for Y axis: ");
            y = y.toUpperCase(); 
            char Yaxis = y.charAt(0);
            
            if (choice.equals("flag")){
                if (!display[Yaxis-65][x].equals("o")){/*if the cell is already been revealed or flagged */
                    System.out.println("You can't place flag here. Hit enetr to continue.");
                    kb.nextLine();
                }
                else{
                    display[Yaxis-65][x] = "F";
                    bombAmount --;
                    if (bombAmount == 0){/*if all falgs is use check if the player win*/
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
                if (!display[Yaxis-65][x].equals("F")){/*if the player try undo the cell that has not been flagged*/
                    System.out.println("You can not undo this cell because there is no flag here.\nHit enter to continue.");
                    kb.nextLine();
                }
                else {
                    display[Yaxis-65][x] = "o";
                    bombAmount ++;
                }
            }else if(choice.equals("dig")){
                if (display[Yaxis-65][x].equals("F")){/*if the player try to dig a cell that already been flagged*/
                    System.out.println("You have is cell flaged please remove the flag before you dig the cell.\nHit enter to comtinue.");
                    kb.nextLine();
                }
                else if (board[Yaxis-65][x].equals("b")){
                    System.out.println("You dig a bomb. You lose!!!💥💥");
                    game = false;
                }
                else{
                    countBomb(Yaxis-65, x);/*check the bomb surrounding this cell*/
                    reveal("revealed");/*reveal the cell by assigning variable to the display*/
                }
            }
        }
    }
}
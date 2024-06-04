package Game;


/**
 * Write a description of class recursion here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.Arrays;
public class recursion
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
        static void CheckBomb(int x, int y){
        //couting the surounding bomb.
        int BombCount = 0;
        
        //System.out.println("this is recursion aghhhhhhhh");
        
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
            //System.out.println(x);
            CheckBomb(x-1, y-1);
            CheckBomb(x, y-1);
            CheckBomb(x+1, y-1);
            
            CheckBomb(x-1, y);
            CheckBomb(x+1, y);            

            CheckBomb(x-1, y+1);
            CheckBomb(x, y+1);
            CheckBomb(x+1, y+1);
            System.out.println("donee");
        }
    }
    public static void main(String[] args){
        plantBomb();
        CheckBomb(5, 5);
    }
}

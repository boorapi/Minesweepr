
/**
 * Write a description of class input here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner;
public class input
{
    public static void main (String[] args){
        Scanner kb = new Scanner(System.in);
        String choice = kb.nextLine();
        if  (choice.equals("flage")){
            System.out.println("Type in the coordinate \nfor X axis");
            int x = kb.nextInt();
            kb.nextLine();
            System.out.println("for Y axis");
            String y = kb.nextLine();
            y = y.toUpperCase();
            //get the upper case y vriable convert it in to char 
            char Yaxis = y.charAt(0);
            Yaxis -= 64;
            System.out.println(x);
            System.out.print(" " + Yaxis);
        }else if(choice.equals("dig")){
            System.out.println("Type in the coordinate \nfor X axis");
            int x1 = kb.nextInt();
            System.out.println("for Y axis");
            String y1 = kb.nextLine();
            y1 = y1.toUpperCase();
            char Yaxis1= y1.charAt(0);
            Yaxis1 -= 64;
            //if (board[x1][Yaxis1].equals("x")){
                //System.out.println("you dig a mine you lose!");
                // game = false;
            //}
            System.out.println(x1 + Yaxis1);
            }
    }
}

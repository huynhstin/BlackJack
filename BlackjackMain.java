
/**
 * Write a description of class BlackjackMain here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class BlackjackMain
{
    public static void main (String [] args)
    {
        Scanner in = new Scanner(System.in);
        BlackJack b = new BlackJack();
        ArrayList<Card> bjDeck = b.getDeck();
        while(true)
        {
            System.out.println("Press [1] to shuffle");
            System.out.println("Press [2] to view deck");
            System.out.println("Press [3] to hit");
            System.out.println("Press [4] to view current hand");
            System.out.println("Press [5] to exit.");
            int select = in.nextInt();
            switch(select)
            {
                case 1: 
                     b.shuffleDeck();
                     break;
                case 2: 
                     b.printDeck();
                     break;
                case 3:
                     b.hit(b.getPlayer());
                     break;
                case 4:
                     b.getPlayer().printCurrHand();
                     break;
                case 5:
                     return;
                default:
                     System.out.println("Pick one");
                     break;
            }
        }
    }
}

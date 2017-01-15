
/**
 * Blackjack runner
 * 
 * @author (Justin Huynh and Aaron Nguyen) 
 * @version (Jan 14 2017)
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
            System.out.println("Press [1] to shuffle.");
            System.out.println("Press [2] to view deck.");
            System.out.println("Press [3] to deal."); //each player gets two cards
            System.out.println("Press [4] to hit.");
            System.out.println("Press [5] to view current hand.");
            System.out.println("Press [6] to split."); // this still needs to be tested
            System.out.println("Press [7] to stand.");
            System.out.println("Press [8] to fold.");
            System.out.println("Press [9] to reset game.");
            int select = in.nextInt();
            switch(select)
            {
                case 1: // shuffle
                    b.shuffleDeck();
                    break; // if modifying make sure to put break after everything
                case 2: // print deck
                    b.printDeck();
                    break;
                case 3: //deal
                    b.deal(b.getDealer(), 2);
                    b.deal(b.getPlayer(), 2); // give each player two cards
                    break;
                case 4: //hit
                    if(b.isDeckSplit())
                    {
                        System.out.println("Press [1] to hit first hand, or [2] for second.");
                        int whichHand = in.nextInt();
                        if(whichHand == 1)
                        {
                            b.hit(b.getFirstHalf());
                        }
                        else if(whichHand == 2)
                        {
                            b.hit(b.getSecondHalf());
                        }
                    }
                    else
                    {      
                        b.hit(b.getPlayer());
                    }
                    break;                
                case 5: //view hand
                    System.out.print("Dealer's face up card: ");
                    b.getDealer().printFirstCard();
                    if(b.isDeckSplit())
                    {
                        System.out.print("First Hand: ");
                        b.getFirstHalf().printCurrHand();
                        System.out.print("Second Hand: ");
                        b.getSecondHalf().printCurrHand();
                    }
                    else
                    {
                        System.out.print("Your hand: ");
                        b.getPlayer().printCurrHand();
                    }
                    break;
                case 6: //split deck
                    b.splitDeck(b.getPlayer());
                    break;
                case 7: // when you stand it's dealer's turn
                    b.dealersTurn(b.getDealer(), b.getPlayer()); //needs to consider split case
                    break;
                case 8: //fold
                    System.out.println("You lost.");
                    return;
                case 9:
                    //reset game 
                default:
                    System.out.println("Pick one");
                    break;
            }
        }
    }
}

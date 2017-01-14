
/**
 * Write a description of class BlackJack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class BlackJack
{
    public Deck deck;
    public static Hand playerOne;
    public static Hand playerTwo;
    public static Hand playerOneSplit;
    public static Hand playerTwoSplit;
    
    public BlackJack()
    {
        deck = new Deck();
        playerOne = new Hand();
        playerTwo = new Hand();
        playerOneSplit = new Hand();
        playerTwoSplit = new Hand();
    }
    
    public void hit(Hand player)
    {
        Card topOfDeck = deck.drawCard();
        player.getHand().add(topOfDeck);
        int cardDrawnVal = topOfDeck.getVal().getValue();
        player.updateTotal(cardDrawnVal);
        check21(player);
    }
    
    public void check21(Hand player)
    {
        if(player.getTotal() > 21)
        {
            System.out.println("Bust");
        }
        else
        {
            System.out.println("Current score: " +player.getTotal());
        }
    }
    
    public Deck getDeck()
    {
        return deck;
    }
    
    public static void main (String [] args)
    {
        while(true)
        {
            Scanner in = new Scanner(System.in);
            BlackJack b = new BlackJack();
            Deck deck = b.getDeck();
            System.out.println("Press [1] to shuffle");
            System.out.println("Press [2] to view deck");
            System.out.println("Press [3] to hit");
            //System.out.println("Press [4] to 
            int select = in.nextInt();
            switch(select)
            {
                case 1: 
                     deck.shuffleDeck();
                     break;
                case 2: 
                     deck.printDeck(deck);
                     break;
                case 3:
                     b.hit(playerOne);
                     break;
                default:
                     System.out.println("Pick one");
                     break;
            }
        }
    }
}

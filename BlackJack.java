
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
    public Hand playerOne;
    public Hand playerTwo;
    public Hand playerOneSplit;
    public Hand playerTwoSplit;
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
        Card topOfDeck = deck.getDeckArr().get(0);
        player.getHand().add(topOfDeck);
        int cardDrawnVal = topOfDeck.getVal().getValue();
        playerOne.updateTotal(cardDrawnVal);
        deck.getDeckArr().remove(0);
        check21(player);
    }
    
    public void check21(Hand player)
    {
        if(player.getTotal() > 21)
        {
            System.out.println("Bust");
        }
    }
}

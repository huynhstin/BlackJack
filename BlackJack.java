
/**
 * Blackjack Game
 * 
 * @author (Justin Huynh and Aaron Nguyen) 
 * @version (Jan 14 2017)
 */
import java.util.*;
public class BlackJack
{
    private ArrayList<Card> deck;
    public static Hand playerOne;
    
    public BlackJack()
    {
        deck = new ArrayList <Card>();
        fillDeck();
        playerOne = new Hand();
    }
    
    public void fillDeck()
    {
        for(int i = 0; i < 13; i++)
        {
            CardValue cVal = CardValue.values()[i];
            for(int j = 0; j < 4; j++)
            {
                Card card = new Card(cVal, Suit.values()[j]);
                deck.add(card);
            }
        }
    }
    
    public void shuffleDeck()
    {
        Collections.shuffle(deck);
    }
    
    public Card drawCard()
    {
        Card topOfDeck = deck.get(0);
        deck.remove(0);
        return topOfDeck;
    }
    
    public void hit(Hand player)
    {
        Card topOfDeck = deck.remove(0);
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
    
    public ArrayList <Card> getDeck()
    {
        return deck;
    }
    
    public void setDeck(ArrayList <Card> d)
    {
        deck = d;
    }
    
    public void printDeck() //for testing
    {
        Iterator cardIterator = deck.iterator();
        while (cardIterator.hasNext())
        {
            Card aCard = (Card)cardIterator.next();
            System.out.print(aCard.getVal() + " of " + aCard.getSuit() +"; ");
        }
        System.out.println();
    }
    
    public Iterator iterator()
    {
        return deck.iterator();
    }
    
    public Hand getPlayer()
    {
        return playerOne;
    }
}

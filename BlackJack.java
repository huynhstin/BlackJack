/**
 * Blackjack Game
 * 
 * @author (Justin Huynh and Aaron Nguyen) 
 * @version (Jan 14 2017)
 */
import java.util.*;
public class BlackJack
{
    private ArrayList<Card> deck; //the deck itself
    public static Hand playerOne; //first player
    public static Hand firstHalf; //two halves
    public static Hand secondHalf;
    public boolean deckSplit;
    
    public BlackJack()
    {
        deck = new ArrayList <Card>();
        fillDeck();
        playerOne = new Hand();
        firstHalf = new Hand();
        secondHalf = new Hand();
        deckSplit = false;
    }
    
    /**
     * Fill in order
     */
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
    
    /**
     * Shuffle
     */
    public void shuffleDeck()
    {
        Collections.shuffle(deck);
    }
    
    /**
     * Get top card of deck
     */
    public Card drawCard()
    {
        Card topOfDeck = deck.get(0);
        deck.remove(0);
        return topOfDeck;
    }
    
    /**
     * Add top card of deck to player's hand, remove top card of deck 
     */
    public void hit(Hand player)
    {
        Card topOfDeck = deck.remove(0);
        player.getHand().add(topOfDeck);
        int cardDrawnVal = topOfDeck.getVal().getValue();
        player.updateTotal(cardDrawnVal);
        check21(player);
    }
    
    public void deal(Hand player)
    {
        for(int i = 0; i < 2; i++) //just hit twice, withour returning current score
        {
            Card topOfDeck = deck.remove(0);
            player.getHand().add(topOfDeck);
            int cardDrawnVal = topOfDeck.getVal().getValue();
            player.updateTotal(cardDrawnVal);
        }
    }
    
    /**
     * Basic logic for now
     */
    public void check21(Hand player)
    {
        if(player.getTotal() > 21)
        {
            System.out.println("Bust: " +player.getTotal());
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
    
    /**
     * Get methods
     */
    public Hand getPlayer()
    {
        return playerOne;
    }
    
    public Hand getFirstHalf()
    {
        return firstHalf;
    }
    
    public Hand getSecondHalf()
    {
        return secondHalf;
    }
    
    public boolean isDeckSplit()
    {
        return deckSplit;
    }
    
    public void splitDeck(Hand player)
    {
        if(player.getHand().size() == 2 && player.getHand().get(0).getVal().values().equals(player.getHand().get(1).getVal().values())) // i'm so sorry 
        {
            deckSplit = true;
            firstHalf.getHand().add(player.getHand().get(0)); 
            hit(firstHalf);
            secondHalf.getHand().add(player.getHand().get(1));
            hit(secondHalf);
            playerOne.getHand().remove(0); //to make sure that they're not accidentally used anymore
            playerOne.getHand().remove(1); //take out both the cards
        }
        else
        {
            System.out.println("You can't do that.");
        }
    }
}

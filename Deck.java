
/**
 * Write a description of class Deck here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class Deck
{
    public ArrayList <Card> deck;
    public Deck()
    {
        deck = new ArrayList <Card>();
        fillDeck();
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
}

/**
 * Card which has a CardValue and a Suit
 * 
 * @author (Justin Huynh and Aaron Nguyen) 
 * @version (Jan 14 2017)
 */
public class Card
{
    public CardValue cardVal;
    public Suit suit;
    public Card(CardValue v, Suit s)
    {
        cardVal = v;
        suit = s;
    }
    
    public Suit getSuit()
    {
        return suit;
    }
    
    public CardValue getVal()
    {
        return cardVal;
    }
    
    public void setValue(CardValue newVal)
    {
        cardVal = newVal;
    }
    
    public void setSuit(Suit newSuit)
    {
        suit = newSuit;
    }
}

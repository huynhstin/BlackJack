
/**
 * Enumeration class CardValue - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum CardValue
{
    TWO(2),
    THREE(3), 
    FOUR(4), 
    FIVE(5), 
    SIX(6), 
    SEVEN(7), 
    EIGHT(8), 
    NINE(9), 
    TEN(10), 
    JACK(10), 
    QUEEN(10), 
    KING(10), 
    ACE(11);
    public int cardVal;

    private CardValue(int num)
    {
        cardVal = num;
    }   
    
    public int getValue()
    {
        return cardVal;
    }
}


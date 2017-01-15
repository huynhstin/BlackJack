
/**
 * Card values
 * 
 * @author (Justin Huynh and Aaron Nguyen) 
 * @version (Jan 14 2017)
 */
public enum CardValue
{
    TWO("Two", 2),
    THREE("Three", 3), 
    FOUR("Four", 4), 
    FIVE("Five", 5), 
    SIX("Six", 6), 
    SEVEN("Seven", 7), 
    EIGHT("Eight", 8), 
    NINE("Nine", 9), 
    TEN("Ten", 10), 
    JACK("Jack", 10), 
    QUEEN("Queen", 10), 
    KING("King", 10), 
    ACE("Ace", 11);
    public int cardVal;
    public String name; 
    private CardValue(String newName, int num)
    {
        name = newName;
        cardVal = num;
    }   
    
    public int getValue()
    {
        return cardVal;
    }
    
    public String getString()
    {
        return name;
    }
}


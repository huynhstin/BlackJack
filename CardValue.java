/**
 * Card values
 * 
 * @author (Justin Huynh and Aaron Nguyen) 
 * @version (Jan 14 2017)
 */
public enum CardValue
{
    TWO("2", 2),
    THREE("3", 3), 
    FOUR("4", 4), 
    FIVE("5", 5), 
    SIX("6", 6), 
    SEVEN("7", 7), 
    EIGHT("8", 8), 
    NINE("9", 9), 
    TEN("10", 10), 
    JACK("J", 10), 
    QUEEN("Q", 10), 
    KING("K", 10),
    ACE("A", 11);
    //ACE2("A, 1);
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
    
    public void setValue(int newValue)
    {
        cardVal = newValue;
    }
    
    public String getString()
    {
        return name;
    }
}

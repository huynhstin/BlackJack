/**
 * Enumeration class Suit - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Suit
{
    HEARTS("HEART"),
    SPADES("SPADE"),
    CLUBS("CLUB"), 
    DIAMONDS("DIAMOND");
    
    public String name;
    private Suit(String newName)
    {
        name = newName;
    }
    
    public String getName()
    {
        return name;
    }
}

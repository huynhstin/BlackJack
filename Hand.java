
/**
 * Write a description of class Hand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class Hand
{
    public ArrayList <Card> player;
    public int total;
    public Hand()
    {
        total = 0;
        player = new ArrayList<Card>();
    }
    
    public void updateTotal(int val)
    {
        total += val;
    }
    
    public int getTotal()
    {
        return total;
    }
    
    public ArrayList<Card> getHand()
    {
        return player;
    }
}

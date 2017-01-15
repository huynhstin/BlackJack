
/**
 * Player's hands
 * 
 * @author (Justin Huynh and Aaron Nguyen) 
 * @version (Jan 14 2017)
 */
import java.util.*;
public class Hand
{
    public ArrayList <Card> player;
    private int total; //the score
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
    
    public void setTotal(int newScore)
    {
        total = newScore;
    }
    
    public ArrayList<Card> getHand()
    {
        return player;
    }
    
    public Iterator iterator()
    {
        return player.iterator();
    }
    
    public void printCurrHand()
    {
        Iterator cardIterator = player.iterator();
        while (cardIterator.hasNext())
        {
            Card aCard = (Card)cardIterator.next();
            System.out.print(aCard.getVal() + " of " + aCard.getSuit() +"; ");
        }
        System.out.println();
    }
    
    public void printFirstCard()
    {
        if(player.size() != 0)
        {
            Card aCard = (player.get(0));
            System.out.println(aCard.getVal() + " of " + aCard.getSuit() +"; ");
        }
        else 
        {
            System.out.println("N/A. Deal first.");
        }
    }
}


/**
 * Player's Players
 * 
 * @author (Justin Huynh and Aaron Nguyen) 
 * @version (Jan 14 2017)
 */
import java.util.*;
public class Player
{
    public ArrayList <Card> hand;
    public boolean doubledDown;
    public boolean busted;
    public Player()
    {
        hand = new ArrayList<Card>();
        doubledDown = false;
        busted = false;
    }
   
    public void setDouble()
    {
        doubledDown = true;
    }
    
    public boolean getDoubledDown()
    {
        return doubledDown;
    }
    
    public int getTotal()
    {
        int total = 0;
        for(int i = 0; i < hand.size() ;i++)
        {
            total += hand.get(i).getVal().getValue();
        }
        return total;
    }
    
    /*
    public boolean getDoubled()
    {
        return doubledDown;
    }
    
    public void setDoubleDown(boolean isDoubled)
    {
        doubledDown = isDoubled;
    }
    */
    //remove, refer to player with instance variable arraylist
    public ArrayList<Card> getHand()
    {
        return hand;
    }
    
    public Iterator iterator()
    {
        return hand.iterator();
    }
        
    public void printCurrPlayer()
    {
        Iterator cardIterator = hand.iterator();
        while (cardIterator.hasNext())
        {
            Card aCard = (Card)cardIterator.next();
            System.out.print(aCard.getVal().getString() + " of " + aCard.getSuit() +"; ");
        }
        System.out.println();
    }
    
    public void printFirstCard()
    {
        if(hand.size() != 0)
        {
            Card aCard = (hand.get(0));
            System.out.println(aCard.getVal().getString() + " of " + aCard.getSuit() +"; ");
        }
        else 
        {
            System.out.println("N/A. Deal first.");
        }
    }
}

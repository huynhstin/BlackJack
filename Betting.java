
/**
 * Write a description of class Bet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Betting
{
    private int bet;
    private int money;
    public Betting()
    {
        bet = 0;
        money = 1000;
    }
    
    public void addBet(int newBet)
    {
        bet = newBet;
    }
    
    public int getBet()
    {
        return bet;
    }
    
    public void setMoney(int newMoney)
    {
        money = newMoney;
    }
    
    public int getMoney()
    {
        return money;
    }
    
    public void lost()
    {
        money -= bet;
    }
    
    public void won()
    {
        money += bet;
    }
}

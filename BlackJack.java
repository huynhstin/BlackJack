/**
 * Blackjack Game
 * 
 * Fix: 
 * split logic; should create a new Player to add in the players isntance variable ArrayList
 *  - Split hand in half by adding Player's hand at [1] to new player
 * 
 * 
 * @author (Justin Huynh and Aaron Nguyen) 
 * @version (Jan 14 2017)
 */
import java.util.*;
public class BlackJack
{
    private ArrayList<Card> deck; //the deck itself
    public ArrayList<Player> players; // the list of players
    public Player dealer;
    public Player aaron;
    
    public BlackJack()
    {
        deck = new ArrayList <Card>();
        fillDeck();
        players = new ArrayList <Player>(); 
        aaron = new Player();
        players.add(aaron);
        dealer = new Player();
    }

    public Player getDealer()
    {
        return dealer;
    }

    public ArrayList <Card> getDeck()
    {
        return deck;
    }
    
    /**
     * Fill in order
     */
    public void fillDeck()
    {
        for(int i = 0; i < 13; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                Card card = new Card(CardValue.values()[i], Suit.values()[j]);
                deck.add(card);
            }
        }
    }
    
    /**
     * Shuffle
     */
    public void shuffleDeck()
    {
        Collections.shuffle(deck);
    }
    
    /**
     * Get top card of deck
     */
    public Card drawCard()
    {
        Card topOfDeck = deck.get(0);
        deck.remove(0);
        return topOfDeck;
    }  
    
    public Iterator iterator()
    {
        return deck.iterator();
    }
    
    public void printDeck() 
    {
        Iterator cardIterator = deck.iterator();
        while (cardIterator.hasNext())
        {
            Card aCard = (Card)cardIterator.next();
            System.out.print(aCard.getVal().getString() + " of " + aCard.getSuit() +"; ");
        }
        System.out.println();
    }
    
    public void deal(Player player, int numberOfCards)
    {
        for(int i = 0; i < numberOfCards; i++) //same as hit but without returning current score
        {
            hit(player);
        }
    }
    
    /**
     * Add top card of deck to player's Player, remove top card of deck 
     * need to add an ending where player busts all his decks
     */
    public void hit(Player player)
    {
        Card topOfDeck = drawCard();
        player.hand.add(topOfDeck);
    }
        
    /**
     * aaron's check21. return a boolean whether or not busted
     */
    public boolean checkBust(Player player) 
    {
        if(player.getTotal() > 21) 
        {
            //checks for potential ace
            for(int i = 0; i < player.hand.size(); i++) 
            {
                if(player.hand.get(i).getVal().getValue() == 11) // ace found; only aces are worth 11
                {
                    Card newAce = new Card(CardValue.values()[13],player.hand.get(i).getSuit()); //13 = ace2
                    player.hand.set(i,newAce);
                    break;
                }
            }
        }
        return(player.getTotal()>21);
    }
    
    public void splitDeck(Player player)  
    {
            Player newPlayer = new Player();
            Card splitter = player.hand.get(1); //second card of first deck
            player.hand.remove(1);
            newPlayer.hand.add(splitter);
            hit(player);
            hit(newPlayer);
            players.add(newPlayer);
    }

    public void doubleDown(Player player)
    {
        if(player.hand.size() == 2 && player.hand.get(0).getVal().getValue() + player.hand.get(1).getVal().getValue() >= 10 && 
        player.hand.get(0).getVal().getValue() + player.hand.get(1).getVal().getValue() <= 11) //Player has 2 cards, which add up to between 9-11
        {
            Card invisible = drawCard();
            invisible.setVisible(false);
            player.hand.add(invisible);
        }
        else
        {
            System.out.println("Conditions not met to double down hand");
        }        
    }
    
    /**
     * I think there's a bunch of redundancies from here down, check to see if it works
     * With the check21s as well 
     **/
     //aaron's
    public void dealersTurn()
    {
        while(dealer.getTotal() < 16)
        {
            hit(dealer); 
        }
        
        if(checkBust(dealer))
        {
            System.out.println("Dealer Busted! You win!");
            return;
        }
        checkWhoWon();
    }
    
    public void checkWhoWon()
    {
       int bestTotal = players.get(0).getTotal();
       for(int i = 0; i < players.size(); i++) // goes through all your hands to find the total that is greatest. or closest to 21
       {
           if(players.get(i).getTotal() > bestTotal && players.get(i).getTotal() <= 21)
           {
               bestTotal = players.get(i).getTotal();
           }
       }
       System.out.print("Your best hand is "+bestTotal+" and dealer's is "+dealer.getTotal()+".");
       if(bestTotal > dealer.getTotal())
       {
           System.out.println(" You win!");
       }
       else
       {
           System.out.println(" You lose!");
       }
    }
    
    public void giveCard(int value,int player)
    {
        Card a = new Card(CardValue.TWO,Suit.SPADES);
        for(int i=0;i<deck.size();i++)
        {
            if(deck.get(i).getVal().getValue()==value)
            {
                a=deck.get(i);
                break;
            }
        }
        players.get(player).hand.add(a);
    }
}

/**
 * Blackjack Game
 * graphics: hit one deck at a time. if you want to move to next deck, click stay.
 * Fix: 
 * split logic; should create a new Player to add in the players isntance variable ArrayList
 *  - Split hand in half by adding Player's hand at [1] to new player
 * 
 * be able to stay for only one hand 
 * @author (Justin Huynh and Aaron Nguyen) 
 * @version (Jan 14 2017)
 */
import java.util.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
public class BlackJack
{
    private ArrayList<Card> deck; //the deck itself
    public ArrayList<Player> players; // the list of players
    public Player dealer;
    public Player aaron;
    private int counter;
    private BufferedImage backImage;
    public int newBestTotal;
    public boolean printedWon;
    public BlackJack()
    {
        deck = new ArrayList<Card>();
        fillDeck();
        shuffleDeck();
        players = new ArrayList<Player>(); 
        aaron = new Player();
        players.add(aaron);
        dealer = new Player();
        counter = 0;
        deal(players.get(0), 2);
        deal(dealer, 2);
        dealer.getHand().get(1).setVisible(false);
        newBestTotal = -99; //impossible number to get 
        printedWon = false;
        try
        {
            backImage = ImageIO.read(new File("BACK.jpg"));
        }
        catch(Exception e){}
    }
    
    public BufferedImage getBackImage()
    {
        return backImage;
    }
    
    public ArrayList<Player> getPlayers()
    {
        return players;
    }
    
    public void incrementCounter()
    {
        if((counter + 1) < players.size())
        {
            counter++;
        }
    }
    
    public int getCounter()
    {
        return counter;
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
        for(int i = 0; i < numberOfCards; i++) 
        {
            hit(player);
        }
    }
    
    public void checkBlackJack() // if you immediately get 21, check conditions. doesnt currently work 
    {
        if(players.get(0).getTotal() == 21 && players.get(0).getHand().size() == 2)
        {
            dealer.getHand().get(1).setVisible(true);
            if(dealer.getTotal() == 21 && dealer.getHand().size() == 2)
            {
                printWon(-4);
                return;
            }
            else
            {
                printWon(-3);
                return;
            }
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
                if(player.hand.get(i).getVal().getValue() == 11 && !player.hand.get(i).isSwitched()) // ace found; only aces are worth 11
                {
                    //Card newAce = new Card(CardValue.values()[13],player.hand.get(i).getSuit()); //13 = ace2
                    //player.hand.set(i , newAce);
                    player.hand.get(i).getVal().setValue(1);
                    player.hand.get(i).switchAce();
                    break;
                }
            }
        }
        return(player.getTotal() > 21);
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
        Card invisible = drawCard();
        invisible.setVisible(false);
        player.setDouble();
        player.hand.add(invisible);
    }
    
    public void dealersTurn()
    {
        dealer.getHand().get(1).setVisible(true);
        if(allBust())
        {
            printWon(-1);
            return;
        }
        while(!checkBust(dealer) && dealer.getTotal() < 16)
        {
            hit(dealer); 
        }
        if(checkBust(dealer))
        {
            printWon(-2);
            return;
        }
        checkWhoWon();
    }
    
    public void checkWhoWon()
    {
        int bestTotal = -1;
        for(int i = 0; i < players.size(); i++)
        {
            if(players.get(i).getTotal() > bestTotal && players.get(i).getTotal() <= 21)
            {
                bestTotal = players.get(i).getTotal();
            }
        }
        printWon(bestTotal);
    }
    
    public void printWon(int bestTotal) 
    {
        printedWon = true;
        newBestTotal = bestTotal;
    }
    
    public void giveCard(int value, int player)
    {
        Card a = new Card(CardValue.TWO, Suit.SPADES);
        for(int i = 0; i < deck.size(); i++)
        {
            if(deck.get(i).getVal().getValue() == value)
            {
                a = deck.get(i);
                deck.remove(a);
                break;
            }
        }
        players.get(player).hand.add(a);
        checkBust(players.get(player));
    }
    
    public void removeCard(int choiceDeck, int choiceCard)
    {
        players.get(choiceDeck).getHand().remove(choiceCard);
    }
    
    public boolean allBust() //if every player in the array is doubled down or busted, return true 
    {
        boolean goToDealer = true;
        for(int i = 0; i < players.size(); i++)
        {
            if(!checkBust(players.get(i)))
            {
                goToDealer = false; 
            }
        }
        return goToDealer;
    }
    
    public void printAll(boolean viewAllDealer)
    {
         if(viewAllDealer)
         {
             System.out.print("Dealer's cards: ");
             dealer.printCurrPlayer();
         }
         else
         {
             System.out.print("Dealer's face up card: ");
             dealer.printFirstCard();
         }
         System.out.println("You: ");
         for(int i = 0; i < players.size(); i++)
         {
             System.out.print("Hand " + (i+1) + ": ");
             players.get(i).printCurrPlayer();
         }
         System.out.println("\nCurrent Hand: "+ (counter+1));
         System.out.println("players.size " +players.size());
         for(int j = 0; j < players.size(); j++)
         {
             if(checkBust(players.get(j)))
             {
                 System.out.println("Hand " +(j+1)+ " busted.");
             }
             if(players.get(j).getDoubledDown())
             {
                 System.out.println("Hand " +(j+1)+ " doubled down.");
             }
         }
    }
}

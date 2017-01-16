/**
 * Blackjack Game
 * 
 * @author (Justin Huynh and Aaron Nguyen) 
 * @version (Jan 14 2017)
 */
import java.util.*;
public class BlackJack
{
    private ArrayList<Card> deck; //the deck itself
    public static Hand playerOne; //first player
    public static Hand firstHalf; //two halves
    public static Hand secondHalf;
    public static Hand dealer;
    public boolean deckSplit;
    public boolean youBusted; //idk if these are useful rn
    public boolean firstBust;
    public boolean secondBust;
    public boolean dealerBusted;
    
    public BlackJack()
    {
        deck = new ArrayList <Card>();
        fillDeck();
        playerOne = new Hand();
        firstHalf = new Hand();
        secondHalf = new Hand();
        dealer = new Hand();
        deckSplit = false;
        youBusted = false;
        dealerBusted = false;
    }
    
    /**
     * Get methods
     * Note: rewrite code to modify instance variables instead
     */
    public Hand getPlayer()
    {
        return playerOne;
    }
    
    public Hand getFirstHalf()
    {
        return firstHalf;
    }
    
    public Hand getSecondHalf()
    {
        return secondHalf;
    }
    
    public Hand getDealer()
    {
        return dealer;
    }
    
    public boolean isDeckSplit()
    {
        return deckSplit;
    }
    
    public ArrayList <Card> getDeck()
    {
        return deck;
    }
    
    /**
     * Kinda useless rn
     */
    public void setDeck(ArrayList <Card> d)
    {
        deck = d;
    }
    
    /**
     * Fill in order
     */
    public void fillDeck()
    {
        for(int i = 0; i < 13; i++)
        {
            CardValue cVal = CardValue.values()[i];
            for(int j = 0; j < 4; j++)
            {
                Card card = new Card(cVal, Suit.values()[j]);
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
    
    public void printDeck() //for testing
    {
        Iterator cardIterator = deck.iterator();
        while (cardIterator.hasNext())
        {
            Card aCard = (Card)cardIterator.next();
            System.out.print(aCard.getVal().getString() + " of " + aCard.getSuit() +"; ");
        }
        System.out.println();
    }
    
    public void deal(Hand player, int numberOfCards)
    {
        for(int i = 0; i < numberOfCards; i++) //same as hit but without returning current score
        {
            Card topOfDeck = deck.get(0);
            player.getHand().add(topOfDeck);
            int cardDrawnVal = topOfDeck.getVal().getValue();
            player.updateTotal(cardDrawnVal);
            deck.remove(0);
            //System.out.println("Current score: " +player.getTotal()); //remove later
        }
    }
    
    /**
     * Add top card of deck to player's hand, remove top card of deck 
     * fix this
     */
    public void hit(Hand player)
    {
        Card topOfDeck = deck.get(0);
        player.getHand().add(topOfDeck);
        deck.remove(0);
        int cardDrawnVal = topOfDeck.getVal().getValue();
        player.updateTotal(cardDrawnVal);
        check21(player);
    }
    
    /**
     * Basic logic for now
     */
    public void check21(Hand player) // needs testing
    {
        if(player.getTotal() > 21)
        {
            boolean isAce = false;
            int index = 0;
            Suit s = Suit.SPADES; //set as default for now
            for(int i = 0; i < player.getHand().size(); i++) // look for 
            {
                if(player.getHand().get(i).getVal().getValue() == 11) // ace found; only aces are worth 11
                {
                    isAce = true;
                    index = i;
                    s = player.getHand().get(i).getSuit(); //update the ace's suit 
                    //player.getHand().remove(i);
                    break;
                }
            }
            if(isAce)
            {   
                int newTot = player.getTotal() - 10; // so now instead of 11, it's 1
                player.setTotal(newTot);
                Card newAce = new Card(CardValue.values()[13], s); //13 = ace2
                player.getHand().set(index, newAce);
                System.out.println("Ace switched to 1. Current score: " +player.getTotal());
            }
            else //bust 
            {
                //System.out.println("Bust, dealer wins. Your Score: " +player.getTotal());
                if(deckSplit)
                {
                    if(player.equals(firstHalf))
                    {
                        firstBust = true;
                        if(!secondBust)
                        {
                            System.out.println("First hand busted, but you still have your second.");
                            return;
                        }
                        else
                        {
                            checkWhoWon();
                        }
                    }
                    else if(player.equals(secondHalf))
                    {
                        secondBust = true;
                        if(!firstBust)
                        {
                            System.out.println("Second hand busted, but you still have your first.");
                            return;
                        }
                        else
                        {
                            checkWhoWon();
                        }
                    }
                }
                else
                {
                    youBusted = true;
                    checkWhoWon();
                    return;
                }
            }
        }
        else
        {
            System.out.println("Current score: " +player.getTotal());
        }
    }
    
    /**
     * Add top card of deck to player's hand, remove top card of deck 
     
    public void hitDealer(Hand dealer, Hand player)
    {
        Card topOfDeck = deck.get(0);
        dealer.getHand().add(topOfDeck);
        deck.remove(0);
        int cardDrawnVal = topOfDeck.getVal().getValue();
        dealer.updateTotal(cardDrawnVal);
        check21Dealer(dealer, player);
    }
    
    
     * Basic logic for now
     *
    public void check21Dealer(Hand dealer, Hand player) // needs testing
    {
        if(dealer.getTotal() > 21)
        {
            boolean isAce = false;
            for(int i = 0; i < player.getHand().size(); i++)
            {
                if(dealer.getHand().get(i).getVal().getValue() == 11) // ace found; only aces are worth 11
                {
                    isAce = true;
                    break;
                }
            }
            if(isAce)
            {   
                int newTot = dealer.getTotal() - 10; // so now instead of 11, it's 1
                dealer.setTotal(newTot);
                System.out.println("Ace switched to 1. Current score: " +dealer.getTotal());
            }
            else
            {
                System.out.println("Dealer busted, you won. Your Score: " +player.getTotal());
                System.out.println("Dealer's score: " +dealer.getTotal()); 
                dealerBusted = true;
            }
        }
        else
        {
            System.out.println("Current score: " +dealer.getTotal());
        }
    }
    */
    
    public void splitDeck() //this still needs testing 
    {
        if(playerOne.getHand().size() == 2 && playerOne.getHand().get(0).getVal().getString().equals(playerOne.getHand().get(1).getVal().getString())) 
        {
            deckSplit = true;
            firstHalf.getHand().add(playerOne.getHand().get(0)); 
            firstHalf.updateTotal(playerOne.getHand().get(0).getVal().getValue()); //update total to add value of player.getHand.get(0).getVal.getValue()
            deal(firstHalf, 1);
            secondHalf.getHand().add(playerOne.getHand().get(1));
            secondHalf.updateTotal(playerOne.getHand().get(1).getVal().getValue());
            deal(secondHalf, 1);
            while(playerOne.getHand().size() > 0) //while there are still objects in player's hand
            {
                playerOne.getHand().remove(0);
            }
        }
        else
        {
            System.out.println("You can't do that. Must have two of the same card");
        }
    }
    
    /**
     * I think there's a bunch of redundancies from here down, check to see if it works
     * With the check21s as well 
     */
    public void dealersTurn()
    {
        System.out.println("Dealer's cards: ");
        dealer.printCurrHand();
        int dealerTot = dealer.getTotal();
        while(dealerTot < 16)
        {
            hit(dealer); 
            int newDealerTot = dealer.getTotal();
            if(newDealerTot > 16) break;
        }
        checkWhoWon();
    }
    
    public void checkWhoWon()
    {
        int playerScore = playerOne.getTotal();
        int dealerScore = dealer.getTotal();
        if(!deckSplit)
        {
            if(!youBusted)
            {
                if(playerScore > dealerScore)
                {
                    System.out.println("You won! Your Score: "+playerOne.getTotal());
                    System.out.println("Dealer's score: " +dealer.getTotal());
                }
                else
                {
                    System.out.println("You lost. Your Score: "+playerOne.getTotal());   
                    System.out.println("Dealer's score: " +dealer.getTotal());
                }
            }
            else
            {
                System.out.print("You busted, dealer wins. Your Score: "+playerOne.getTotal());
                return;
            }
        }
        else //deck is split
        {
            if(firstBust)
            {
                
            }
        }
    }
}

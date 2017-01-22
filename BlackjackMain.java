/**
 * Blackjack runner. 
 * need to implement busted decks
 * make player lose if all decks are busted. player can still hit busted deck
 * do testing for addCard () method
 * once every deck busted, ask to play again.
 * consider: what if all the hands of the player are doubled down
 * use a for loop to check if every hand has busted. if true, then go straight to end game/ you lost
 * 
 * @author (Justin Huynh and Aaron Nguyen) 
 * @version (Jan 14 2017)
 */
import java.util.*;
public class BlackjackMain
{
    public static void main(String[] args)
    {
       Scanner in = new Scanner(System.in);
       boolean program = true;
       boolean continue1;
       while(program)
       {
           continue1 = true;
           BlackJack b = new BlackJack();
           while(continue1)
           {    
               System.out.println("Press [0] to double down.");
               System.out.println("Press [1] to shuffle.");
               System.out.println("Press [2] to view deck.");
               System.out.println("Press [3] to deal."); 
               System.out.println("Press [4] to hit."); //cant hit anymore if busted or if doubled down
               System.out.println("Press [5] to view current hand.");
               System.out.println("Press [6] to split.");
               System.out.println("Press [7] to stand.");
               System.out.println("Press [8] to quit.");
               System.out.println("Press [9] to reset game.");
               System.out.println("Press [10] to hit a specific card");
               int select = in.nextInt();
               switch(select)
               {
                   case 0:
                       if(b.players.size() > 1) //new hit
                       {
                           System.out.println("Which deck do you wish to double down?");
                           int a = in.nextInt() - 1;
                           b.doubleDown(b.players.get(a));
                       }
                       else
                       {
                           b.doubleDown(b.players.get(0));
                       }
                       break;
                   case 1: // shuffle
                       b.shuffleDeck();
                       break; 
                   case 2: // print deck
                       b.printDeck();
                       break;
                   case 3: //deal
                       b.deal(b.dealer, 2);
                       b.deal(b.players.get(0), 2); // give each player two cards
                       break;
                   case 4: 
                       int a = 0;
                       if(b.players.size() > 1)
                       {
                           System.out.println("Which deck do you wish to hit?");
                           a = in.nextInt() - 1;
                       }
                          
                       if(b.checkBust(b.players.get(a)))
                       {
                           System.out.println("Deck busted. Choose a different deck");
                       }
                       else if(b.players.get(a).getDoubledDown())
                       {
                           System.out.println("Doubled down, can't hit this deck.");
                       }
                       else
                       {
                           b.hit(b.players.get(a));
                       }
   
                       System.out.print("Dealer: ");
                       System.out.println(b.dealer.getTotal());
                       System.out.println("You: ");
                       for(int i = 0; i < b.players.size(); i++)
                       {
                          System.out.println("Hand " + (i+1) + " : "+b.players.get(i).getTotal());
                       }
                       break;                
                   case 5: //view hands of all players
                       System.out.print("Dealer's face up card: ");
                       b.dealer.printCurrPlayer();
                       for(int i = 0; i < b.players.size(); i++)
                       {
                           System.out.print("Hand " + (i+1) + ": ");
                           b.players.get(i).printCurrPlayer();
                       }
                       break;
                   case 6: //split deck
                       if(b.players.size() > 1)
                       {
                           System.out.println("Which deck do you wish to split?");
                           int deckChoice = in.nextInt() - 1;
                           if(b.players.get(deckChoice).getHand().size() == 2 && b.players.get(deckChoice).getHand().get(0).getVal().getString().equals(b.players.get(deckChoice).getHand().get(1).getVal().getString())) 
                           {
                               b.splitDeck(b.players.get(deckChoice));
                           }
                           else
                           {
                               System.out.println("Deck cannot be split.");
                           }   
                       }
                       else
                       {
                           if(b.players.get(0).getHand().size() == 2 && b.players.get(0).getHand().get(0).getVal().getString().equals(b.players.get(0).getHand().get(1).getVal().getString()))
                           {
                               b.splitDeck(b.players.get(0));
                           }
                           else
                           {
                               System.out.println("Deck cannot be split.");
                           }
                       }
                       break;
                   case 7: // when you stand it's dealer's turn
                       b.dealersTurn(); 
                       Scanner in1 = new Scanner(System.in);
                       System.out.println("Play again? y/n");
                       String choice = in1.nextLine();
                       if(choice.equals("y"))
                       {
                           continue1=false;
                           //continue;
                       }
                       else
                       {
                           continue1=false;
                           program=false;
                       }
                       break;
                   case 8: //quit
                       continue1 = false;
                       program = false;
                       break;
                   case 9://reset game
                       continue1 = false;
                       break;
                   case 10://find specific card
                       System.out.println("What value?");
                       int value = in.nextInt();
                       if(b.players.size() > 1)
                       {
                           System.out.println("Which deck?");
                           int choiceDeck = in.nextInt() - 1;
                           b.giveCard(value , choiceDeck);
                       }
                       else
                       {
                           b.giveCard(value , 0);
                       }
                       break;
                   default:
                       System.out.println("Pick a choice that is on the list");
                       break;
               }
            }
        }
    }
}

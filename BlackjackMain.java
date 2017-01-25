/**
 * Blackjack runner. 
 * make player lose if all decks are busted. player can still hit busted deck. no more option to hit busted,doubled deck. 
 * stay ends for the player, not the game and moves on to next player. once all players stayed/busted/doubled, game ends.
 * @author (Justin Huynh and Aaron Nguyen) 
 * @version (Jan 14 2017)
 */
import java.util.*;
public class BlackjackMain
{
    public static void main(String[] args)
    {
       Scanner in = new Scanner(System.in);
       quitter: while(true)
       {
           BlackJack b = new BlackJack();
           resetter: while(true)
           {    
               System.out.println("Press [0] to double down.");// increment cursor
               System.out.println("Press [1] to shuffle.");
               System.out.println("Press [2] to view deck.");
               System.out.println("Press [3] to deal."); 
               System.out.println("Press [4] to hit."); //cant hit anymore if busted or if doubled down // increment cursor
               System.out.println("Press [5] to view current hand."); 
               System.out.println("Press [6] to split.");
               System.out.println("Press [7] to stand.");//increment cursor
               System.out.println("Press [8] to quit."); 
               System.out.println("Press [9] to reset game.");
               System.out.println("Press [10] to hit a specific card");
               System.out.println("Press [11] to remove a specific card");
               System.out.println("Press [12] to surrender.");
               int select = in.nextInt();
               switch(select)
               {
                   case 0:
                       Player p = b.players.get(b.getCounter());
                       if(p.hand.size() == 2)
                       {
                           int total = p.hand.get(0).getVal().getValue() + p.hand.get(1).getVal().getValue();
                           if(total >= 10 && total <= 11)
                           {
                               b.doubleDown(p); // double down the current player, aslong as condition met
                           }
                           else
                           {
                               System.out.println("Conditions not met to double down hand");
                               break;
                           }
                           
                           if(b.getCounter() == b.players.size() - 1) // if counter is equal to the number of players currenty playing, meaning that it's the end
                           {
                               System.out.println("You can no longer hit. Dealer's turn. \n");
                               b.dealersTurn();
                               b.printAll(true);
                               Scanner in2 = new Scanner(System.in);
                               System.out.println("\nPlay again? Y/N");
                               String selec = in2.nextLine();
                               if(selec.equalsIgnoreCase("y"))
                               {
                                   break resetter;
                               }
                               else
                               {
                                   break quitter;
                               }
                           }
                           else 
                           {
                               b.incrementCounter();
                           }
                       }
                       else
                       {
                           System.out.println("Deal first.");
                           break;
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
                       if(b.players.get(0).getTotal() == 21 && b.players.get(0).getHand().size() == 2)
                       {
                           Scanner a = new Scanner(System.in);
                           if(b.dealer.getTotal() == 21 && b.dealer.getHand().size() == 2)
                           {
                               b.printWon(-4);
                               b.printAll(true);
                               System.out.println("\nPlay again? Y/N");
                               String select1 = a.nextLine();
                               if(select1.equalsIgnoreCase("y"))
                               {
                                   break resetter;
                               }
                               else
                               {
                                   break quitter;
                               }
                           }
                           else
                           {
                               b.printWon(-3);
                               b.printAll(true);
                               System.out.println("\nPlay again? Y/N");
                               String select1 = a.nextLine();
                               if(select1.equalsIgnoreCase("y"))
                               {
                                   break resetter;
                               }
                               else
                               {
                                   break quitter;
                               }
                           }
                       }
                       break;
                   case 4: //fix this 
                       b.hit(b.players.get(b.getCounter()));
                       if(b.checkBust(b.players.get(b.getCounter())))
                       {
                           System.out.println("Deck busted.");
                           System.out.println("players.size " +b.players.size());
                           if(b.getCounter() == b.players.size() - 1) // zero indexing 
                           {
                               System.out.println("You can no longer hit. Dealer's turn. \n");
                               b.dealersTurn();
                               b.printAll(true);
                               Scanner in3 = new Scanner(System.in);
                               System.out.println("\nPlay again? Y/N");
                               String select1 = in3.nextLine();
                               if(select1.equalsIgnoreCase("y"))
                               {
                                   break resetter;
                               }
                               else
                               {
                                   break quitter;
                               }
                           }
                           else
                           {
                               b.incrementCounter();
                           }
                       }
                       b.printAll(false);
                       break;                
                   case 5: //view hands of all players
                       b.printAll(false);
                       break;
                   case 6: //split deck
                       int deckChoice = 0; 
                       if(b.players.size() > 1)
                       {
                           System.out.println("Which deck do you wish to split?");
                           deckChoice = in.nextInt() - 1;
                       }
                       if(b.players.get(deckChoice).getHand().size() == 2 && b.players.get(deckChoice).getHand().get(0).getVal().getString().equals(b.players.get(deckChoice).getHand().get(1).getVal().getString())) 
                       {
                           b.splitDeck(b.players.get(deckChoice));
                       }
                       else
                       {
                           System.out.println("Deck cannot be split.");
                       } 
                       break;
                   case 7: // change so that stand only applies to the deck not the game
                       if(b.getCounter() == b.players.size() - 1)
                       {
                           b.dealersTurn(); 
                           b.printAll(true);
                           Scanner in1 = new Scanner(System.in);
                           System.out.println("\nPlay again? y/n");
                           String choice = in1.nextLine();
                           if(choice.equals("y"))
                           {
                               break resetter;
                           }
                           else
                           {
                               break quitter;
                           }
                       }
                       else
                       {
                           b.incrementCounter();
                       }
                       break;
                   case 8: //quit
                       break quitter;
                   case 9://reset game
                       break resetter;
                   case 10://find specific card
                       System.out.println("What value?");
                       int value = in.nextInt();
                       if(b.players.size() > 1)
                       {
                           System.out.println("Which deck?");
                           int choiceDeck = in.nextInt() - 1;
                           b.giveCard(value, choiceDeck);
                       }
                       else
                       {
                           b.giveCard(value, 0);
                       }
                       break;
                   case 11:
                       if(b.players.size() > 1)
                       {
                           System.out.println("Which deck?");
                           int choiceDeck = in.nextInt() - 1; 
                           System.out.println("Which card number in the deck?");
                           int choiceCard = in.nextInt() - 1;
                           b.removeCard(choiceDeck, choiceCard);
                       }
                       else
                       {
                           System.out.println("Which card number in the deck?");
                           int choiceCard = in.nextInt() - 1;
                           b.removeCard(0, choiceCard); 
                       }
                       break;
                   case 12: 
                       if(b.players.size() == 1 && b.players.get(0).hand.size() == 2)
                       {
                           Scanner in1 = new Scanner(System.in);
                           System.out.println("Surrendered.\nPlay again? y/n");
                           String choice = in1.nextLine();
                           if(choice.equals("y"))
                           {
                               break resetter;
                           }
                           else
                           {
                               break quitter;
                           }
                       }
                       else
                       {
                           System.out.println("Cannot surrender now.");
                           break;
                       }
                   default:
                       System.out.println("Pick a choice that is on the list");
                       break;
               }
            }
        }
    }
}

/**
 * Write a description of class Graphics here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.awt.event.*;
public class GraphicsBlackJack extends JPanel
{
    private final int FRAME_WIDTH;
    private final int FRAME_HEIGHT;
    private BlackJack a;
    private Betting d; 
    public JFrame frame;
    public boolean canUpdate; // check if its still possible to update the money 
    public GraphicsBlackJack()
    {
        a = new BlackJack();
        a.checkBlackJack();
        d = new Betting(); // new betting class
        frame = new JFrame(); 
        frame.setBackground(new Color(0, 145, 58));// jframe now an instance variable, so i can make dialogs outside of paintcomponent
        FRAME_WIDTH = 1200;
        FRAME_HEIGHT = 1000;
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Blackjack");
        canUpdate = true; // set true by default
    }
    
    public void reset() // create a new blackjack game to reset 
    {
        a = new BlackJack(); 
        a.checkBlackJack();
        canUpdate = true; 
        repaint();
    }
    
    public void doBets()
    {
        frame.setVisible(false); // hide the frame, so you cant see what the new cards are before betting 
        if(d.getMoney() <= 0) // if youre out of money
        { 
            JOptionPane.showMessageDialog(frame, "You're out of money.", "You're broke", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            return;
        }
        String stringBet; // joptionpane returns a string
        stringBet = JOptionPane.showInputDialog(frame, "You currently have $" +d.getMoney()+". \nHow much would you like to bet?", "Place bet", JOptionPane.PLAIN_MESSAGE);
        if(stringBet == null) // if you press cancel, exit
        {
            System.exit(0);
        }
        int bet = Integer.parseInt(stringBet); // cast to int 
        if(bet > d.getMoney()) 
        {
            JOptionPane.showMessageDialog(frame, "You can't bet that much." , "Error",  JOptionPane.ERROR_MESSAGE);
            doBets();
            return;
        }
        if(bet < 1)
        {
            JOptionPane.showMessageDialog(frame, "You can't bet that little." , "Error",  JOptionPane.ERROR_MESSAGE);
            doBets();
            return;
        }
        d.addBet(bet);
        frame.setVisible(true);
    }
    
    public void hit()
    {
        if(!a.checkBust(a.players.get(a.getCounter()))) // if not busted
        {
            a.hit(a.players.get(a.getCounter())); // then hit
            if(a.checkBust(a.players.get(a.getCounter())) && a.getCounter() == a.players.size() - 1) //now after hit, if it is busted and the last one, dealers turn
            {
                a.dealersTurn();
            }
        }
        else 
        {
            a.incrementCounter();
        }
    }
    
    public void split()
    {
        if(a.players.get(a.getCounter()).getHand().size() == 2 && a.players.get(a.getCounter()).getHand().get(0).getVal().getString().equals(a.players.get(a.getCounter()).getHand().get(1).getVal().getString()))
        {        
            a.splitDeck(a.players.get(a.getCounter()));
            d.addBet(2 * d.getBet());
        }
    }
    
    public void updateMoney()
    {
        canUpdate = false;
        if((a.newBestTotal == a.dealer.getTotal()) || a.newBestTotal == -4)
        {
            return;
        }
        else if(a.newBestTotal == -2 || a.newBestTotal == -3 || (a.newBestTotal > a.dealer.getTotal() && a.newBestTotal <= 21))
        {
            d.won();
            return;
        }
        else
        {
            d.lost();
            return;
        }
    }
    
    public void displayWinner(Graphics g)
    {
        for(int i = 0; i < a.players.size(); i++) // when its time to display winner, make doubled down cards visible again
        {
            if(a.players.get(i).getDoubledDown())
            {
                a.players.get(i).getHand().get(2).setVisible(true);
            }
        }
        Graphics2D g2 = (Graphics2D)g;
        if(a.newBestTotal == -1)
        {
            g2.drawString("All decks busted. You lose!", (FRAME_WIDTH / 2) - 75, 360);
            g2.setColor(Color.white);
            return;
        }
        if(a.newBestTotal == -2)
        {
            g2.drawString("Dealer Busted! You win!", (FRAME_WIDTH / 2) - 75, 360);
            g2.setColor(Color.white);
            return;
        }
        if(a.newBestTotal == -3)
        {
            g2.drawString("Blackjack! You win!", (FRAME_WIDTH / 2) - 75 , 360);
            g2.setColor(Color.white);
            return;
        }
        g2.drawString("Your best hand is "+a.newBestTotal+" and dealer's is "+a.dealer.getTotal()+". ",  (FRAME_WIDTH / 2) - 75, 348);
        g2.setColor(Color.white);
        if(a.newBestTotal > a.dealer.getTotal() && a.newBestTotal <= 21)
        {
            g2.drawString("You win!",  (FRAME_WIDTH / 2), 360);
            g2.setColor(Color.white);
            return;
        }
        else if((a.newBestTotal == a.dealer.getTotal()) || a.newBestTotal == -4)
        {
            g2.drawString("Push!",  (FRAME_WIDTH / 2), 360);
            g2.setColor(Color.white);
            return;
        }
        else
        {
            g2.drawString("You lose!",  (FRAME_WIDTH / 2), 360);
            g2.setColor(Color.white);
            return;
        }
    }
    
    public void stay()
    {
        if(a.getCounter() == a.players.size() - 1)
        {
            a.dealersTurn();
            return;
        }
        else
        {
            a.incrementCounter();
        }
    }

    public void doubleDown()
    {
        Player p = a.players.get(a.getCounter()); // last player
        int total = p.hand.get(0).getVal().getValue() + p.hand.get(1).getVal().getValue();
        if(p.hand.size() == 2 && total >= 10 && total <= 11)
        {
            a.doubleDown(p);
            d.addBet(2 * d.getBet());
            if(a.getCounter() == a.players.size() - 1) // if counter is equal to the number of players currenty playing, meaning that it's the end
            {
                return;
            }
            else
            {
                a.incrementCounter();
            }
        }
    }
    
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        frame.setBackground(new Color(0, 145, 58));
        //g2.drawString("PLAYER", FRAME_WIDTH / 2, FRAME_HEIGHT - 400); // took out player indicator
        g2.setColor(Color.white);
        g2.drawString("DEALER", FRAME_WIDTH / 2, 65);
        for(int i = 0; i < a.dealer.getHand().size(); i++)
        {
            if(a.dealer.getHand().get(i).getVisible())
            {
                //g2.drawImage(a.dealer.getHand().get(i).getImage(), (FRAME_WIDTH/2)-50, 75+i*50, null);
                g2.drawImage(a.dealer.getHand().get(i).getImage(), ((FRAME_WIDTH / 8) - 35) +50*i, 85, null); // modified the above line to go horizontal 
            }
            else
            {
                g2.drawImage(a.getBackImage(), ((FRAME_WIDTH / 8) - 35) +50*i, 85, null);
            }
        }
        
        for(int i = 0; i < a.getPlayers().size(); i++)
        {
            for(int j = 0; j < a.getPlayers().get(i).getHand().size(); j++)
            {
                if(a.players.get(i).getHand().get(j).getVisible())
                {
                    g2.drawImage(a.getPlayers().get(i).getHand().get(j).getImage(), 100+300*i, 400+50*j, null);
                }
                else
                {
                    g2.drawImage(a.getBackImage(), 100+300*i, 400+50*j, null);
                }
                
                if(i == a.getCounter()) // print out current hand indicator
                { 
                    if(a.checkBust(a.players.get(i))) // if current hand is busted, say so 
                    {
                        g2.drawString("Deck Busted", 125+300*i, 375);
                        g2.setColor(Color.white);
                        a.incrementCounter();
                    }
                    else 
                    {
                        g2.drawString("Current Hand" , 125 + 300*i, 375); //otherwise indicate current hand
                        g2.setColor(Color.white);
                    }
                } 
                else if(a.checkBust(a.players.get(i)))
                {
                    g2.drawString("Deck Busted", 125+300*i, 375);
                    g2.setColor(Color.white);
                    a.incrementCounter();
                }
                
            }
            if(a.players.get(i).getDoubledDown() && !a.printedWon)
            {
                int total = a.getPlayers().get(i).getTotal(); // the total hand value
                int lastCard = a.getPlayers().get(i).hand.get(2).getVal().getValue(); // the last card valu e
                String b = (total - lastCard) + "";
                g2.drawString("TOTAL: "+b+" + ?", 140+300*i , 390);
                g2.setColor(Color.white);
            }
            else 
            {
                String b = a.getPlayers().get(i).getTotal()+"";
                g2.drawString("TOTAL: "+b, 140+300*i , 390);
                g2.setColor(Color.white);
            }
        }
        if(a.printedWon)
        {
            displayWinner(g2);
        }
        if(a.printedWon && canUpdate)
        {
            updateMoney();
        }
        g2.drawString("MONEY: $" +d.getMoney(), FRAME_WIDTH / 2 + 400, 65);
        g2.setColor(Color.white);
        g2.drawString("BET: $" +d.getBet(), FRAME_WIDTH / 2 + 400, 80);
        g2.setColor(Color.white);
        repaint();
    }
    
    public static void main (String [] args) throws InterruptedException
    {        
        GraphicsBlackJack c = new GraphicsBlackJack();
        JButton hit = new JButton("Hit");
        c.add(hit);
        JButton stay = new JButton("Stay");
        c.add(stay);
        JButton doubleDown = new JButton("Double Down");
        c.add(doubleDown);
        JButton split = new JButton("Split");
        c.add(split);
        JButton reset = new JButton("Play Again");
        c.add(reset);
        JButton exit = new JButton("Exit");
        c.add(exit);
        class HitButtonListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                if(!c.a.printedWon && !c.a.players.get(c.a.getCounter()).getDoubledDown())
                {
                    c.hit();
                    c.a.switchAce(c.a.players.get(0));
                    JPanel newPanel = new JPanel();
                    c.frame.add(newPanel);
                    newPanel.setBackground(new Color(0, 145, 58));
                    c.frame.setVisible(true);
                }
            }
        }
        
        class StayButtonListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                c.stay();
                JPanel newPanel = new JPanel();
                c.frame.add(newPanel);
                newPanel.setBackground(new Color(0, 145, 58));
                c.frame.setVisible(true);
                c.repaint();
                return;
           }
        }
        
        class SplitButtonListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                c.split();
                JPanel newPanel = new JPanel();
                c.frame.add(newPanel);
                newPanel.setBackground(new Color(0, 145, 58));
                c.frame.setVisible(true);
            }
        }
        
        class DoubleButtonListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                c.doubleDown();
                c.a.switchAce(c.a.players.get(0));
                JPanel newPanel = new JPanel();
                c.frame.add(newPanel);
                newPanel.setBackground(new Color(0, 145, 58));
                c.frame.setVisible(true);
            }
        }
        
        class ResetButtonListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                if(c.a.printedWon) // you can only reset the game after you have checked who won so you cant just reset while playing 
                {
                    c.reset(); 
                    c.doBets();
                    c.repaint();
                    JPanel newPanel = new JPanel();
                    c.frame.add(newPanel);
                    newPanel.setBackground(new Color(0, 145, 58));
                    c.frame.setVisible(true);
                }
            }
        }
        
        class ExitButtonListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event) //exit button
            {
                System.exit(0);
            }
        }

        ActionListener hitListener = new HitButtonListener();
        ActionListener splitListener = new SplitButtonListener();
        ActionListener stayListener = new StayButtonListener();
        ActionListener doubleListener = new DoubleButtonListener();
        ActionListener resetListener = new ResetButtonListener();
        ActionListener exitListener = new ExitButtonListener();
        hit.addActionListener(hitListener);
        split.addActionListener(splitListener);
        stay.addActionListener(stayListener);
        doubleDown.addActionListener(doubleListener);
        reset.addActionListener(resetListener);
        exit.addActionListener(exitListener);
        c.frame.add(c);
        c.frame.setVisible(true);

        c.doBets();
    }
}

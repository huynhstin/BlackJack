/**
 * Card which has a CardValue and a Suit
 * 
 * @author (Justin Huynh and Aaron Nguyen) 
 * @version (Jan 14 2017)
 */
import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
public class Card
{
    public CardValue cardVal;
    public Suit suit;
    public boolean visible;
    private BufferedImage image;
    private boolean aceSwitched;
    public Card(CardValue v, Suit s)
    {
        cardVal = v;
        suit = s;
        visible = true;
        aceSwitched = false;
        try
        {
            image = ImageIO.read(new File(cardVal.getString() + "_" + suit.getName() +".jpg")); // use cardval, suit enums to load image
        }
        catch(Exception e){}
    }
    
    public boolean getVisible()
    {
        return visible;
    }
    
    public void setVisible(boolean visiblity)
    {
        visible = visiblity;
    }
    
    public BufferedImage getImage()
    {
        return image;
    }
    
    public void switchAce()
    {
        aceSwitched=true;
    }
    
    public boolean isSwitched()
    {
        return aceSwitched;
    }
    
    public Suit getSuit()
    {
        return suit;
    }
    
    public CardValue getVal()
    {
        return cardVal;
    }
    
    public void setValue(CardValue newVal)
    {
        cardVal = newVal;
    }
    
    public void setSuit(Suit newSuit)
    {
        suit = newSuit;
    }
}

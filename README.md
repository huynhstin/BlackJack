# BlackJack
Blackjack game for CS

https://www.youtube.com/watch?v=8zmRADamXJU

http://healy.econ.ohio-state.edu/blackjack/table/dealing.html 

http://www.blackjack-direct.com/blackjack-rules.html

To Do:

  Ace logic: (done ish, needs testing)
    
    -> Switch from 1 to 11 as needed
    
  Dealer (done ish, needs testing)
  
    -> One of the dealer's cards is visible
    
    -> AI to keep hitting 
    
  Dealing cards: (done, needs testing)
  
    -> Player gets two cards at the start
    
  View hands: (done ish, needs testing)
  
    -> Dealer has one visible card.
    
    -> But, when it's the dealer's turn (when player is done) all cards are visible
    
  Stand logic: (done, test)
  
    -> When finished, move on to dealer
    
  Split deck: (done ish, needs testing)
  
    -> When hand has two of the same cards, split into two hands
    
    -> Only when initially dealt
    
    -> Each hand gets dealt one more card, then proceed as normal
      
      -> Can pick which of the two split hands to hit with
    
  Other logic: 
  
    -> Dealer must hit if less than 16, stand if at least 17
    
  JFrame/Animation
  
    -> Way later
    
  Note:
  
  The methods in BlackJack that take (Hand dealer, Hand player) need to be reworked to consider the case where the deck is split
    
  

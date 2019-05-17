package finalGame.objects;
/*
 * Author:
 * 		Zach
 * 		Sean
 * 		Matthias
 */
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Deck {
	
	public static final List<Card> DECK = new LinkedList<Card>();
	public static final List<Card> DISCARD = new LinkedList<Card>();
	
	public Deck() {
		for (CardEnum color : CardEnum.cardColors()) { //add all cards to the deck
			if (color != CardEnum.BLACK) {
				for (CardEnum nomination : CardEnum.cardNominations()) { //for all colors except black
					DECK.add(new Card(color, nomination)); //add card to deck
					if (nomination != CardEnum.ZERO) { //if not zero (only one zero per color)
						DECK.add(new Card(color, nomination));  //add another card to the deck
					}
				}
			}
			else { //for only black cards
				for (CardEnum nomination : CardEnum.blackNominations()) {
					DECK.add(new Card(color, nomination)); //add four of each kind to the deck
					DECK.add(new Card(color, nomination));
					DECK.add(new Card(color, nomination));
					DECK.add(new Card(color, nomination));
				}
			}
		}		
		this.shuffleDeck();
		
		DISCARD.add(this.placeFirstCard());
	}
		
	private void shuffleDeck() { //shuffles deck duh
		Collections.shuffle(DECK, new Random());
		Collections.shuffle(DECK);
	}
	
	private Card placeFirstCard() {				//helper function for deck. Only to add first
		Card temp = DECK.get(DECK.size() - 1); // card to discard after deck initialization
		if (temp.getColor() != CardEnum.BLACK) {	
			DECK.remove(DECK.size() - 1);
			return temp;
		}
		else {
			DECK.remove(DECK.size() - 1);
			DECK.add(0, temp);
			return placeFirstCard();
		}
	}
	
	public Card getNextCard() {
		if (DECK.isEmpty()) { //if deck is empty move add all discarded cards and shuffle
			Card topCard = DISCARD.get(DISCARD.size() - 1);
			DISCARD.remove(DISCARD.size() - 1);
			
			for (Card card : DISCARD) {
				if (card.getNomination() == CardEnum.PLUS_FOUR ||
					card.getNomination() == CardEnum.WILD)
				{ //if black card then set color back to black since its changed in discard
					card.setColor(CardEnum.BLACK);
				}
				
				DECK.add(card);
			}
					
			DISCARD.removeAll(DISCARD);
			DISCARD.add(topCard);
			
			this.shuffleDeck();
		}
		Card temp = DECK.get(DECK.size() - 1);
		DECK.remove(DECK.size() - 1);
		return temp;
	}
	
	public Card getLastCard() {
		return DISCARD.get(DISCARD.size() - 1);
	}
	
	public boolean discard(Card card) { //discard non black cards
		if (card.getColor() == DISCARD.get(DISCARD.size() - 1).getColor() ||
			card.getNomination() == DISCARD.get(DISCARD.size() - 1).getNomination()) 
		{ //if card is same color or number as last discard
			DISCARD.add(card);
			return true;
		}
		return false;
	}
	
	public void discard(Card card, CardEnum newColor) { //discard only black cards
		card.setColor(newColor); //change black card to new color
		DISCARD.add(card);
	}
	
	public int numCards() {
		return DECK.size();
	}
	
	public int discardCards() {
		return DISCARD.size();
	}
	
	/*
	public boolean isPlayable(Card card) { //only use this method if you only want to check if 
		if (card.getColor() == CardEnum.BLACK ||    // a card is playable without discarding it
			card.getColor() == DISCARD.get(DISCARD.size() - 1).getColor() ||
			card.getNomination() == DISCARD.get(DISCARD.size() - 1).getNomination()) 
		{ //if card is black or same color or number as last discard
			return true;
		}
		return false;
	}
	*/
}

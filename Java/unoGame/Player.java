package finalGame.objects;
/*
 * Author:
 * 		Sean
 * 		Richard
 * 		Zach
 * 		Harvey
 * 		Richard
 * 
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {

	private List<Card> hand = new ArrayList<Card>();
	private String name;
	private static Scanner scanner = new Scanner(System.in);
	private static Deck deck;

	public Player(String name, Deck theDeck) {
		this.name = name;
		deck = theDeck;
		for(int i = 0; i < 7; i++) {
			hand.add(deck.getNextCard());
		}
	}
	
	public void play() {
		
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		boolean card_Check = false;
		
		for (Card temp_Card : hand) {
			if (temp_Card.compare(deck.getLastCard()) == true) {
				card_Check = true;
				break;
			}
		}
		
		System.out.println(name + "'s turn\n");
		try { Thread.sleep(4000); } catch (InterruptedException e) { e.printStackTrace(); }
		
		if (card_Check == false) {
			System.out.println("Your hand does not have a playable card. Drawing card...\n");
			
			while(true) {
				Card addedCard = deck.getNextCard();
				System.out.println("You drew: \n" + addedCard.toString());
				hand.add(addedCard);
				deck.discard(addedCard);
				
				try { Thread.sleep(4000); } catch (InterruptedException e) { e.printStackTrace(); }
				
				if (addedCard.compare(deck.getLastCard())) { break; }
			}
		}
		
		Card card = hand.get(promptCard());
		
		if (card.getColor() == CardEnum.BLACK) {
			deck.discard(card, promptColor());
			hand.remove(card);
		}
		else {
			if (deck.discard(card)) {
				hand.remove(card);
			}
			else {
				System.out.println("You cannot play this card");
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				play();
			}
		}
	}
	
	private int promptCard() {
		displayHand();
		System.out.println("The last played card is:\n" + deck.getLastCard().toString());
		System.out.println("\nPlease enter the card you would like to play (-1 to draw):");
		
		int toPlay = 0;
		try {
			toPlay = Integer.parseInt(scanner.nextLine());
		}
		catch(Exception ex) {
			System.out.println("Invalid input");
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return promptCard();
		}
		
		if (toPlay != -1) {
			toPlay--;
			if (toPlay < 0 || toPlay >= hand.size()) {
				System.out.println("Invalid number");
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return promptCard();
			}
			else {
				return toPlay;
			}
		}
		else {
			hand.add(deck.getNextCard());
			return promptCard();
		}
	}
	
	private CardEnum promptColor() {
		System.out.println("Please enter the color you would like your card to be:");
		
		String playerColor = scanner.nextLine();
		
		try {
			switch (CardEnum.valueOf(playerColor.toUpperCase())) {
			case GREEN:
				return CardEnum.GREEN;
			case BLUE:
				return CardEnum.BLUE;
			case RED:
				return CardEnum.RED;
			case YELLOW:
				return CardEnum.YELLOW;
			default:
				
			}
			System.out.println("Invalid card color");
			return promptColor();
		}
		catch(Exception ex) {
			System.out.println("Invalid card color");
			return promptColor();
		}
	}
	
	/*
	public boolean play(Card fromHand, Deck theDeck) {
		if (hand.contains(fromHand)){	
			hand.remove(fromHand);
			theDeck.playCard(fromHand);
			return true;
		}
		return false;
	}//end play v1
	
	public boolean play(int color, int nomination, Deck theDeck) {
		for (Card fromHand : hand) {
			if (fromHand.getColor() == color && fromHand.getNomination() == nomination) {
				hand.remove(fromHand);
				theDeck.playCard(fromHand);
				return true;
			}	
		}
		return false;
	}//end play v2
	*/

	public void displayHand() {
		int i = 1;
		for (Card c : hand) {
			System.out.println(i++ + ": " + c.toString());
		}
	}
	
	public void draw() {
		hand.add(deck.getNextCard());
	}
	
	public void draw(int n) {
		for (int i = 0; i < n; i++) {
			hand.add(deck.getNextCard());
		}
	}
	
	public List<Card> getHand() {
		return hand;
	}
	
	public boolean hasCards() {
		if (hand.size() > 0) {
			return true;
		}
		return false;
	}
	
	public boolean hasUno() {
		if (hand.size() == 1) {
			return true;
		}
		return false;
	}
	
	public String getName() {
		return name;
	}
	
	/*
	public static void main(String[] args) {
		Deck deck = new Deck();
		
		Player p1 = new Player("p1", deck);
		
		System.out.println(deck.numCards());
		System.out.println(deck.discardCards());
		
		p1.play();
		
		System.out.println(deck.numCards());
		System.out.println(deck.discardCards());
	}
	*/
	
	/*
	//tester
	public static void main(String[] args) {
		Deck thingy = new Deck();
		Player p1 = new Player("Sean", thingy);
		p1.displayHand();
		System.out.println("\nTop card of discard pile is: \n"+ thingy.getLastCard());
		for (Card c : p1.hand) {
			if(c.isPlayable(thingy.getLastCard())) {
				p1.play(c, thingy);
				System.out.println("Played: " +c.toString());
				break;
			}
		}
		System.out.println("\nTop card of discard pile is: \n"+ thingy.getLastCard());

	}
	*/
	
}

package finalGame.tester;
/*
 * Authors:
 * 		Harvey
 * 		Matthias
 * 		Zach
 * 		Richard
 * 	
 * 
 */
import java.util.*;
import finalGame.objects.*;

public class Tester_Game {
	
	// Game Setup
	private static Deck deck = new Deck();
	private static PlayerLinkedList players = new PlayerLinkedList();
	private static boolean direction = true;
	
	private static void director() {
		
		Player nextPlayer;
		// "nextPlayer" -> true = clockwise || false = counter-clockwise
		if (direction) {
			nextPlayer = players.next().getPlayer();
		} else {
			nextPlayer = players.last().getPlayer();
		}
		
		// card conditions
		switch (deck.getLastCard().getNomination()) {
		case PLUS_TWO:
			nextPlayer.draw(2);
			break;
		case PLUS_FOUR:
			nextPlayer.draw(4);
			break;
		case SKIP:
			if (direction) {
				players = players.next();
			} else {
				players = players.last();
			} break;
		case REVERSE:
			direction = !direction;
			break;
		default:
			break;
		}
	}
	
	public static void main(String[] args) {
		boolean winCondition = false;
		Scanner player_Input = new Scanner(System.in);
		
		System.out.printf(" _    _ _   _  ____  \n" + 
						  "| |  | | \\ | |/ __ \\ \n" + 
						  "| |  | |  \\| | |  | |\n" +
						  "| |  | | . ` | |  | |\n" + 
						  "| |__| | |\\  | |__| |\n" + 
						  " \\____/|_| \\_|\\____/ \n");
		
		int playerCount = 0;
		
		// main menu
		while (playerCount < 2 || playerCount > 6) {
			System.out.print("How many players? (2-6) (-1 to quit): ");
			
			try {
				playerCount = player_Input.nextInt();
			} catch (Exception e){
				System.out.println("Invalid input. Please try again.");
				player_Input.nextLine();
				playerCount = 0;
			}
			
			if (playerCount == -1) {
				System.exit(0);
			}
			
			System.out.println();
		}
		
		// player setup
		
		String temp_Name;
		
		for (int i = 0; i < playerCount; i++) {
			System.out.printf("Player %d's Name: ", i+1);
			temp_Name = player_Input.next();
			
			players.add(new Player(temp_Name, deck));
		}
		
		// gameplay
		while (!winCondition) {
			for (int i = 0; i < playerCount; i++) {
				// check if player doesnt have cards, then change "winCondition" to true
				if (!players.getPlayer().hasCards()) {
					winCondition = true;
					break;
				}
				
				// check if player has uno
				if (players.getPlayer().hasUno()) {
					System.out.println("[ " + players.getPlayer().getName() + " ] has UNO!");
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				players = players.next();
			}
			
			// check if "winCondition" is true, then break to reveal winner
			if (winCondition) {
				break;
			}
			
			players.getPlayer().play();
			
			director();
			
			if (direction) {
				players = players.next();
			} else {
				players = players.last();
			}
		}
		System.out.println("[ " + players.getPlayer().getName() + " ] won the game!");
		player_Input.close();
	}
}

package sMeginness.HW03;

import java.util.Scanner;

public class StonesGame {
	static void player1(Scanner read) {
		int stones = 13;							//Setting up initial conditions
		System.out.println("Welcome to 13 stones!");
		int roundCounter = 0;						//Keeps track of whose turn it is
		
		while (stones > 0) {						//Game loop. Ends when there are no more stones.
			System.out.println("There are " + stones + " stones left.");
			System.out.println("Player" + ((roundCounter%2)+ 1) + " choose to take 1, 2, or 3 stones.");
			int playerInput = 0;
			
			playerInput = read.nextInt();
			while (playerInput < 1 || playerInput > 3) {	//input validation loop
				System.out.println("Invalid input, please enter 1, 2, or 3.");
				playerInput = read.nextInt();
			}
			stones -= playerInput;
			++roundCounter;
		}
		System.out.println("Congrats! Player" + (((roundCounter-1)%2)+ 1) + " wins!!");		//math stuff used to 
		read.close();															 			//correctly display 
	}																						//winner, also used in
}																							//earlier println()
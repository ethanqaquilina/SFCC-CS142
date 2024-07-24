package nim;

import java.util.Scanner;

import utilities.Utilities;

public class Game {
	private Player player1, player2;
	private Scanner scan;
	private int pile, min = 10, max = 100, marbles_left = 0;
	private int turn = 0;
	private int coinOutcome = 0;
	private String[] smart_names = { "Eniac", "Brainiac", "HAL", "Deep Thought", "Sky Net" };
	private boolean game_over = false;

	public Game(Scanner scan) {
		// Constructor
		this.scan = scan;
	}

	private void initialize_game() {
		// run the other methods to start the game.
		rules();
		this.pile = Utilities.calc_random(min, max); // Generates pile
		System.out.println("The pile size is " + this.pile); // Displays pile size
		initialize_human();
		initialize_computer();
		coinOutcome = flip_coin();

	}

	// Also, chose how many marbles are in the pile.
	private void initialize_human() {
		System.out.println("Hello human what is your name?");
		String name = scan.nextLine();
		this.player1 = new Human(name, scan);
		System.out.printf("Welcome %s\n", name);

	}

	private void initialize_computer() {
		String name = smart_names[Utilities.calc_random(0, 4)]; // Randomly chooses smart name
		this.player2 = new Computer(name);
		System.out.printf("Welcome %s\n", name);
	}

	private void rules() {
		// Display the rules of the game
		System.out.println("We will have two players.\n" + "     One human\n" + "     One Computer\n"
				+ "Each will take turns removing marbles from a heap.  The size of the heap is randomly selected.  You must \n"
				+ "take at least one but no more than half the remaining marbles.\n"
				+ "The player to remove the last marble is the winner.");
	}

	private int flip_coin() {
		// randomly chose who goes first
		int coinOutcome = Utilities.calc_random(1, 2);
		if (coinOutcome == 1) {
			System.out.println("Human will go first.");
		} else {
			System.out.println("Computer will go first.");
		}

		return coinOutcome;

	}

	public void play_game() {

		initialize_game();
		while (!game_over) {
			take_turn();
		}

	}

	private void take_turn() {
		// alternate based on the turn counter
		if (this.coinOutcome == 1) {
			if (turn % 2 == 0) {
				take_human_turn();
			} else {
				take_computer_turn();
			}
		} else {
			if (turn % 2 == 0) {
				take_computer_turn();
			} else {
				take_human_turn();
			}
			
		}
		this.turn++;
	}

	private void take_human_turn() {
		int marblesTaken = player1.take_marbles(pile);
		pile -= marblesTaken;
		marbles_left = pile;
		if (marbles_left == 0) {
			game_over = true;

			System.out.println("There are zero marbles left. " + player1.get_name() + " won!");
		} else {
			System.out.println("There are " + marbles_left + " marbles left");
		}
	}

	private void take_computer_turn() {

		int marblesTaken = player2.take_marbles(pile);
		System.out.println(player2.get_name() + " took " + marblesTaken + " marbles.");
		pile -= marblesTaken;
		marbles_left = pile;
		if (marbles_left == 0) {
			game_over = true;

			System.out.println("There are zero marbles left. " + player2.get_name() + " won!");
		} else {
			System.out.println("There are " + marbles_left + " marbles left");
		}

	}
}

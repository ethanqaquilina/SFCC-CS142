package ChickGame;

import java.util.Scanner;

import utilities.Utilities;

public class Game {

	private Scanner scan;
	private boolean keep_playing = true, game_over = false;
	int turn;
	Chicken C1, C2;
	private board board;
	private int rows, columns;

	public Game(Scanner scan, int rows, int columns) {
		this.scan = scan;
		this.rows = rows;
		this.columns = columns;

	}

	private void check_game_over() {
		if (!C1.is_alive() || !C2.is_alive()) {
			this.game_over = true;
			if (C1.is_alive()) {
				System.out.println(this.C1.get_name() + " wins!");
			} else if (C2.is_alive()) {
				System.out.println(this.C2.get_name() + " wins!");
			}
		}

	}

	private void move(Chicken C) {
		int[] old_location = C.get_location();
		board.mark_board(old_location[0], old_location[1], '-');
		C.move();
		int[] new_location = C.get_location();
		board.mark_board(new_location[0], new_location[1], C.get_symbol());
	}

	private void combat(Chicken attacker, Chicken defender) {
		int[] solution = attacker.attack();
		int locations = solution.length / 2; // calculate number of y, x pairs.
		int[] def_loc = defender.get_location();
		int size = solution.length;
		int damage = solution[size - 1];
		for (int i = 0; i < locations; i = i + 2) {
			if (def_loc[0] == solution[i] && def_loc[1] == solution[i + 1]) {
				defender.hit(damage);
				System.out.println(attacker.get_name() + " hit " + defender.get_name());
				System.out.println("and did " + damage);
			}
		}

	}

	private void human_change_direction() {
		System.out.println("What direction do you want to change to?" + "\n1 = North\n2 = East\n3 = South\n4 = West");
		int choice = Utilities.get_user_int(scan, 1, 4);
		C1.change_direction(choice);
		System.out.println(C1.get_name() + " changes direction and is facing " + C1.facing());
	}

	private void computer_change_direction() {
		int choice = Utilities.calc_random(1, 4);
		C2.change_direction(choice);
		System.out.println(C2.get_name() + " changes direction and is facing " + C2.facing());
	}

	private void display_info() {
		System.out.printf("Name:%-10s\nFacing:%-6s\nHP:%4d\n", C1.get_name(), C1.facing(), C1.get_hp());
		System.out.println();
		System.out.printf("Name:%-10s\nFacing:%-6s\nHP:%4d\n", C2.get_name(), C2.facing(), C2.get_hp());
	}

	public void play_game() {
		initialize_game();
		while (!game_over) {
			take_turns();
		}

	}

	private void initialize_game() {
		this.turn = 0;
		this.keep_playing = true;
		this.game_over = false;
		this.board = new board();
		this.initialize_characters();

	}

	private void take_turns() {
		display_info();
		this.board.display_board();
		if (this.turn % 2 == 0) {
			take_human_turn();
		} else {
			take_computer_turn();
		}
		this.turn++;
		check_game_over();

	}

	private void take_human_turn() {
		System.out.println(C1.get_name() + " it is your turn.");
		System.out.printf("Do you want to change direction(1)\nmove(2)\ncombate(3)");
		int choice = Utilities.get_user_int(scan, 1, 3);
		switch (choice) {
		case 1:
			human_change_direction();
			break;
		case 2:
			move(C1);
			break;
		case 3:
			combat(C1, C2);
			break;
		}

	}

	private void take_computer_turn() {
		System.out.println(C2.get_name() + " it is your turn.");
		int choice = Utilities.calc_random(1, 3);
		switch (choice) {
		case 1:
			System.out.println(C2.get_name() + " changes direction");
			computer_change_direction();
			break;
		case 2:
			System.out.println(C2.get_name() + " moves");
			move(C2);
			break;
		case 3:
			System.out.println(C2.get_name() + " attacks");
			combat(C2, C1);
			break;
		}

	}

	private void initialize_characters() {
		initialize_human_character();
		initialize_computer_character();

	}

	private void initialize_human_character() {
		System.out.println("What is your name?");
		String name = scan.nextLine();

		int hp = 20;
		int x_coord = 0;
		int y_coord = 0;
		double base_chance = .60;
		int damage_min = 3;
		int damage_max = 20;
		int direction = 3;
		this.C1 = new Chicken(name, hp, x_coord, y_coord, base_chance, damage_min, damage_max, direction);
		this.board.mark_board(y_coord, x_coord, C1.get_symbol());
	}

	private void initialize_computer_character() {

		String name = "Bob";
		int hp = 20;
		int x_coord = this.columns - 1;
		int y_coord = this.rows - 1;
		double base_chance = .60;
		int damage_min = 3;
		int damage_max = 20;
		int direction = 1;
		this.C2 = new Chicken(name, hp, x_coord, y_coord, base_chance, damage_min, damage_max, direction);
		this.board.mark_board(y_coord, x_coord, C2.get_symbol());

	}

}

package ChickGame;

import java.util.Random;

import utilities.Utilities;

public class Chicken implements iDC {

	private String name;
	private char symbol;
	private int max_hp;
	private int current_hp;
	private int x_coord;
	private int y_coord;
	private double base_chance;
	private int damage_max, damage_min;
	private int direction;
	private int max_rows = 10;
	private int max_columns = 10;

	public Chicken(String name, int hp, int x_coord, int y_coord, double base_chance, int damage_min,
			int damage_max, int direction) {

		this.name = name;
		this.symbol = this.name.charAt(0);
		this.max_hp = hp;
		this.current_hp = hp;
		this.x_coord = x_coord;
		this.y_coord = y_coord;
		this.base_chance = base_chance;
		this.damage_max = damage_max;
		this.damage_min = damage_min;
		this.direction = direction;

	}

	public Chicken() {
		this.name = "Harold";
		this.symbol = this.name.charAt(0);
		this.max_hp = 20;
		this.current_hp = 20;
		this.x_coord = 0;
		this.y_coord = 0;
		this.base_chance = .6;
		this.damage_max = 20;
		this.damage_min = 2;
		this.direction = 2;
		this.max_columns = 9;
		this.max_rows = 9;
	}

	public String facing() {
		switch (this.direction) {
		case 1:
			return "North";
		case 2:
			return "East";
		case 3:
			return "South";
		case 4:
			return "West";
		default:
			return "Error";

		}
	}

	@Override
	public int set_vars(int min, int max, int var) {
		if (var < min) {
			var = min;
		} else if (var > max) {
			var = max;
		}
		return var;
	}

	@Override
	public boolean is_alive() {
		return this.current_hp > 0;
	}

	@Override
	public boolean successful_action(double chance) {
		Random rand = new Random();
		if (rand.nextDouble() <= chance) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String get_name() {
		return this.name;
	}

	@Override
	public char get_symbol() {
		return this.symbol;
	}

	@Override
	public void change_direction(int direction) {
		this.direction = this.set_vars(1, 4, direction);
	}

	@Override
	public void move() {
		int[] new_position = new_increment();
		this.y_coord = new_position[0];
		this.x_coord = new_position[1];

	}

	@Override
	public int[] attack() {
		int[] fire_solution = new int[3];
		int[] new_position = new_increment();
		fire_solution[0] = new_position[0];
		fire_solution[1] = new_position[1];
		fire_solution[2] = 0;
		if (this.successful_action(base_chance)) {
			fire_solution[2] = Utilities.calc_random(damage_min, damage_max);
			System.out.println(this.get_name() + " hits and does " + fire_solution[2] + " damage!");
		} else {
			System.out.println("You missed ");
		}

		return fire_solution;
	}
	
	public int get_hp() {
		return this.current_hp;
	}

	@Override
	public int[] get_location() {
		int[] location = { this.y_coord, this.x_coord };
		return location;
	}

	@Override
	public void hit(int damage) {
		this.current_hp = set_vars(0, this.max_hp, this.current_hp - damage);
	}

	private int[] new_increment() {
		int[] increment = new int[2];
		switch (this.direction) {
		case 1:
			increment = increment_north();
			break;
		case 2:
			increment = increment_east();
			break;
		case 3:
			increment = increment_south();
			break;
		case 4:
			increment = increment_west();
			break;
		}
		return increment;
	}

	private int[] increment_north() {
		int y = set_vars(0, this.max_rows, this.y_coord - 2);
		int[] new_increment = { y, this.x_coord };
		return new_increment;
	}

	private int[] increment_south() {
		int y = set_vars(0, this.max_rows, this.y_coord + 2);
		int[] new_increment = { y, this.x_coord };
		return new_increment;
	}

	private int[] increment_east() {
		int x = set_vars(0, this.max_columns, this.y_coord + 2);
		int[] new_increment = { this.y_coord, x };
		return new_increment;
	}

	private int[] increment_west() {
		int x = set_vars(0, this.max_columns, this.y_coord - 2);
		int[] new_increment = { this.y_coord, x };
		return new_increment;
	}

}

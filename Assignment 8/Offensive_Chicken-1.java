package ChickGame;

import java.util.Random;

import utilities.Utilities;

public class Offensive_Chicken extends Chicken {
	 private double increase_damage;
	 private double base_chance;
	 private int damage_min;
	 private int damage_max;
	 

	public Offensive_Chicken(String name, int hp, int x_coord, int y_coord, double base_chance, int damage_min,
			int damage_max, int direction) {
		super(name, hp, x_coord, y_coord, base_chance, damage_min, damage_max, direction);
		this.base_chance = base_chance;
		this.damage_max = damage_max;
		this.damage_min = damage_min;

		}
		
		public Offensive_Chicken() {
			super();
			this.increase_damage = .10;
		}
		
		@Override
		public int[] attack() {
			int damage = 0;
			int[] fire_solution = new int[3];
			int[] new_position = new_increment();
			fire_solution[0] = new_position[0];
			fire_solution[1] = new_position[1];
			fire_solution[2] = 0;
			if (this.successful_action(base_chance)) {
				fire_solution[2] = Utilities.calc_random(damage_min, damage_max);
				fire_solution[2] = damage;
				fire_solution[2] = (int) (damage*increase_damage) + damage;
				System.out.println(this.get_name() + " hits and does " + fire_solution[2] + " damage!");
			} else {
				System.out.println("You missed ");
			}

			return fire_solution;
		}

	}
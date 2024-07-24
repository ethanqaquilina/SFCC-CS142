package ChickGame;

import java.util.Random;

public class Defensive_Chicken extends Chicken {
	
	private double chance_to_block;

	public Defensive_Chicken(String name, int hp, int x_coord, int y_coord, double base_chance, int damage_min,
			int damage_max, int direction) {
		super(name, hp, x_coord, y_coord, base_chance, damage_min, damage_max, direction);
	}
	
	public Defensive_Chicken() {
		super();
		this.chance_to_block = .25;
	}
	
	@Override
	public void hit(int damage) {
		if (super.successful_action(chance_to_block)) {
			System.out.println(super.get_name() + " has blocked some damage");
			Random rand = new Random();
			damage = (int) (damage * rand.nextDouble());
		}
		super.hit(damage);
	}

}

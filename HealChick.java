package ChickGame;

public class HealChick extends Defensive_Chicken {
	
	private double chance_to_heal;
	private int heal_points;
	private int hp;

	public HealChick(String name, int hp, int x_coord, int y_coord, double base_chance, int damage_min, int damage_max,
			int direction) {
		super(name, hp, x_coord, y_coord, base_chance, damage_min, damage_max, direction);
		hp = this.hp;
	}

	public HealChick() {
		super();
		this.chance_to_heal = .40;
		this.heal_points = 5;
	}

	@Override
	public void hit(int damage) {
		super.hit(damage);
		if (super.successful_action(chance_to_heal)) {
			System.out.println(super.get_name() + " healed for 5 HP");
			hp += heal_points;
			System.out.println(super.get_name() + " has " + super.get_hp());
		}
	}
	
}

package ChickGame;

public class DoubleHitChick extends Offensive_Chicken {

	private double chance_to_double_hit;

	public DoubleHitChick(String name, int hp, int x_coord, int y_coord, double base_chance, int damage_min,
			int damage_max, int direction) {
		super(name, hp, x_coord, y_coord, base_chance, damage_min, damage_max, direction);

	}

	public DoubleHitChick() {
		super();
		this.chance_to_double_hit = .50;
	}

	@Override
	public void hit(int damage) {
		if (super.successful_action(chance_to_double_hit)) {
			System.out.println(super.get_name() + " doublehit!");
			for (int i = 0; i < 2; i++) {
				super.hit(damage);
			}
		}
		super.hit(damage);
	}

}

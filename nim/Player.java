package nim;

public abstract class Player implements IPlayer{
	
	private String name;
	public Player(String name) {
		//Constructor
		this.name = name;
	}
	public String get_name() {
		//getter
		return this.name;
	}


	@Override
	public int take_marbles(int available) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}

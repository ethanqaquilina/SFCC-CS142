package nim;

import utilities.Utilities;

public class Computer extends Player {

	public Computer(String name) {
		// Constructor
		super(name);
	}

	@Override
	public int take_marbles(int available) {
		// method from interface
		int marblesTaken = 0;
		if(available > 1) {
		 marblesTaken = Utilities.calc_random(1, available/2);
		}else {
			marblesTaken = 1;
		}
		

		return marblesTaken;

	}
}

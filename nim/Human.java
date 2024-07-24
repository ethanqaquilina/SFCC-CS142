package nim;

import java.util.Scanner;

public class Human extends Player {

	private Scanner scan;

	public Human(String name, Scanner scan) {
		// Constructor
		super(name);
		this.scan = scan;
	}

	@Override
	public int take_marbles(int available) {
		// method from interface
		int marblesTaken = 0 ;
		if(available == 1 ) {
			System.out.println("You take the last marble.");
			return 1;
		}
		while((marblesTaken < 1) || (marblesTaken > available/2)){
		System.out.println("Enter a number to take between one and half of the marbles.");
		marblesTaken = scan.nextInt();
		} 
		
		return marblesTaken;

	}
}

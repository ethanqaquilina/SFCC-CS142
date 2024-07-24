package nim;

import java.util.Scanner;

public class NIM_Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Game theGame = new Game(scan);
		
		theGame.play_game();
		
		scan.close();
	}

}

package sol.missions;

import java.util.Random;

public class Test {
	private static Random random = new Random();
	
	public static void main(String[] args) {
		Board board = new Board();
		
		for (int i = 0; i < 3; i++) {
			board.addOrganisation(new Organisation(random.nextInt(3) + 4));
		}
		
		board.print();
	}
}
 
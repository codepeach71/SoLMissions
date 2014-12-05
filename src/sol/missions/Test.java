package sol.missions;

import java.util.Random;

public class Test {
	private static Random random = new Random();
	
	public static void main(String[] args) {
		testMods();
	}
	
	public static void testRolls(){
		DiceBag myDiceBag = new DiceBag();
		myDiceBag.toggleVerbose(true);
		
		System.out.println("Quick Roll = " + myDiceBag.qRoll(3, 6, 2));
		System.out.println("--------------------------\n");
				
		myDiceBag.addDice(2, 6, 1);
		myDiceBag.addDice(1, 20, -2);
		System.out.println("Custom Roll = " + myDiceBag.rollDice());
		System.out.println("--------------------------\n");

		
		System.out.println("Quick Roll II = " + myDiceBag.qRoll(1, 20, 5, true));
		System.out.println("--------------------------\n");
		
		myDiceBag.emptyDice();
		myDiceBag.addDice(2, 4, -1);
		myDiceBag.addDice(1, 8, 3);
		System.out.println("Custom Roll II = " + myDiceBag.rollDice());
		System.out.println("--------------------------\n");
		
		myDiceBag.emptyDice();
		System.out.println("Empty Roll = " + myDiceBag.rollDice());
		System.out.println("--------------------------\n");
	}
	
	public static void testMods(){
		RollModifier rm = new RollModifier();
	}
	
	public static void testBoard(){
		Board board = new Board();
		for (int i = 0; i < 3; i++) {
			board.addOrganisation(new Organisation(random.nextInt(3) + 4));
		}
		board.print();
	}
}
 
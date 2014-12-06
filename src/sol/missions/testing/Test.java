package sol.missions.testing;

import java.util.Random;

import sol.missions.DiceBag;
import sol.missions.RollModifier;

public class Test {
	private static Random random = new Random();
	
	public static void main(String[] args) {
		testRolls();
	}
	
	public static void testRolls(){
		DiceBag myDiceBag = new DiceBag();
		myDiceBag.toggleVerbose(true);
		
		System.out.println("Quick Roll Silent = " + myDiceBag.qRoll(3, 6, 2));
		System.out.println("--------------------------\n");
				
		myDiceBag.addDice(2, 6, 1);
		myDiceBag.addDice(1, 20, -2);
		System.out.println("Custom Roll = " + myDiceBag.rollDice());
		System.out.println("--------------------------\n");

		
		System.out.println("Quick Roll Verbose = " + myDiceBag.qRoll(1, 20, 5, true));
		System.out.println("--------------------------\n");
		
		myDiceBag.toggleVerbose();
		myDiceBag.emptyDice();
		myDiceBag.addDice(2, 4, -1);
		myDiceBag.addDice(1, 8, 3);
		System.out.println("Custom Roll Silent = " + myDiceBag.rollDice());
		System.out.println("--------------------------\n");
		
		myDiceBag.emptyDice();
		System.out.println("Empty Roll = " + myDiceBag.rollDice());
		System.out.println("--------------------------\n");
	}
	
	public static void testMods(){
		int[] x = {1,2,3};
		RollModifier rm;
		RollModifier rm2;
		rm = new RollModifier(new int[]{1,2,3,4,5,6,7});
		rm2 = new RollModifier(new int[]{-1,-1,-1,-1,-1,-1,-1});
		rm.addModList(rm2);
		System.out.println(rm.getPhysicalSecurity());
		
		
	}
	
	public static void testBoard(){
		TestBoard testBoard = new TestBoard();
		testBoard.go();
	}
}
 
package sol.missions;

import java.util.*;


public class DiceBag {
	private static Random random = new Random();
	private List<Dice> diceList;
	private boolean isVerbose;
	
	public DiceBag() {
		diceList = new ArrayList<Dice>();
		isVerbose = false;
	}
	
	/* Quick Roll */
	public static int qRoll(int numOfDice, int numOfSides, int modifier){ //Quick Roll xDy+z
		return qRoll(numOfDice, numOfSides, modifier, false);
	}
	public static int qRoll(int numOfDice, int numOfSides, int modifier, boolean verbose){ //Quick Roll xDy+z
		int diceTotal = 0;		
		List<Dice> qDiceList = new ArrayList<Dice>();
		DiceBag tempBag = new DiceBag();
		if (verbose) { tempBag.toggleVerbose(true); }
		for(int i = 0; i < numOfDice; i++){
			qDiceList.add(tempBag.new Dice(numOfSides, modifier));
		}
		for (Dice dice : qDiceList){
			diceTotal += dice.roll();
		}
		return diceTotal;
	}
	
	/* Add Dice */
	public void addDice(int numOfDice, int numOfSides, int modifier){
		for(int i = 0; i < numOfDice; i++){
			diceList.add(new Dice (numOfSides, modifier));
		}
	}
	
	/* Empty Bag */
	public void emptyDice(){
		diceList.clear();
	}
	
	/* Roll Bag */
	public int rollDice(){
		int diceTotal = 0;
		for (Dice dice : diceList){
			diceTotal += dice.roll();
		}
		return diceTotal;
	}
	
	/* Toggle Verbose */
	public void toggleVerbose(){
		isVerbose = !isVerbose;
	}
	public void toggleVerbose(boolean switchVerbose){
		isVerbose = switchVerbose;
	}
	
	/* Dice Class */
	private class Dice {
		private int sides, mod;
		
		public Dice(int numOfSides, int modifier){
			sides = numOfSides;
			mod = modifier;
		}
		
		public int roll(){
			int rollResult =  random.nextInt(sides) + 1;
			if (DiceBag.this.isVerbose) { System.out.println("Rolled: " + rollResult + "\nMod: " + mod + "\nTotal: " + (rollResult + mod) + "\n");}
			return rollResult + mod;
		}
	}
}

	
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
	public int qRoll(int numOfDice, int numOfSides, int modifier){ //Quick Roll xDy+z
		List<Dice> qDiceList = new ArrayList<Dice>();
		int diceTotal = 0;
		qDiceList.clear();
		for(int i = 1; i <= numOfDice; i++){
			qDiceList.add(new Dice(numOfSides, modifier));
		}
		for (Dice dice : qDiceList){
			diceTotal += dice.roll();
		}
		return diceTotal;
	}
	
	/*Add Dice*/
	public void addDice(int numOfDice, int numOfSides, int modifier){
		for(int i = 1; i<= numOfDice; i++){
			diceList.add(new Dice (numOfSides, modifier));
		}
	}
	
	/*Empty Bag*/
	public void emptyDice(){
		diceList.clear();
	}
	
	/*Roll Bag*/
	public int rollDice(){
		int diceTotal = 0;
		for (Dice dice : diceList){
			diceTotal += dice.roll();
		}
		return diceTotal;
	}
	
	/*Toggle Verbose*/
	public void toggleVerbose(){
		isVerbose = !isVerbose;
	}
	public void toggleVerbose(boolean switchVerbose){
		if (switchVerbose == false) { isVerbose = false; }
		else { isVerbose = true; }
	}
	
	/*Dice Class*/
	private class Dice {
		private int sides, mod;
		
		public Dice(int numOfSides, int modifier){
			sides = numOfSides;
			mod = modifier;
		}
		
		public int roll(){
			int rollResult =  random.nextInt(sides) + 1;
			if (DiceBag.this.isVerbose==true) { System.out.println("Rolled: " + rollResult + "\nMod: " + mod + "\nTotal: " + (rollResult + mod) + "\n");}
			return rollResult + mod;
		}
	}
}

	
package sol.missions;

import java.util.*;

public class Person implements Compromisable {
	public enum PersonType {BASE, SECURITY, COMMUNICATIONS, HR, PR};
	private Action actions;
	
	private Map<String, String> miscInfo;	

	private PersonType type;
	private RollModifier modifiers;
	private boolean isCompromised, isDead, isCooperative;
	private static Random random = new Random();
	
	public Action getActions() { return actions; }
	public RollModifier getModifiers() { return modifiers; }
	
	private int statFortitude, statInstability, statMorality;	
	public int getStatFortitude() {	return statFortitude; }
	public int getStatInstability() { return statInstability; }
	public int getStatMorality() { return statMorality; }
	public void updateStatFortitude(int change) { statFortitude += change; }
	public void updateStatInstability(int change) {	statInstability += change;	}
	public void updateStatMorality(int change) { statMorality += change; }

	private int statusParanoia, statusCompromised, statusInfluence;
	public int getStatusParanoia() { return statusParanoia; }
	public int getStatusCompromised() { return statusCompromised; }
	public int getStatusInfluence() { return statusInfluence; }
	
	public void updateStatusParanoia(int change) {
		statusParanoia = minMax(statusParanoia + change);
	}
	
	public void updateStatusCompromised(int change) {		
		statusCompromised = minMax(statusCompromised + change);
		isCooperative = statusCompromised >= 50 ? true : false;
		isCompromised = statusCompromised >= 80 ? true : false;		
	}
	
	public void updatestatusInfluence(int change) {
		statusInfluence = minMax(statusInfluence + change);
	}
	
	public void setStatusParanoia(int statusParanoia) {
		this.statusParanoia = minMax(statusParanoia);
	}
	public void setStatusCompromised(int statusCompromised) {
		this.statusCompromised = minMax(statusCompromised);
		isCooperative = statusCompromised >= 50 ? true : false;
		isCompromised = statusCompromised >= 80 ? true : false;
	}
	public void setStatusInfluence(int statusInfluence) {
		this.statusInfluence = minMax(statusInfluence);
	}

	/*Link to database here*/ 
	private final String[] firstNamesMale = { "Brian", "Les", "Bindu", "Jeff", "Scott" };
	private final String[] firstNamesFemale = { "Jana", "Muddy Mae", "Ozma" };	
	private final String[] lastNames = { "Johnson", "Dobson", "Suggins", "Bean" };
	/*---------------------*/
	
	public boolean isCompromised() { return isCompromised; }
	public void setCompromised(boolean compromised) { isCompromised = compromised; }
	public boolean isDead() { return isDead; }
	public void kill() { isDead = true; }
	
	public Person() {
		generateNew(PersonType.BASE);
	}
	
	public Person(PersonType type) {
		generateNew(type);
	}
		
	private void generateNew(PersonType type){
		this.type = type;
		
		actions = new Action(this);
		
		statFortitude = random.nextInt(20) + 1;
		statInstability = random.nextInt(20) + 1;
		statMorality = random.nextInt(20) + 1;
		
		statusParanoia = statInstability * 2;
		statusCompromised = 0;
		statusInfluence= random.nextInt(10) + 20;
		
		modifiers = new RollModifier();
		
		isCompromised = false;
		isDead = false;
		isCooperative = false;
		
		miscInfo = new HashMap<String, String>();
		String sex = random.nextBoolean() ? "Male" : "Female";
		miscInfo.put("sex", sex);		
		if (sex.equals("Male")) {
			miscInfo.put("firstName", firstNamesMale[random.nextInt(firstNamesMale.length)]);
		}
		else {
			miscInfo.put("firstName", firstNamesFemale[random.nextInt(firstNamesFemale.length)]);				
		}
		miscInfo.put("lastName", lastNames[random.nextInt(lastNames.length)]);
	}
	
	public void update() {
		
	}
	
	public String getName() {
		return String.format("%s %s", miscInfo.get("firstName"), miscInfo.get("lastName"));
	}
	
	public int getBribeCost() {
		return statusInfluence * 1000;
	}
	
	public void print() {
		System.out.println(String.format("%s (Fortitude: %s, Instability: %s, Morality: %s)", getName(), statFortitude, statInstability, statMorality));
	}
	
    private int minMax(int value, int min, int max)
    {
        return (value < min) ? min : (value > max) ? max : value;
    }
    
    private int minMax(int value)
    {
        return minMax(value, 0, 100);
    }
	
	
}

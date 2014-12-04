package sol.missions;

import java.util.*;

public class Person {
	public enum PersonType {BASE, SECURITY, COMMUNICATIONS, HR, PR};
	
	private Map<String, String> miscInfo;	
	private int statFortitude, statInstability, statMorality, statusParanoia, statusCompromised, statusInfluence;
	private PersonType type;
	private RollModifier modifiers;
	private boolean isCompromised, isDead, isCooperative;
	private static Random random = new Random();
	
	private final String[] firstNamesMale = { "Brian", "Les", "Bindu", "Jeff", "Scott" };
	private final String[] firstNamesFemale = { "Jana", "Muddy Mae", "Ozma" };	
	private final String[] lastNames = { "Johnson", "Dobson", "Suggins", "Bean" };
	
	public Person(PersonType type) {
		generateNew(type);
	}
		
	private void generateNew(PersonType type){
		this.type = type;
		
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
	
	public void print() {
		System.out.println(String.format("%s (Fortitude: %s, Instability: %s, Morality: %s)", getName(), statFortitude, statInstability, statMorality));
	}
}

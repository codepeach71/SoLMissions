package sol.missions;

import java.util.*;

public class Person {
	private Map<String, String> miscInfo;
	private int statFortitude, statInstability, statMorality, statusParanoia, statusCompromised, statusInfluence;
	private enum TypeEnum {BASE, SECURITY, COMMUNICATIONS, HR, PR};
	private TypeEnum type;
	private RollModifier modifiers;
	private boolean isCompromised, isDead, isCooperative;
	private static Random random = new Random();
	
	
	public Person(TypeEnum type) {
		generateNew(type);
	}
	
	private void generateNew(TypeEnum type){
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
		
		miscInfo = new HashMap<String, String>();ff 
	}
	
	
}

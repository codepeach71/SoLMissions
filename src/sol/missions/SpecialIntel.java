package sol.missions;

public class SpecialIntel extends Intel {
	Person targetPerson;
	
	public SpecialIntel(String name, String contents, Person targetPerson, String actionType, int cost, String outcome, RollModifier modifiers) {
		super(name, contents);
		this.targetPerson = targetPerson;
	}		
}

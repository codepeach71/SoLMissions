package sol.missions;

public class SpecialIntel extends Intel {
	Person targetPerson;
	
	public SpecialIntel(String name, String contents, Person targetPerson) {
		super(name, contents);
		this.targetPerson = targetPerson;
	}		
}

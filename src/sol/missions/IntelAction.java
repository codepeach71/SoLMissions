package sol.missions;

public class IntelAction extends Intel {
	private Person targetPerson;

	public IntelAction(String name, String shortDescription, String contents, String action, String whereFound, Person targetPerson) {
		super(name, shortDescription, contents, action, whereFound, name, 0);
		this.targetPerson = targetPerson;
	}
}

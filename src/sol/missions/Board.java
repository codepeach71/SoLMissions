package sol.missions;


public class Board {
	private Graph<Organisation> organisations;
	private RollModifier modifiers;
	private Person currentPerson;
	private Organisation currentOrganisation;

	public Person getCurrentPerson() { return currentPerson; }
	public void setCurrentPerson(Person person) { currentPerson = person; }
	public Organisation getCurrentOrganisation() { return currentOrganisation; }
	public void setCurrentOrganisation(Organisation org) { currentOrganisation = org; }		

	public Board() {
		organisations = new Graph<Organisation>();
		modifiers = new RollModifier();
	}	

	public void addOrganisation(Organisation org) {
		organisations.add(org);

	}

	public void update() {
		modifiers.applyChanges();		  

		for (Organisation org : organisations.getAll()) {
			org.update(modifiers.getChanges());
		}
		modifiers.clearChanges();
	}

	public void print() {
		for (Organisation o : organisations.getAll()) {
			o.print();
			System.out.println("\n");
		}
	}

	public void printCurrent() {
		for (Organisation o : organisations.getAll()) {
			o.print(currentPerson);
			System.out.println("\n");
		}
	}
	
	// TEMP
	public void printMods() {
		System.out.println("Board");
		modifiers.printMods();
	}
	
	// TEMP
	public void printModsWithChildren() {
		System.out.println("Board");
		modifiers.printMods();
		for (Organisation org : organisations.getAll()) {
			org.printModsWithChildren();
			System.out.println();
		}
	}
	
	// TEMP
	public void addModifier(int x) {
		modifiers.updateGeneric(x);
	}
}

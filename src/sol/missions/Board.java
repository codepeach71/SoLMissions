package sol.missions;

public class Board {
	private Graph<Organisation> organisations;
	private RollModifier modifiers;
	
	public Board() {
		organisations = new Graph<Organisation>();
		modifiers = new RollModifier();
	}	
	
	public void addOrganisation(Organisation org) {
		organisations.add(org);
		
	}
	
	public void update() {
		for (Organisation o : organisations.getAll()) {
			o.update();
		}
	}
	
	public void print() {
		for (Organisation o : organisations.getAll()) {
			o.print();
			System.out.println();
		}
	}
}

package sol.missions;

import java.util.*;


public class Board {
	private Graph<Organisation> organisations;
	private RollModifier modifiers;
	private Person currentPerson;
	private Organisation currentOrganisation;
	private List<GraphTemplate> templates;
	
	private static final Random random = new Random();

	public Person getCurrentPerson() { return currentPerson; }
	public void setCurrentPerson(Person person) { currentPerson = person; }
	public Organisation getCurrentOrganisation() { return currentOrganisation; }
	public void setCurrentOrganisation(Organisation org) { currentOrganisation = org; }		

	public Board() {
		organisations = new Graph<Organisation>();
		modifiers = new RollModifier();
		templates = loadTemplates();
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

	/*
	 * Generate an organisation based on a template 
	 */
	public Organisation generateOrganisation(int templateNumber) {
		return new Organisation(generatePersonGraph(templateNumber));
	}
	
	/*
	 * Creates and populates a graph of people based on the specified template
	 */
	private Graph<Person> generatePersonGraph(int templateNumber) {
		Graph<Person> peopleGraph = new Graph<Person>();
		GraphTemplate template = templates.get(templateNumber);

		// populate graph with random people
		for (int i = 0; i < template.size; i++) {
			peopleGraph.add(new Person());
		}		
		List<Person> peopleList = peopleGraph.getAll();

		// set entry point and connections
		peopleGraph.setRootData(peopleList.get(0));
		for (int i = 0; i < template.size; i++) {
			Person personA = peopleList.get(template.connections[i].left);
			Person personB = peopleList.get(template.connections[i].right);			
			peopleGraph.addConnection(personA, personB);
		}

		// set influences, plus/minus 2
		for (int i = 0; i < template.size; i++) {
			peopleList.get(i).setStatusInfluence(template.influences[i] + (random.nextInt(5) - 2));
		}

		return peopleGraph;
	}
	
	/*
	 * This will eventually load the list of templates from the DB 
	 */
	private List<GraphTemplate> loadTemplates() {
		List<GraphTemplate> templates = new ArrayList<GraphTemplate>();
		
		Pair[] connections = new Pair[] { new Pair(0,1), new Pair(0,2), new Pair(1,3), new Pair(2,4), new Pair(3,5), new Pair(4,5) };
		int[] influences = new int[] { 3, 5, 7, 12, 14, 20 };
		templates.add(new GraphTemplate(connections, influences));
		
		return templates;
	}

	private class GraphTemplate {
		public final int size;				// number of people in graph
		public final Pair[] connections;	// connections between people
		public final int[] influences;		// influence statuses of people

		public GraphTemplate(Pair[] connections, int[] influences) {
			this.size = influences.length;
			this.connections = connections;
			this.influences = influences;			
		}		
	}

	private class Pair {
		public final int left;
		public final int right;

		public Pair(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}
}
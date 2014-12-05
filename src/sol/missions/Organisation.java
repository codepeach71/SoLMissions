package sol.missions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import sol.missions.Person.PersonType;

public class Organisation {
	public enum organisationType { NORMAL, TElECOM, SECURITY }

	private String name;
	private Graph<Person> people;
	private organisationType type;
	private RollModifier modifiers;
	private int strength;
	private static Random random = new Random();

	private final String[] firstNames = { "Ram", "Gruzzle", "Basement", "Dumptrucks", "T.R." };
	private final String[] lastNames = { "Corporation", "Corp.", "Incorporated", "Inc.", "R US", "of India" };

	public Organisation(int size) {
		name = firstNames[random.nextInt(firstNames.length)] + " " + lastNames[random.nextInt(lastNames.length)];
		type = organisationType.NORMAL;
		modifiers = new RollModifier();
		strength = random.nextInt(5) + 1;

		people = new Graph<Person>();
		List<String> takenNames = new ArrayList<String>();
		for (int i = 0; i < size; i++) {
			Person newPerson = new Person(PersonType.BASE);			
			while (takenNames.contains(newPerson.getName())) {
				newPerson = new Person(PersonType.BASE);
			}

			takenNames.add(newPerson.getName());
			people.add(newPerson);
		}

		List<Person> personList = people.getAll();
		for (Person p : personList) {
			int connections = random.nextInt(2) + 1;

			for (int i = 0; i < connections; i++) {			
				int neighbourIndex = personList.indexOf(p);
				while (neighbourIndex == personList.indexOf(p)) {
					neighbourIndex = random.nextInt(personList.size());					
				}

				people.addConnection(people.getNode(p), people.getNode(personList.get(neighbourIndex)));
			}
		}
	}

	public void update() {
		for (Person p : people.getAll()) {
			p.update();
		}
	}

	public void print() {
		System.out.println(String.format("* %s *", name));
		printPeople();
	}

	private void printPeople() {
		for (Person p : people.getAll()) {
			p.print();
		}

		System.out.println();		

		for (Person p : people.getAll()) {
			String outputString = p.getName() + " has ";

			List<Person> neighbours = people.getNeighbours(p);

			outputString += "connections to: ";
			for (Person neighbour : neighbours) {
				outputString += neighbour.getName() + ", ";
			}

			System.out.println(outputString.substring(0, outputString.length() -2));
		}
	}

}

package sol.missions.testing;

import java.util.List;
import java.util.Scanner;

import sol.missions.Board;
import sol.missions.Organisation;
import sol.missions.Person;

public class TestBoard {
	private Board board;
	
	public void go() {
		board = new Board();

		Organisation org = new Organisation();
		board.addOrganisation(org);

		Person personA = new Person();
		Person personB = new Person();
		Person personC = new Person();
		//Person personD = new Person();
		//Person personE = new Person();
		//Person personF = new Person();

		org.addPerson(personA);
		org.addPerson(personB);
		org.addPerson(personC);
		//org.addPerson(personD);
		//org.addPerson(personE);
		//org.addPerson(personF);

		org.setEntryPoint(personA);		
		org.addConnection(personA, personB);
		org.addConnection(personA, personC);
		//org.addConnection(personB, personD);
		//org.addConnection(personC, personE);
		//org.addConnection(personD, personF);
		//org.addConnection(personE, personF);

		board.setCurrentPerson(personA);
		board.setCurrentOrganisation(org);

		/*
		while (true) {
			board.printCurrent();
			selectCurrentPerson();
			board.update();
		}
		*/
	
		System.out.println("* set board mod to 5 *");
		board.addModifier(5);
		board.printModsWithChildren();
		System.out.println("* call board.update() *");		
		board.update();
		board.printModsWithChildren();
		System.out.println("* set organisation mod to 3 *");		
		org.addModifier(3);
		board.printModsWithChildren();
		System.out.println("* call board.update() *");
		board.update();
		board.printModsWithChildren();
		System.out.println(" * set person mod to 2 *");
		personA.addModifier(2);
		board.update();
		board.printModsWithChildren();
	}
	
	public void selectCurrentPerson() {
		Scanner reader = new Scanner(System.in);
		System.out.println("Select person: ");

		int selection = reader.nextInt();

		if (selection == 0) {
			board.getCurrentPerson().setCompromised(true);
			board.update();
		}
		else {		
			Person selectedPerson = board.getCurrentOrganisation().getPersonGraph().getIndex(selection - 1);
			List<Person> reachablePeople = board.getCurrentOrganisation().getPersonGraph().getReachable();
			if (reachablePeople.contains(selectedPerson)) {
				board.setCurrentPerson(selectedPerson);
			}
			else {
				System.out.println(String.format("Sorry, %s is not reachable\n", selectedPerson.getName()));
			}		
		}
		reader.close();
	}
}

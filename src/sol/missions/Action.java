package sol.missions;

public class Action {

	private static Player player;
	private Person thisPerson;	
	
	public Action(Person inPerson) {
		thisPerson = inPerson;		
	}
	
	public void setPlayer(Player p) {
		player = p;
	}
	
	public boolean canBribe() {
		return player.getAvailableMoney() >= thisPerson.getBribeCost();		
	}

	public void bribe() {
		if (canBribe()) {
			if (DCheck.beatDC(DiceBag.qRoll(1, 20, thisPerson.getModifiers().getManipulation()), thisPerson.getStatMorality())) {
				thisPerson.updateStatusCompromised(20);
			}
			else {
				thisPerson.updateStatusParanoia(10);
			}
		}		
	}
	
	public boolean canThreaten() {
		return false;
	}

	public void threaten() {
		if (canThreaten()) {
			if (DCheck.beatDC(DiceBag.qRoll(1, 20, thisPerson.getModifiers().getManipulation()), thisPerson.getStatFortitude())) {
				thisPerson.updateStatusCompromised(20);
			}
			else {
				thisPerson.updateStatusParanoia(30);
			}
		}
	}
	
	public void kidnap() {
		
	}

	public void getOutOfTown() {

	}

	public void secretPrison() {
		
	}

	public void sniper() {
		
	}

	public void poison() {
	
	}

	public void mug() {

	}

	public void fakeSuicide() {
	
	}

	public void smear() {
		
	}

	public void doSurveillance() {

	}

	public void searchHouse() {

	}

	public void searchPC() {

	}

	public void tapCommunications() {

	}

	public void bugHouse() {

	}

	public void tail() {

	}

	public void askOnStreets() {

	}

	public void searchDB() {

	}

	public void searchSocialMedia() {

	}
}

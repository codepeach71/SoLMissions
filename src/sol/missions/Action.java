package sol.missions;

public class Action {

	private Person thisPerson;	
	private DiceBag bag = new DiceBag();

	private boolean canBribe, canThreaten, canKidnap, canGetOut, canSecretPrison, canSniper, canPoison, canMug,
					canFakeSuicide, canSmear, canDoSurveillance, canSearchHouse, canSearchPC, canTap, canBug,
					canTail, canAskOnStreets, canSearchDB, canSearchSocialMedia = true;

	public Action(Person inPerson) {
		thisPerson = inPerson;		
	}

	public void bribe() {
		if (canBribe) {
			d20Bag(thisPerson.getModifiers().getManipulation());
			if (DCheck.beatDC(bag, thisPerson.getStatMorality())) {
				thisPerson.updateStatusCompromised(20);
			}
			else {
				thisPerson.updateStatusParanoia(10);
			}
		}		
	}

	public void threaten() {
		if (canThreaten) {
			d20Bag(thisPerson.getModifiers().getManipulation());
			if (DCheck.beatDC(bag, thisPerson.getStatFortitude())) {
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
		if(canGetOut) {
			
		}
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
	
	private void d20Bag(int modifier) {
		bag.emptyDice();
		bag.addDice(1, 20, modifier);
	}	
}

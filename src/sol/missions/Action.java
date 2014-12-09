package sol.missions;

import java.util.*;

public class Action {

	private static Player player;
	private Person thisPerson;
	
	private enum ActionType { BRIBE, KIDNAP, SNIPER }
	private enum RewardType { COMPROMISE, TREASURE, PARANOIA };	
	
	private Map<ActionType, Integer> costs;
	private Map<ActionType, Integer> mods;
	private Map<ActionType, ActionResult> rewards;
	private Map<ActionType, ActionResult> failurePenalties;
	private Map<ActionType, Boolean> disabled;
	
	public Action(Person inPerson) {
		thisPerson = inPerson;
		
		// initialise maps
		costs = new HashMap<ActionType, Integer>();
		mods = new HashMap<ActionType, Integer>();
		rewards = new HashMap<ActionType, ActionResult>();
		failurePenalties = new HashMap<ActionType, ActionResult>();
		disabled = new HashMap<ActionType, Boolean>();
		
		// set default values for actions
		// bribe
		costs.put(ActionType.BRIBE, 1000);
		mods.put(ActionType.BRIBE, 0);
		rewards.put(ActionType.BRIBE, new ActionResult(RewardType.COMPROMISE, 20));
		failurePenalties.put(ActionType.BRIBE, new ActionResult(RewardType.PARANOIA, -10));
		disabled.put(ActionType.BRIBE, false);
		
		// kidnap
		costs.put(ActionType.KIDNAP, 1000);
		mods.put(ActionType.KIDNAP, 0);
		rewards.put(ActionType.KIDNAP, null);
		failurePenalties.put(ActionType.KIDNAP, null);
		disabled.put(ActionType.KIDNAP, false);
		
	}
	
	public void setPlayer(Player p) {
		player = p;
	}
	
	/*
	 * Rewards/Penalties
	 */
	
	private class ActionResult {
		private RewardType type;
		private int value;
		
		private ActionResult(RewardType type, int value) {
			this.type = type;
			this.value = value;
		}
	}
	
	private void processResult(ActionResult reward) {
		switch(reward.type) {
		case COMPROMISE:
			thisPerson.updateStatusCompromised(reward.value);
			break;
		case TREASURE:
			// add to player treasure
			break;
		case PARANOIA:
			thisPerson.updateStatusParanoia(reward.value);
			break;
		}
	}
	
	/*
	 * Actions
	 */
	
	public boolean canBribe() {
		return !disabled.get(ActionType.BRIBE) && player.getAvailableMoney() >= thisPerson.getBribeCost();		
	}

	public void bribe() {
		if (canBribe()) {
			if (DCheck.beatDC(DiceBag.qRoll(1, 20, thisPerson.getModifiers().getManipulation()), thisPerson.getStatMorality())) {
				processResult(rewards.get(ActionType.BRIBE));
			}
			else {
				processResult(failurePenalties.get(ActionType.BRIBE));
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

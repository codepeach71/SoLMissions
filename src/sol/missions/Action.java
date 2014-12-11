package sol.missions;

import java.util.*;

public class Action {

	private static Player player;
	private Person thisPerson;
	
	private enum ActionType { BRIBE, BRIBE_SPECIAL, THREATEN, BLACKMAIL_SPECIAL, GET_OUT, IMPRISON, SNIPER, 
								POISON, MUG, SUICIDE, DISCREDIT, DISCREDIT_SPECIAL  }
	private enum RewardType { COMPROMISE, TREASURE, PARANOIA, DISAPPEAR, MEDIA_LEGITIMACY, OPSEC, INFLUENCE };	
	
	private Map<ActionType, Integer> moneyCosts;
	private Map<ActionType, Integer> mediaCosts;	
	private Map<ActionType, Integer> mods;
	private Map<ActionType, List<ActionResult>> rewards;
	private Map<ActionType, List<ActionResult>> failurePenalties;
	private Map<ActionType, Boolean> disabled;
	private Map<ActionType, Integer> dangerRating;
	
	@SuppressWarnings("serial")
	public Action(Person inPerson) {
		thisPerson = inPerson;
		
		// initialise maps
		moneyCosts = new DefaultHashMap<ActionType, Integer>(0);
		mediaCosts = new DefaultHashMap<ActionType, Integer>(0);
		mods = new DefaultHashMap<ActionType, Integer>(0);
		rewards = new HashMap<ActionType, List<ActionResult>>();
		failurePenalties = new HashMap<ActionType, List<ActionResult>>();
		dangerRating = new HashMap<ActionType, Integer>(0);
		disabled = new DefaultHashMap<ActionType, Boolean>(false);
		
		// set default values for actions
		// bribe
		moneyCosts.put(ActionType.BRIBE, 1000);
		rewards.put(ActionType.BRIBE, Arrays.asList(new ActionResult(RewardType.COMPROMISE, 20)));
		failurePenalties.put(ActionType.BRIBE, Arrays.asList(new ActionResult(RewardType.PARANOIA, 10)));
		
		// bribe special
		moneyCosts.put(ActionType.BRIBE_SPECIAL, 1000);
		rewards.put(ActionType.BRIBE_SPECIAL, Arrays.asList(new ActionResult(RewardType.COMPROMISE, 20)));
		failurePenalties.put(ActionType.BRIBE_SPECIAL, Arrays.asList(new ActionResult(RewardType.PARANOIA, 10)));
		
		// threaten
		rewards.put(ActionType.THREATEN, Arrays.asList(new ActionResult(RewardType.COMPROMISE, 20)));
		failurePenalties.put(ActionType.THREATEN, Arrays.asList(new ActionResult(RewardType.PARANOIA, 10)));
		dangerRating.put(ActionType.THREATEN, 10);
		
		// blackmail special
		rewards.put(ActionType.BLACKMAIL_SPECIAL, Arrays.asList(new ActionResult(RewardType.COMPROMISE, 20)));
		failurePenalties.put(ActionType.BLACKMAIL_SPECIAL, Arrays.asList(new ActionResult(RewardType.PARANOIA, 10)));
		dangerRating.put(ActionType.BLACKMAIL_SPECIAL, 10);
		
		// get out of town
		rewards.put(ActionType.GET_OUT, Arrays.asList(new ActionResult(RewardType.DISAPPEAR, 0)));
		dangerRating.put(ActionType.GET_OUT, 10);
		
		// imprison
		rewards.put(ActionType.IMPRISON, Arrays.asList(new ActionResult(RewardType.DISAPPEAR, 0)));
		dangerRating.put(ActionType.IMPRISON, 10);
		
		// sniper
		rewards.put(ActionType.SNIPER, Arrays.asList(new ActionResult(RewardType.DISAPPEAR, 0)));
		failurePenalties.put(ActionType.SNIPER, Arrays.asList(	new ActionResult(RewardType.MEDIA_LEGITIMACY, -10),
																new ActionResult(RewardType.OPSEC, 1)));
		dangerRating.put(ActionType.SNIPER, 10);
		
		// poison
		rewards.put(ActionType.POISON, Arrays.asList(new ActionResult(RewardType.DISAPPEAR, 0)));
		failurePenalties.put(ActionType.POISON, Arrays.asList(	new ActionResult(RewardType.MEDIA_LEGITIMACY, -10),
																new ActionResult(RewardType.OPSEC, 1)));
		dangerRating.put(ActionType.POISON, 10);
		
		// mugging
		rewards.put(ActionType.MUG, Arrays.asList(new ActionResult(RewardType.DISAPPEAR, 0)));
		failurePenalties.put(ActionType.MUG, Arrays.asList(	new ActionResult(RewardType.MEDIA_LEGITIMACY, -10),
																new ActionResult(RewardType.OPSEC, 1)));
		dangerRating.put(ActionType.MUG, 10);
		
		// suicide
		rewards.put(ActionType.SUICIDE, Arrays.asList(new ActionResult(RewardType.DISAPPEAR, 0)));
		failurePenalties.put(ActionType.SUICIDE, Arrays.asList(	new ActionResult(RewardType.MEDIA_LEGITIMACY, -10),
																new ActionResult(RewardType.OPSEC, 1)));
		dangerRating.put(ActionType.SUICIDE, 10);
		
		// discredit
		mediaCosts.put(ActionType.DISCREDIT, 50);
		rewards.put(ActionType.DISCREDIT, Arrays.asList(new ActionResult(RewardType.INFLUENCE, -10)));
		failurePenalties.put(ActionType.DISCREDIT, Arrays.asList(new ActionResult(RewardType.MEDIA_LEGITIMACY, -10)));
	}
	
	public void setPlayer(Player p) {
		player = p; // plz
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
	
	private void processResults(List<ActionResult> rewards) {
		for (ActionResult reward : rewards) {
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
			case DISAPPEAR:
				// disappear person
				break;
			case MEDIA_LEGITIMACY:
				player.changeMediaLegitimacy(reward.value);
				break;
			case OPSEC:
				player.changeOPSEC(reward.value);
				break;
			}
		}
	}
	
	/*
	 * Actions
	 */
	
	public boolean canBribe() {  
		return !disabled.get(ActionType.BRIBE) && player.getAvailableMoney() >= moneyCosts.get(ActionType.BRIBE);		
	}

	public void bribe() {
		if (canBribe()) {
			if (DCheck.beatDC(DiceBag.qRoll(1, 20, thisPerson.getModifiers().getManipulation()), thisPerson.getStatMorality())) {
				processResults(rewards.get(ActionType.BRIBE));
			}
			else {
				processResults(failurePenalties.get(ActionType.BRIBE));				
			}
		}		
	}
	
	public boolean canThreaten() {
		return !disabled.get(ActionType.THREATEN);
	}

	public void threaten() {
		if (canThreaten()) {
			if (DCheck.beatDC(DiceBag.qRoll(1, 20, thisPerson.getModifiers().getManipulation()), thisPerson.getStatFortitude())) {
				processResults(rewards.get(ActionType.THREATEN));
			}
			else {
				processResults(failurePenalties.get(ActionType.THREATEN));
			}
		}
	}
	
	public void bribe_() {
		doSimpleAction(canBribe(), thisPerson.getModifiers().getManipulation() + mods.get(ActionType.BRIBE), 
				thisPerson.getStatMorality(), rewards.get(ActionType.THREATEN), failurePenalties.get(ActionType.THREATEN));
	}

	public void threaten_() {
		doSimpleAction(canThreaten(), thisPerson.getModifiers().getManipulation() + mods.get(ActionType.THREATEN), 
				thisPerson.getStatFortitude(), rewards.get(ActionType.THREATEN), failurePenalties.get(ActionType.THREATEN));
	}
	
	private void doSimpleAction(boolean available, int mod, int targetRoll, List<ActionResult> rewards, List<ActionResult> penalties) {
		if (available) {
			if (DCheck.beatDC(DiceBag.qRoll(1, 20, mod), targetRoll)) {
				processResults(rewards);
			}
			else {
				processResults(penalties);
			}
		}
	}
	
	@SuppressWarnings("serial")
	private class DefaultHashMap<K,V> extends HashMap<K,V> {
		  protected V defaultValue;
		  public DefaultHashMap(V defaultValue) {
		    this.defaultValue = defaultValue;
		  }
		  @Override
		  public V get(Object k) {
		    return containsKey(k) ? super.get(k) : defaultValue;
		  }
	}
}

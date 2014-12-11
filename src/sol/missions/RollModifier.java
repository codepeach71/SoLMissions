package sol.missions;

import java.util.*;

public class RollModifier {
	private Map<String, Integer> modMap = new HashMap<String, Integer>();
	
	private static enum modType { GENERIC, MANIPULATION, PHYSICAL_SECURITY, INTEL_ELECTRONIC, INTEL_OTHER, MORALITY, PUBLIC_PERCEPTION }
	private int[] mods = new int[modType.values().length];
	private int[] changes = new int[mods.length];
	
	
	public RollModifier(){
		for (int i = 0; i < mods.length; i++) {
			mods[i] = 0;
		}
	}
	
	public RollModifier(int[] rollArray){
		if (rollArray.length == mods.length) {
			mods = rollArray;
		}
		else {
			for (int i = 0; i < mods.length; i++) {
				mods[i] = 0;
			}
		}	
	}
	
/* Accessors */	
	public int getGeneric(){ return mods[modType.GENERIC.ordinal()]; }	
	public int getManipulation(){ return mods[modType.MANIPULATION.ordinal()]; }	
	public int getPhysicalSecurity(){ return mods[modType.PHYSICAL_SECURITY.ordinal()]; }	
	public int getElectronicIntel(){ return mods[modType.INTEL_ELECTRONIC.ordinal()]; }
	public int getIntelOther(){ return mods[modType.INTEL_OTHER.ordinal()];	}
	public int getMorality(){ return mods[modType.MORALITY.ordinal()]; }
	public int getPublicPerception(){ return mods[modType.PUBLIC_PERCEPTION.ordinal()]; }

/* Mutators - Set */
	public void setGeneric(int x) { mods[modType.GENERIC.ordinal()] = x; } 
	public void setManipulation(int x) { mods[modType.MANIPULATION.ordinal()] = x; }
	public void setPhysicalSecurity(int x) { mods[modType.PHYSICAL_SECURITY.ordinal()] = x; }
	public void setElectronicIntel(int x) { mods[modType.INTEL_ELECTRONIC.ordinal()] = x; }			
	public void setIntelOther(int x) { mods[modType.INTEL_OTHER.ordinal()] = x; }
	public void setMorality(int x) { mods[modType.MORALITY.ordinal()] = x; }
	public void setPublicPerception(int x) { mods[modType.PUBLIC_PERCEPTION.ordinal()] = x; }
	
	/* Mutators - Update */
	public void updateGeneric(int change) { 
		mods[modType.GENERIC.ordinal()] += change;
		changes[modType.GENERIC.ordinal()] += change;
	} 
	public void updateManipulation(int change) { mods[modType.MANIPULATION.ordinal()] += change; }
	public void updatePhysicalSecurity(int change) { mods[modType.PHYSICAL_SECURITY.ordinal()] += change; }
	public void updateElectronicIntel(int change) { mods[modType.INTEL_ELECTRONIC.ordinal()] += change; }			
	public void updateIntelOther(int change) { mods[modType.INTEL_OTHER.ordinal()] += change; }
	public void updateMorality(int change) { mods[modType.MORALITY.ordinal()] += change; }
	public void updatePublicPerception(int change) { mods[modType.PUBLIC_PERCEPTION.ordinal()] += change; }
	
	public void applyChanges() {
		for (int i = 0; i < changes.length; i++) {
			mods[i] += changes[i];
		}
	}
	
	public RollModifier getChanges() {
		return new RollModifier(changes);
	}
	
	public void addChanges(RollModifier newChanges) {
		changes[modType.GENERIC.ordinal()] += newChanges.getGeneric();
		changes[modType.MANIPULATION.ordinal()] += newChanges.getManipulation();
		changes[modType.PHYSICAL_SECURITY.ordinal()] += newChanges.getPhysicalSecurity();
		changes[modType.INTEL_ELECTRONIC.ordinal()] += newChanges.getElectronicIntel();
		changes[modType.INTEL_OTHER.ordinal()] += newChanges.getIntelOther();
		changes[modType.MORALITY.ordinal()] += newChanges.getMorality();
		changes[modType.PUBLIC_PERCEPTION.ordinal()] += newChanges.getPublicPerception();
	}
	
	public void clearChanges() {
		changes = new int[changes.length];		
	}
	
	/* Add 2 RollModifiers */
	public void addModList(RollModifier newMods){
		mods[modType.GENERIC.ordinal()] += newMods.getGeneric();
		mods[modType.MANIPULATION.ordinal()] += newMods.getManipulation();
		mods[modType.PHYSICAL_SECURITY.ordinal()] += newMods.getPhysicalSecurity();
		mods[modType.INTEL_ELECTRONIC.ordinal()] += newMods.getElectronicIntel();
		mods[modType.INTEL_OTHER.ordinal()] += newMods.getIntelOther();
		mods[modType.MORALITY.ordinal()] += newMods.getMorality();
		mods[modType.PUBLIC_PERCEPTION.ordinal()] += newMods.getPublicPerception();		
	}
	
}


package sol.missions;

import java.util.*;

public class RollModifier {
	private Map<String, Integer> modMap = new HashMap<String, Integer>();
	
	public RollModifier(){
		modMap.put("generic", 0);
		modMap.put("manipulation", 0);
		modMap.put("physSec", 0);
		modMap.put("elecIntel", 0);
		modMap.put("intelOther", 0);
		modMap.put("morality", 0);
		modMap.put("publicPerception", 0);
	}
	
	public RollModifier(int[] rollArray){
		if (rollArray.length==7){
			modMap.put("generic", rollArray[0]);
			modMap.put("manipulation", rollArray[1]);
			modMap.put("physSec", rollArray[2]);
			modMap.put("elecIntel", rollArray[3]);
			modMap.put("intelOther", rollArray[4]);
			modMap.put("morality", rollArray[5]);
			modMap.put("publicPerception", rollArray[6]);
		} else {
			modMap.put("generic", 0);
			modMap.put("manipulation", 0);
			modMap.put("physSec", 0);
			modMap.put("elecIntel", 0);
			modMap.put("intelOther", 0);
			modMap.put("morality", 0);
			modMap.put("publicPerception", 0);
		}
	}
/* Accessors */	
	public int getGeneric(){
		return modMap.get("generic");
	}	
	public int getManipulation(){
		return modMap.get("manipulation");
	}	
	public int getPhysicalSecurity(){
		return modMap.get("physSec");
	}	
	public int getElectronicIntel(){
		return modMap.get("elecIntel");
	}	
	public int getIntelOther(){
		return modMap.get("intelOther");
	}
	public int getMorality(){
		return modMap.get("morality");
	}
	public int getPublicPerception(){
		return modMap.get("publicPerception");
	}
	
/* Mutators - Set */
	public void setGeneric(int x){
		modMap.put("generic", x);
	}
	public void setManipulation(int x){
		modMap.put("manipulation", x);
	}	
	public void setPhysicalSecurity(int x){
		modMap.put("physSec", x);
	}	
	public void setElectronicIntel(int x){
		modMap.put("elecIntel", x);
	}	
	public void setIntelOther(int x){
		modMap.put("intelOther", x);
	}
	public void setMorality(int x){
		modMap.put("morality", x);
	}
	public void setPublicPerception(int x){
		modMap.put("publicPerception", x);
	}
	
	/* Mutators - Update */
	public void updateGenericMod(int x){
		modMap.put("generic", modMap.get("generic") + x);
	}
	public void updateManipulationMod(int x){
		modMap.put("manipulation",  modMap.get("manipulation") + x);
	}	
	public void updatePhysicalSecurityMod(int x){
		modMap.put("physSec",  modMap.get("physSec") + x);
	}	
	public void updateElectronicIntelMod(int x){
		modMap.put("elecIntel", modMap.get("elecIntel") + x);
	}	
	public void updateIntelOtherMod(int x){
		modMap.put("intelOther",  modMap.get("intelOther") + x);
	}
	public void updateMoralityMod(int x){
		modMap.put("morality", modMap.get("morality") + x);
	}
	public void updatePublicPerceptionMod(int x){
		modMap.put("publicPerception", modMap.get("publicPerception") + x);
	}
	
	/* Add 2 RollModifiers */
	public void addModList(RollModifier newMods){
		modMap.put("generic", modMap.get("generic") + newMods.getGeneric());
		modMap.put("manipulation", modMap.get("manipulation") + newMods.getManipulation());
		modMap.put("physSec", modMap.get("physSec") + newMods.getPhysicalSecurity());
		modMap.put("elecIntel", modMap.get("elecIntel") + newMods.getElectronicIntel());
		modMap.put("intelOther", modMap.get("intelOther") + newMods.getIntelOther());
		modMap.put("morality", modMap.get("morality") + newMods.getMorality());
		modMap.put("publicPerception", modMap.get("publicPerception") + newMods.getMorality());
	}
	
}


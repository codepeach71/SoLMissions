package sol.missions;

public final class DCheck {
	private DCheck(){
		
	}
	
	public static boolean isSuccessful(DiceBag d1, int DC){
		if(d1.rollDice() >= DC){ 
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isSuccessful(DiceBag d1, DiceBag d2){
		if(d1.rollDice() > d2.rollDice()){
			return true;
		} else {
			return false;
		}
	}
}

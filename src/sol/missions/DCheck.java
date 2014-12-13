package sol.missions;



public final class DCheck {
	private DCheck(){
		
	}
	
	public static boolean beatDC(int roll, int DC) {
		return roll > DC;
	}
	
	public static boolean isSuccessful(DiceBag d1, int DC){
		return d1.rollDice() >= DC; 
	}
	
	public static boolean beatDC(DiceBag d1, int DC){
		return d1.rollDice() > DC; 
	}
		
	public static boolean isSuccessful(DiceBag d1, DiceBag d2){
		return d1.rollDice() > d2.rollDice();

	}
}

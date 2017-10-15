package utilities;

public class convertCoordinates {
	
	public static int[] convertPosition(String pos){
		pos = pos.toLowerCase();
		char coordinate1=pos.charAt(0);
		char coordinate2=pos.charAt(1);
		int result[]=new int[2];
		result[0]=8-(Character.getNumericValue(coordinate2));
		result[1]=coordinate1-97;
		
		return result;
		
	}
	
}

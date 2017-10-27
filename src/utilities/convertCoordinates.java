package utilities;

public class convertCoordinates {
	
	/**
	 * convertPosition converts a string into an int array of two elements corresponding to the row and column of the input.
	 * 
	 * @param pos This is a string of the position input by the user
	 * @return an int array of two elements where the first element is the row and the second element is the column.
	 */
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

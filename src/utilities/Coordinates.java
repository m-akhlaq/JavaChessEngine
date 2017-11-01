package utilities;
/**
 * @author Muhammad S. Akhlaq
 * @author John Brauner
 *
 */
public class Coordinates {
	int r,c;
	public Coordinates(int r,int c){
		this.r=r;
		this.c=c;
	}
	
	/**
	 * getRow returns the current row of the coordinate.
	 * 
	 * @return the current row of the coordinate as an int.
	 */
	public int getRow(){
		return r;
	}
	
	/**
	 * getColumn returns the current column of the coordinate.
	 * 
	 * @return the current column of the coordinate as an int.
	 */
	public int getColumn(){
		return c;
	}

	
	/**
	 *equals checks to see if the given objects is of type Coordinates. 
	 * 
	 * @param o This is an object that is checked whether or not it is a Coordinates object.
	 * @return a boolean of whether o is a Coordinates object.
	 */
	 public boolean equals(Object o) {
		 if (o == null ||
		 (!(o instanceof Coordinates))){
		 return false;
		 }
		 Coordinates other = (Coordinates)o;
		 return r == other.r &&
		 c == other.c;
		 }
	 
	 /**
		 * toString returns the row and column of this coordinate as a string.
		 * 
		 * @return a String version of the row and column of the coordinate.
		 */
	 public String toString(){
		 return r+" "+c;
	 }
}

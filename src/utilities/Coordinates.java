package utilities;

public class Coordinates {
	int r,c;
	public Coordinates(int r,int c){
		this.r=r;
		this.c=c;
	}
	public int getRow(){
		return r;
	}
	public int getColumn(){
		return c;
	}

	 public boolean equals(Object o) {
		 if (o == null ||
		 (!(o instanceof Coordinates))){
		 return false;
		 }
		 Coordinates other = (Coordinates)o;
		 return r == other.r &&
		 c == other.c;
		 }
	 
	 public String toString(){
		 return r+" "+c;
	 }
}

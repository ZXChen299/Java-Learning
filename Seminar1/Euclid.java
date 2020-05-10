package Seminar1;

// Usage: java Euclid a b
// Calculates and prints out the greatest common divisor of a and b
class Euclid {

	public static void main(String[] args) {
		// start with the pair (x,y) of numbers
		int x = Integer.parseInt(args[0]); 
		int y = Integer.parseInt(args[1]); 
		while ( y!=0 ) {
			// calculate a new pair (y,x%y) of numbers
			int previousX = x;
			x = y;
			y = previousX % y;
		}
		System.out.println( "The greatest common divisor is " + x);
	}

}

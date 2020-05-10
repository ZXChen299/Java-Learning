package Ex6;
/*
Zixuan Chen
201917656
 */

public class Interval {

    private final double left, right;

    public double left()  { return left;  }
    public double right() { return right; }

    public boolean contains( double x ){ return x >= left && x <= right; }

    public boolean intersects( Interval b ){
        return contains(b.left) || contains(b.right) || ( b.contains(left) && b.contains(right) );
    }

    public double length() { return right - left; }

    public String toString() { return "[ " + left + " , " + right + " ]"; }

    public Interval( double left, double right ) { this.left = left; this.right = right; }

    public static void main( String[] args ){

        double[] endpoints = new double[8];
        for( int i = 0; i < 4; i++ ) endpoints[i] = Double.parseDouble(args[i]);
        if( endpoints[0] > endpoints[1] || endpoints[2] > endpoints[3] ) return;
        Interval a = new Interval( endpoints[0], endpoints[1] );
        Interval b = new Interval( endpoints[2], endpoints[3] );
        // print the lengths
        System.out.println( "The length of interval A is " + a.length() + "." );
        System.out.println( "The length of interval B is " + b.length() + "." );
        // check whether they intersect or not
        if( a.intersects(b) ) System.out.println( "They intersect." );
        else System.out.println( "They do not intersect." );
        // check whether they contain 0 or not
        if( a.contains(0) ) System.out.println( "A contains 0." );
        else System.out.println( "A does not contain 0." );
        if( b.contains(0) ) System.out.println( "B contains 0." );
        else System.out.println( "B does not contain 0." );

    }


}

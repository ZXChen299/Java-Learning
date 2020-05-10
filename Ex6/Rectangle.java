package Ex6;
/*
Zixuan Chen
201917656
 */

public class Rectangle {
    private final Interval x, y;

    public double area() { return x.length()* y.length(); }

    public double perimeter() { return ( ( x.length() + y.length() ) * 2 ); }

    public boolean intersects( Rectangle b ){ return x.intersects(b.x) && y.intersects(b.y); }

    public boolean contains ( Rectangle b ){
        boolean inX = x.contains(b.x.left()) && x.contains(b.x.right());
        boolean inY = y.contains(b.y.left()) && y.contains(b.y.right());
        return inX && inY;
    }

    public Rectangle( Interval x, Interval y ){ this.x = x; this.y = y; }

    public static void main( String[] args ){

        Interval aX = new Interval(3.0, 7.0);
        Interval aY = new Interval(4.0, 8.0);
        Interval bX = new Interval(5.0, 6.5);
        Interval bY = new Interval(6.0, 7.0);
        Rectangle A = new Rectangle( aX, aY );
        Rectangle B = new Rectangle( bX, bY );
        // print the areas and perimeters of each rectangles
        System.out.println( "Area of A is " + A.area() + "." );
        System.out.println( "Area of B is " + B.area() + "." );
        System.out.println( "Perimeter of A is " + A.perimeter() + "." );
        System.out.println( "Perimeter of B is " + B.perimeter() + "." );
        // check whether they intersect or not
        if( A.intersects(B) ) System.out.println( "They intersect. " );
        else System.out.println( "They does not intersect." );
        // check whether one contains the other or not
        if( A.contains(B) ) System.out.println( "A contains B." );
        else if( B.contains(A) ) System.out.println( "B contains A." );
        else System.out.println( "They do not contain each other." );
    }

}

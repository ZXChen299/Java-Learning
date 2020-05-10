package Seminar10;// implements an axis-parallel rectangle
// Usage:
// java Rectangle
// prints out some tests

public class Rectangle {

    private Interval x;
    private Interval y;

    public Rectangle(Interval x, Interval y) {
        this.x = x;
        this.y = y;
    }

    public double area() {
        return (x.length() * y.length());
    }

    public double perimeter() {
        return 2*(x.length() + y.length());
    }

    public boolean intersects(Rectangle b) {
        return (x.intersects(b.x) && y.intersects(b.y));
    }

    public boolean contains(Rectangle b) {
        return (x.contains(b.x) && y.contains(b.y));
    }

    public String toString() {
        return x.toString() + " x " + y.toString();
    }

    public static void main(String[] args) {
        Rectangle rectangleA = new Rectangle(new Interval(0,3), new Interval(1,4));
        Rectangle rectangleB = new Rectangle(new Interval(1,2), new Interval(2,5));
        Rectangle rectangleC = new Rectangle(new Interval(1,2), new Interval(2,3));
        
        System.out.println("Rectangle A = " + rectangleA + " has area " 
             + rectangleA.area() + " and perimeter " + rectangleA.perimeter());
        System.out.println("Rectangle B = " + rectangleB + " has area "
             + rectangleB.area() + " and perimeter " + rectangleB.perimeter());
        System.out.println("Rectangle C = " + rectangleC + " has area "
             + rectangleC.area() + " and perimeter " + rectangleC.perimeter());

        if (rectangleA.intersects(rectangleB)) {
            System.out.println("Rectangle A intersects Rectangle B");
        } else {
            System.out.println("Rectangle A does not intersect Rectangle B");
        }

        if (rectangleA.intersects(rectangleC)) {
            System.out.println("Rectangle A intersects Rectangle C");
        } else {
            System.out.println("Rectangle A does not intersect Rectangle C");
        }

        if (rectangleB.intersects(rectangleC)) {
            System.out.println("Rectangle B intersects Rectangle C");
        } else {
            System.out.println("Rectangle B does not intersect Rectangle C");
        }

        if (rectangleA.contains(rectangleB)) {
            System.out.println("Rectangle A contains Rectangle B");
        } else {
            System.out.println("Rectangle A does not contain Rectangle B");
        }

        if (rectangleA.contains(rectangleC)) {
            System.out.println("Rectangle A contains Rectangle C");
        } else {
            System.out.println("Rectangle A does not contain Rectangle C");
        }

        if (rectangleB.contains(rectangleC)) {
            System.out.println("Rectangle B contains Rectangle C");
        } else {
            System.out.println("Rectangle B does not contain Rectangle C");
        }

    }
}

package Seminar6;

// implements a data type for intervals [left,right]
// Usage:
// java Interval a1 a2 b1 b2
// creates intervals [a1,a1] and [b1,b2], and prints out whether they
// intersect, and whether they contain 0.
public class Interval {
    
    private double left;
    private double right;

    public Interval(double l, double r) {
        if (l <= r) { // endpoints in correct order
            this.left = l;
            this.right = r;
        } else { // endpoints in wrong order, amend
            this.left = r;
            this.right = l;
        }
    }

    public boolean contains(double x) {
        return (left <= x && x <= right);
    }

    // tests whether this contains the interval b.
    // not asked, but needed for the class Rectangle.
    public boolean contains(Interval b) {
        return (this.contains(b.left) && this.contains(b.right));
    }

    public boolean intersects(Interval b) {
        return (this.left <= b.right && b.left <= this.right);
    }

    public double length() {
        return (right - left);
    }

    public String toString() {
        return ("[" + left + ", " + right + "]");
    }

    public static void main(String[] args) {
        if (args.length < 4) {
            return;
        }
        double left_a = Double.parseDouble(args[0]);
        double right_a = Double.parseDouble(args[1]);
        double left_b = Double.parseDouble(args[2]);
        double right_b = Double.parseDouble(args[3]);

        Interval a = new Interval(left_a, right_a);
        Interval b = new Interval(left_b, right_b);

        System.out.print("Interval " + a + " and  Interval " + b);
        if (a.intersects(b)) {
            System.out.println(" intersect.");
        } else {
            System.out.println(" don't intersect.");
        }
        
        System.out.print("Interval " + a);
        if (a.contains(0.0)) {
            System.out.println(" contains 0.");
        } else {
            System.out.println(" does not contain 0.");
        }

        System.out.print("Interval " + b);
        if (b.contains(0.0)) {
            System.out.println(" contains 0.");
        } else {
            System.out.println(" does not contain 0.");
        }
    }
}

package Seminar6;

/*************************************************************************
 *  Compilation:  javac Complex2.java
 *  Execution:    java Complex2
 *
 *  Data type for complex numbers using polar representation.
 *
 *************************************************************************/

public final class Complex2 {
    private double r;        // distance
    private double theta;        // angle

    // constructor that takes in rectangular coordinates
    public Complex2(double re, double im) {
        r     = Math.sqrt(re*re + im*im);
        theta = Math.atan2(im, re);
    }

    // accessor methods
    public double re() { return r * Math.cos(theta); }
    public double im() { return r * Math.sin(theta); }

    // return a string representation of this complex number
    public String toString()  {
        return re() + " + " + im() + "i";
    }

    // return this complex number plus b
    public Complex2 plus(Complex2 b) {
        Complex2 a = this;
        double re = a.r * Math.cos(a.theta) + b.r * Math.cos(b.theta);
        double im = a.r * Math.sin(a.theta) + b.r * Math.sin(b.theta);
        return new Complex2(re, im);
    }

    // return this complex number times b
    public Complex2 times(Complex2 b) {
        Complex2 a = this;
        Complex2 c = new Complex2(0, 0);
        c.r = a.r * b.r;                // can't make r and theta final
        c.theta = a.theta + b.theta;    // because of these two statements
        return c;
    }

    // return this complex number times this complex number
    public Complex2 square() {
        return this.times( this );
    }
    
    // return the magnitude / absolute value of this complex number
    public double abs() { return r; }


    // sample client for testing 
    public static void main(String[] args) {
        Complex2 a = new Complex2(5.0, 6.0);
        System.out.println("a = " + a);

        Complex2 b = new Complex2(-2.0, 3.0);
        System.out.println("b = " + b);

        Complex2 c = b.times(a);
        System.out.println("c = " + c);
    }

}

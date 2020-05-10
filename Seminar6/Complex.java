package Seminar6;

/*************************************************************************
 *  Program 3.2.6 in "Introduction to programming in Java" (Sedgewick, Wayne)
 *
 *  Compilation:  javac Complex.java
 *  Execution:    java Complex
 *
 *  Data type for complex numbers.
 *
 *************************************************************************/

public class Complex {

    private final double re, im;
    
    public Complex(double real, double imag) {
        re = real;
        im = imag;
    }

    public double re() { return re; }
    public double im() { return im; }

    // return a string representation of this complex number
    public String toString()  {
        return re() + " + " + im() + "i";
    }

    // return this complex number plus b
    public Complex plus(Complex b) {
        double real = re + b.re;
        double imag = im + b.im;
        return new Complex(real, imag);
    }

    // return this complex number times b
    public Complex times(Complex b) {
        double real = re * b.re - im * b.im;
        double imag = re * b.im + im * b.re;
        return new Complex(real, imag);        
    }

    // return this complex number times this complex number
    public Complex square() {
        return this.times( this );
    }

    // return the magnitude / absolute value of this complex number
    public double abs() { 
        return Math.sqrt(re*re + im*im); 
    }
    
    // sample client for testing
    public static void main(String[] args) {
        Complex a = new Complex(5.0, 6.0);
        System.out.println("a = " + a);

        Complex b = new Complex(-2.0, 3.0);
        System.out.println("b = " + b);

        Complex c = b.times(a);
        System.out.println("c = " + c);
    }

}

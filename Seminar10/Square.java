package Seminar10;
// Julia Boettcher, 2014, 2015, 2016
// 2018 Updated by Konrad Swanepoel
// A simple example of inheritance 
//
// To compile this class, the classes Rectangle and Interval have to be in the same
// folder.

public class Square extends Rectangle {

    public Square (double x, double y, double length) {
        // we call the constructor of Rectangle
        // this can only be done as first command in constructor
        super( new Interval(x,x+length), new Interval(y,y+length) );
    }

    // a new method, not existing in Rectangle
    public double sideLength() {
        return perimeter() / 4;  // perimeter() is inherited from Rectangle
    }

    // this overrides toString() of Rectangle
    public String toString () {
        // we cannot access xInterval of Rectangle here because it is private
        return "Square: " + super.toString();
    }

    // this class also inherits the main method from Rectangle

}

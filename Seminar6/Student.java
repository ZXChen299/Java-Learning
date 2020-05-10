package Seminar6;// Julia Boettcher, 2012; changed 2013
//
// an example for an own data type, which stores information about a student
//
// this class does not contain a test client (main method), so you cannot
// run it with java Student

public class Student {

    // instance variables:

    private String name;
    private final int number; // student number should not change

    // constructors:
    
    public Student (String na, int nu) {
        name = na;
        number = nu;
    }

    public Student (int nu) {
        number = nu;
    }

    // instance methods:
    
    public void print () {
        System.out.println("student number: "+number+" name: "+name);
    }
    
    public void setName(String na) {
        name = na;
    }
    
    public String getName() {
        return name;
    }
    
    public int getNumber() {
        return number;
    }
    
}

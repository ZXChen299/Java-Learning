package Seminar6;// Julia Boettcher, 2012; changed 2013; updated 2016
//
// an example for an own data type, which stores information about a lecture course
// many other methods should be added to make this a really useful data type
//
// java LectureCourse name1 name2 name3 ...
// tests this data type

public class LectureCourse {
 
    static final int maxSize = 40;  // a class variable (a constant)

    // instance variables:

    private Student[] students; // an array of Student objects
    private int registered = 0; // initialised at object creation
    private final String name;

    // constructor:

    // create a course with the given size and name
    public LectureCourse (int size, String name) {
        // name is the local variable "name" in this method
        // this.name is the instance variable "name" of this object
        this.name=name;
        if (size>maxSize) {
            students = new Student[maxSize];
        } else {
            students = new Student[size];
        }
    }

    // reference methods:

    // register the given student, if course is not full yet
    public void register (Student student) {
        if( registered<students.length) {
            students[registered] = student;
            registered++;
        }
    }

    // print information about this course
    public void print () {
        System.out.println ("Course " + name + ", " + registered + " students registered:"); 
        for (int i=0; i<registered; i++) { // here we use the method
            students[i].print();           // print() of Student
        }
    }

    // class methods:

    // create and return a LectureCourse object with students with the
    // given names, and some arbitrarily assigned student numbers (not a
    // very practical method; this method just illustrates some features of
    // OO-programming)
    public static LectureCourse createCourse (String[] names) {
        LectureCourse c = new LectureCourse(names.length,"MA407");
        for (int i=0; i<names.length; i++) {
            Student student = new Student (names[i], 1000 + i );
            c.register (student);
        }
        return c;
    }

    // for test purposes only
    public static void main( String args[] ) {
        if (args.length > 0) {
            LectureCourse course = createCourse(args);
            course.print();
        }  // we CANNOT write course.createCourse(args);
    }      // we also CANNOT call print(); without object

}
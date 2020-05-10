package Seminar3;
// Julia Boettcher, 2013, based on exercise 2.3.32 of "Introduction to
// programming in Java"; updated 2015, 2016, 2019
//
// run with java FunnyFunction <number>
// prints funnyFunction(i) for i=1..<number>  (see also lecture slides week 3)
//
// What does this function compute?

class FunnyFunction {

    static int funnyFunction (int n) {
        if (n>100) {
            return n-10;
        } else {
            return funnyFunction( funnyFunction( n+11 ) );
        }
    }

    public static void main (String[] args) {
        if (args.length<1) {
            return;
        }
        int n = Integer.parseInt( args[0] );
        for (int i=1; i<=n; i++) {
            System.out.println( "funnyFunction(" + i + ") = " + funnyFunction(i) );
        }
    }

}

package Seminar3;
// Julia Boettcher 2013, copied from ex. 2.3.29 in "Introduction to
// programming in Java"; updated 2015, 2016, 2019
//
// An implementation of the "3n+1-problem", a famous open problem.
// 
// run with:   java IntriguingFunction <number>
// 
// prints a sequence of numbers n_0 n_1 n_2 ...
// where n_0=<number> and
// n_{i+1} = n_i / 2   if n_i is even and
// n_{i+1} = 3*n_i + 1 if n_i is odd (but not 1)
// if n_i is 1 the sequence stops with n_i
//
// try it for example with each of 200,...,220 as input

class IntriguingFunction {

    public static void intriguingFunction (int n) {
        System.out.print( n + " " );
        if (n==1) return;
        if (n % 2 == 0) {
            intriguingFunction(n/2);
        } else {
            intriguingFunction(3*n+1);
        }
    }

    public static void main (String[] args) {
        if (args.length<1) {
            return;
        }
        int n = Integer.parseInt( args[0] );
        intriguingFunction(n);
        System.out.println();
    }

}

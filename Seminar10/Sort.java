package Seminar10;
// Julia Boettcher, 2014, 2015, 2016
// 2018 Updated by Konrad Swanepoel
// A simple example of an abstract class
//
// run with java Sort
// (the NEW VERSIONS of the classes InsertionSort and MergeSort need to be
// in the same directory)

public abstract class Sort {

    // sort the array
    public abstract void sort(int[] x);
    
    // test the sorting algorithm
    public void test (int n) {
        int[] x = new int[n];
        randomFill(x);
        long time = System.currentTimeMillis();
        sort(x);
        time = System.currentTimeMillis() - time;
        System.out.print("Sorting " + n + " numbers with " + this);
        System.out.println(" needed " + time + "ms.");
    }

    // fill the array with random integers
    private static void randomFill (int[] x) { 
        for (int i=0; i < x.length; i++)
            x[i] = (int) ((double) x.length * Math.random());
    }
    
    // test the non-abstract subclasses of this class
    public static void main (String[] args) {
        InsertionSort s0 = new InsertionSort();
        s0.test(10000);
        Sort s = new InsertionSort();
        s.test(10000);
        s = new MergeSort();
        s.test(10000);
    }

}

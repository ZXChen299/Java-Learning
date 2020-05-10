package Seminar4;
// Bernhard von Stengel
// Modified by Julia Boettcher 2012, 2013, 2014, 2016
// and Konrad Swanepoel 2018
// run with:
// java InsertionSort
//
// an implementation of insertion sort

class InsertionSort {

    // sort the array x with insertion sort
    static void insertionSort (int[] x) {
        for (int i=1; i < x.length; i++) {
            // insert x[i] into the already sorted
            // sequence  x[0]...x[i-1]
            int key = x[i];
            int j = i;
            // find j so that key >= x[j-1]
            // and move the elements x[j]...x[i-1] one to the right
            while ( j > 0 && key < x[j-1] ) {
                x[j] = x[j-1];
                j-- ;
            }
            // now either j==0 or key >= x[j-1]
            x[j] = key;
        }
    }

    // for test purposes only
    public static void main (String[] args) {
        int[] x = {33, 5, 2, 1, 20, 15, 3, 9, 11, 7};
        outArray(x, "Before sorting:");
        insertionSort (x) ;
        outArray(x, "After sorting:");
        test(100000);
        test(200000);
        test(400000);
        test(800000);
    } 

    // print first info then the array
    static void outArray (int[] x, String info) {
        System.out.println(info);
        for (int i=0; i < x.length; i++)
            System.out.print(x[i] + "  ");
        System.out.println();
    }

    // test sorting on a random array with n entries
    static void test (int n) {
        int[] x = new int[n];
        randomFill(x);
        long time = System.currentTimeMillis();
        insertionSort(x);
        time = System.currentTimeMillis() - time;
        System.out.println( "Sorting " + n + " numbers needed " + time + " ms.");
    }

    // fill the array x with random numbers
    static void randomFill (int[] x) { 
        for (int i=0; i < x.length; i++) {
            x[i] = (int) ((double) x.length * Math.random());
        }
    }

}

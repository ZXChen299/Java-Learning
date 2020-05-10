package Seminar4;// Bernhard von Stengel
// Modified by Julia Boettcher 2012, 2013, 2014, 2016
// and Konrad Swanepoel 2018
// run with:
// java MergeSort
//
// This is a recursive version of the mergesort algorithm, which
// is a divide and conquer algorithm. It divides the array to be 
// sorted in two sub-arrays and sorts each of them by dividing 
// each into two sub-arrays and so on... . The two sorted 
// subarrays are then merged into one sorted array.

class MergeSort {
   
    // sort the array x
    static void mergeSort (int[] x) {
        mergeSort(x,0,x.length);
    }

    // sort the elements  x[start], x[start+1], ... , x[end-1]
    static void mergeSort (int[] x, int start, int end) {
        if (start < end-1)  { // at least two elements to be sorted
            int middle = (start+end)/2;
            mergeSort (x, start, middle);
            mergeSort (x, middle, end);
            merge (x, start, middle, end);
        }
    }

    // merge the two sorted sequences
    // x[start],... , x[middle-1]   and   x[middle], ... , x[end-1]
    static void merge (int[] x, int start, int middle, int end) {
        int[] left = new int[middle-start];
        int[] right = new int[end-middle];
        // copy the first half to left, the second half to right
        for (int i=start; i < middle; i++) {
            left[i-start] = x[i];
        }
        for (int i=middle; i < end; i++) {
            right[i-middle] = x[i];
        }

        int iLeft = 0;  // index to left
        int iRight = 0; // index to right

        for (int i=start; i<end ; i++) {
            // if done with left part, copy from right part
            if (iLeft >= left.length) {
                x[i] = right[iRight];
                iRight++;
            // if done with right part, copy from left part
            } else if (iRight >= right.length) {
                x[i] = left[iLeft];
                iLeft++;
            // otherwise, if left element smaller than right, copy left
            } else if ( left[iLeft] <= right[iRight] ) {
                x[i] = left[iLeft];
                iLeft++;
             // otherwise, if right smaller than left, copy right
            } else {
                x[i] = right[iRight];
                iRight++;
            }
        }
    }

    // for test purposes only
    public static void main (String[] args) {
        int[] A = {33, 5, 2, 1, 20, 15, 3, 9, 11, 7};
        outArray(A, "before sorting:");
        mergeSort (A) ;
        outArray(A, "after sorting:");
        test(100000);
        test(200000);
        test(400000);
        test(800000); 
    } 

    // print info, and the array
    static void outArray (int[] x, String info) {
        System.out.println(info);
        for (int i=0; i < x.length; i++)
            System.out.print(x[i] + "  ");
        System.out.println();
    }

    // test merge sort on a random array with n entries
    static void test (int n) {
        int[] x = new int[n];
        randomFill(x);
        long time = System.currentTimeMillis();
        mergeSort(x);
        time = System.currentTimeMillis() - time;
        System.out.println( "Sorting " + n + " numbers needed " + time + " ms.");
    }

    // fill the array x with random numbers
    static void randomFill (int[] x) { 
        for (int i=0; i < x.length; i++)
            x[i] = (int) ((double) x.length * Math.random());
    }

}

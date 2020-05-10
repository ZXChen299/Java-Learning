package Seminar4;
// Bernhard von Stengel
// Modified by Julia Boettcher 2012, 2013, 2014, 2016
// and Konrad Swanepoel 2018
// run with:
// java InPlaceMergeSort
//
// This is a version of the mergesort algorithm, 
// that only copies half of of the array to be merged
// into a temporary array, leaving the other half in
// the original array.
// It also avoids unnecessarily many case distinctions
// in the merge method and attempts to stop as early
// as possible.

class InPlaceMergeSort {
   
    // sort the array x
    static void mergeSort (int[] x) {
        mergeSort(x,0,x.length);
    }

    // sort the elements  x[start], x[start+1], ... , x[end-1]
    static void mergeSort (int[] x, int start, int end) {
        if (start < end-1)  { // if at least two elements have to be sorted
            int middle = (start+end)/2;
            mergeSort(x, start, middle);
            mergeSort(x, middle, end);
            merge(x, start, middle, end);
        }
    }

    // merge the two sorted sequences
    // x[start],... , x[middle-1]   and   x[middle], ... , x[end-1]
    static void merge (int[] x, int start, int middle, int end) {
        int[] left = new int[middle-start];
        // copy the first half to left
        for (int i=start; i < middle; i++) {
            left[i-start] = x[i];        
        }

        int iLeft = 0;  // index to left
        int iRight = middle; // index to right part of x

        for (int i=start; i<end ; i++) {
            // if right part empty, or left element smaller than right,
            // use left element next 
            if (iRight >= end || left[iLeft] <= x[iRight]) {
                x[i] = left[iLeft];
                iLeft++;
                if (iLeft >= left.length)
                    break;  // if we arrive at the end of left,
                            // we are done
            }
             // otherwise use right element next
            else {
                x[i] = x[iRight];
                iRight++;
            }
        }
    }

    // for test purposes only
    public static void main (String[] args) {
        int[] x = {33, 5, 2, 1, 20, 15, 3, 9, 11, 7};
        outArray(x, "before sorting:");
        mergeSort (x) ;
        outArray(x, "after sorting:");
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
        System.out.println( "Sorting " + n + " numbers needed " + time + "ms.");
    }

    // fill the array x with random numbers
    static void randomFill (int[] x) { 
        for (int i=0; i < x.length; i++)
            x[i] = (int) ((double) x.length * Math.random());
    }

}

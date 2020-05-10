package Seminar5;
// Julia Boettcher 2013; updated 2016
//
// run with:
// java QuickSort
//
// This is a recursive version of the quicksort algorithm, which chooses
// the left-most element as pivot.

class QuickSort {
   
    // sort the array x
    static void quickSort (int[] x) {
        quickSort(x,0,x.length);
    }

    // sort the elements x[start], x[start+1], ... , x[end-1]
    static void quickSort (int[] x, int start, int end) {
        if (start < end-1)  {    // if only one element, nothing to be done
            int pivotPosition = reorder(x, start, end);
            quickSort(x, start, pivotPosition);
            quickSort(x, pivotPosition+1, end);
        }
    }

    // take the entry at x[start] and call it pivot; then rearrange the (sub)array
    // x[start],..,x[end-1] so that
    //   1. all elements <= pivot come before pivot
    //   2. all elements > pivot come after pivot
    // and return the final position of pivot
    static int reorder (int[] x, int start, int end) {
        int pivot = x[start];
        int pivotPosition = start;
        for (int i=start+1; i<end; i++) {
            // any element which is <= pivot gets moved in front of the pivot
            if ( x[i] <= pivot ) { 
                x[pivotPosition] = x[i];
                x[i] = x[pivotPosition + 1];
                x[pivotPosition + 1] = pivot;
                pivotPosition++;
            }
        }
        return pivotPosition;
    }

    // for test purposes only
    public static void main (String[] args) {
        int[] A = {33, 5, 2, 1, 20, 15, 3, 9, 11, 7};
        outarray(A, "before sorting:");
        quickSort (A) ;
        outarray(A, "after sorting:");
        test(100000);
        test(200000);
        test(400000);
        test(800000); 
        test(1600000); 
        test(3200000); 
        test(6400000); 
    } 

    static void outarray (int[] x, String info) {
        System.out.println(info);
        for (int i=0; i < x.length; i++)
            System.out.print(x[i] + "  ");
        System.out.println();
    }

    static void test (int n) {
        int[] x = new int[n];
        randomfill(x);
        long time = System.currentTimeMillis();
        quickSort(x);
        time = System.currentTimeMillis() - time;
        System.out.println( "Sorting " + n + " numbers needed " + time + "ms.");
    }

    static void randomfill (int[] A) { 
        for (int i=0; i < A.length; i++)
            A[i] = (int) ((double) A.length * Math.random());
    }

}

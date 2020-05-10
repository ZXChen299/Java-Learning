package Seminar10;
// Julia Boettcher, 2014, 2015, 2016
// Updated 2018 Konrad Swanepoel
// A sorting algorithm class for merge sort using inheritance
// (from the abstract class Sort)

public class MergeSort extends Sort {

    // use merge sort for sorting
    public void sort(int[] x) {
        mergeSort(x,0,x.length);
    }

    public String toString() {
        return "Merge Sort";
    }

    // sort the elements  x[start], x[start+1], ... , x[end-1]
    private void mergeSort (int[] x, int start, int end) {
        if (start < end-1)  { // if at least two elements have to be sorted
            int middle = (start+end)/2;
            mergeSort(x, start, middle);
            mergeSort(x, middle, end);
            merge(x, start, middle, end);
        }
    }

    // merge the two sorted sequences
    // x[start],... , x[middle-1]   and   x[middle], ... , x[end-1]
    private void merge (int[] x, int start, int middle, int end) {
        int[] left = new int[middle-start];
        // copy the first half to left
        for (int i=start; i < middle; i++)
            left[i-start] = x[i];        
        int iLeft = 0;  // index to left
        int iRight = middle; // index to right part of x

        for (int i=start; i<end ; i++) {
            // if right part empty, or left element smaller than right,
            // use left element next 
            if (iRight >= end || left[iLeft] <= x[iRight]) {
                x[i] = left[iLeft];
                iLeft++;
                if (iLeft >= left.length)
                    break;  // If we arrive at the end of left,
                            // we are done.
            }
             // otherwise use right element next
            else {
                x[i] = x[iRight];
                iRight++;
            }
        }
    }

}

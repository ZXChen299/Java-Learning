package Seminar10;
// Julia Boettcher, 2014, 2015, 2016
//
// A sorting algorithm class for insertion sort using inheritance
// (from the abstract class Sort)

public class InsertionSort extends Sort {

    // use insertion sort for sorting
    public void sort(int[] x) {
        for (int i=1; i < x.length; i++) {
            int key = x[i];
            int j = i;
            while ( j > 0 && key < x[j-1] ) {
                x[j] = x[j-1];
                j-- ;
            }
            x[j] = key;
        }        
    }

    public String toString() {
        return "Insertion Sort";
    }

}

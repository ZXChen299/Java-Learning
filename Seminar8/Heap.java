package Seminar8;

class Heap // implementing a min-heap
{
    private int size; // number of elements in the heap
    private int[] A;  // array for storing the heap elements
    // in A[1] ... A[size]. Heap property means:
    // the children of a heap element A[i] are A[2*i] and
    // A[2*i+1], and both are >= A[i]. Hence A[1] is smallest. 

    public Heap(String[] args)  // create a heap from an argument list
    {
        size = args.length;
        A = new int[size+1]; // stored in A[1] ... A[size]

        for (int i=0; i<size; i++) // fill in heap, unsorted
            A[i+1] = Integer.parseInt(args[i]);
        // NOW CREATE THE HEAP
        for (int i = size/2; i>=1; i--) 
            heapify (i);
    }

    public boolean notempty() { // the heap is not empty
        return size>0; 
    }

    public int extract_min() { // extract the smallest element from the heap
        int min = A[1];  // this is the smallest element
        A[1] = A[size];
        size-- ;
        heapify(1);
        return min;
    }

    // recursive version of heapify(i):
    // create a heap with root  A[i]  up to element  A[size]
    //
    // ASSUMPTION: children A[2*i] and A[2*i+1] are already
    // roots of heaps, but A[i] may be out of place.
    // RESULT: afterwards A[i] is the root of a heap.

    private void heapify (int i) {
        if (2*i <= size) { // there is a left child, otherwise done
            int c = 2*i; int min = A[c]; // the currently smallest child
            if (2*i+1 <= size)  {        // and there is a right child
                if ( A[2*i+1] < min) {   // the right child is smaller
                    c = 2*i+1 ; min = A[c];
                }
            }
            if (min < A[i]) { // need to modify the heap, o/w done
                A[c] = A[i];  // move A[i] downwards
                A[i] = min;   // and A[c] into A[i]
                heapify(c);
            }
        }
    }

    public static void main (String[] args) {
        Heap h = new Heap(args) ;
        while (h.notempty())
            System.out.print(h.extract_min() + " ");
        System.out.println();
    }
}

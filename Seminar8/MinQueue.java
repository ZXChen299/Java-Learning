package Seminar8;

public class MinQueue
{
    public MinQueue (FreqItem start) { // create the queue
                    // from a list of frequency items
        makeHeap(start);
    }

    public boolean notempty() { // the queue is not empty
        return size>0; 
    }

    public FreqItem extract_min() {
        // extract the smallest element from the queue
        FreqItem min = A[1];  // this is the smallest element
        A[1] = A[size];
        size-- ;
        heapify(1);
        return min;
    }

    private int size; // number of elements in the heap
    private FreqItem[] A;  // array for storing the heap elements
    // the children of a heap element A[i] are A[2*i],
    // A[2*i+1], and both >= A[i] to have the heap property
    // fulfilled. They are stored in A[1] ... A[size]

    private void makeHeap(FreqItem start) {
        size = 0;
        // first, count the items
        for (FreqItem p = start; p!=null; p=p.next)
            size ++;
        A = new FreqItem[size+1]; // stored in A[1] ... A[size]

        int i=1;
        for (FreqItem p = start; p!=null; p=p.next) {
            A[i] = p;
            i++;
        }

        for (i = size/2; i>=1; i--) // create the heap
            heapify (i);
        return;
    }

    // recursive version of heapify, at position i of the heap
    // heap means:  children's values >= node value
    // children of  i  are  2i  and  2i+1  if  <= size
    private void heapify(int i)
    {
        if (2*i <= size) // there is a left child, otherwise done
        {
            int c = 2*i;  // left child
            FreqItem min = A[c]; // min = the currently smallest child
            if (2*i+1 <= size)   // there is a right child
            {
                if ( A[2*i+1].count < min.count) // the right child is smaller
                {
                    c = 2*i+1 ; min = A[c];
                }
            }
            if (min.count < A[i].count) // need to modify the heap, o/w done
            {
                A[c] = A[i]; // move A[i] downwards
                A[i] = min;  // and A[c] into A
                heapify(c);
            }
        }
    }

    public static void main (String[] args)
    {
        FreqCount f = new FreqCount(args) ;
        MinQueue q = new MinQueue(f.start) ;
        while (q.notempty())
            System.out.print(q.extract_min() + " ");
        System.out.println();
    }
}

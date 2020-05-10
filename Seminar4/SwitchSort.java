package Seminar4;
// run with:
// java SwitchSort a1 a2 ... an
//
// Puts a1, a2, ... an into an array, outputs the array,
// sorts the array and outputs the sorted array.
// Then sorts various large test arrays. Sorting is done using
// Switch Sort (aka Bubblesort).
//
// For 3 answers to Exercise 4.3, see the comments at the end.

class SwitchSort {
   
    // sort the array x by switching neighbouring
    // x[i] and x[i+1] with x[i]>x[i+1]
    // until no such switching neighbours exists
    static void switchSort (int[] x) {
        boolean switched = true;
        while (switched) {
            switched = false;
            for (int i = 0; i < x.length - 1; i++) {
                if (x[i] > x[i+1]) {
                    int temp = x[i];
                    x[i] = x[i+1];
                    x[i+1] = temp;
                    switched = true;
                }
            }
        }
    }

    // print the array x
    static void outArray (int[] x, String info) {
        System.out.print(info + " ");
        for (int i=0; i < x.length; i++)
            System.out.print(x[i] + " ");
        System.out.println();
    }

    // sort the numbers given as command line arguments
    // and also run some bigger test cases
    public static void main (String[] args) {
        int[] x = new int[args.length];
        for (int i = 0; i < x.length; i++) {
            x[i] = Integer.parseInt(args[i]);
        }
        outArray(x, "before sorting:");
        switchSort (x) ;
        outArray(x, "after sorting:");

        testSequence(10000);
        testSequence(20000);
        testSequence(40000);
    } 

    // sort x and print how much time this needed
    static void test (int[] x) {
        long time = System.currentTimeMillis();
        switchSort(x);
        time = System.currentTimeMillis() - time;
        System.out.println( "Sorting " + x.length + " numbers needed " + time + "ms.");
    }

    // run sort tests on the following arrays of size n:
    // 1. a random array
    // 2. the arrray 0 2 4 6 ...
    // 3. the array 10 10 10 ...
    // 4. the array ... 3 2 1 0
    static void testSequence(int n) {
        int[] x = new int[n];
        // Test random array of size n
        System.out.println();
        System.out.println("Testing a random array of size " + n + ".");
        randomFill(x);
        test(x);

        // Test array containing 0 2 4 6 ...
        System.out.println();
        System.out.println("Testing a large array with " + n + 
                           " entries: 0 2 4 6 ... " + 2*(n-1));
        x = new int[n];
        for (int i =0; i < x.length; i++) {
            x[i] = 2*i;
        }
        test(x);

        // Test array containing identical entries 10 10 ... 10
        System.out.println();
        System.out.println("Testing a large array with " + n + 
                           " identical entries: 10 10 10 ... 10.");
        for (int i =0; i < x.length; i++) {
            x[i] = 10;
        }
        test(x);

        // Test array containing entries in reverse order
        System.out.println();
        System.out.println("Testing a large array with " + n + 
                           " entries in reverse order: " + (n-1) +
                           " ... 3 2 1 0.");
        for (int i =0; i < x.length; i++) {
            x[i] = x.length-1-i;
        }
        test(x);
    }

    // fill the array x with random numbers
    static void randomFill (int[] A) {
        for (int i=0; i < A.length; i++)
            A[i] = (int) ((double) A.length * Math.random());
    }

}

// 1) One way of seeing that the algorithm terminates after time 
// O(n^2), is to consider all possible pairs i, j with
// 0 <= i < j <= n-1. For each such pair,  the original values of x[i] 
// and x[j] will switch at most once throughout the algorithm. Therefore,
// there are at most n choose 2 = n(n-1)/2 switches, and we conclude that
// the algorithm will terminate. The number of iterations of the while 
// loop cannot be more than the number of switches, otherwise in one of 
// the iterations there will be no switches, and then the boolean variable
// will stay true and the algorithm will terminate. The for loop iterates 
// n-1 times, so this argument bounds the running time to be
// O(n(n-1)/2)O(n-1) = O(n^3).
// 
// 2) To find a better bound, we consider what happens to the largest 
// element. In the first iteration of the while loop, it moves up to 
// x[n-1]. Then consider the 2nd largest element. In the second iteration
// of the while loop, it moves up to x[n-2]. We thus see that the
// following statement is a loop invariant:
//      after the j-th iteration of the while loop, the elements
//      x[n-j],...,x[n-1] are in the correct position.
// It is clear that this loop invariant is (vacuously) true for j=0.
// Now assume this is true after the j-th iteration. This means, in 
// particular, that
//
// (*) for each i and j with i < n−j <= k we have that x[i] <= x[k]
//
// (because otherwise x[n-j],...,x[n-1] would not have their final 
// values). Now consider the (j+1)-st iteration of the while-loop.
// Clearly, by (*), when we have i=n-j-1 in the for-loop in this 
// iteration, x[i] and x[i+1] do not get switched. Hence the values
// of x[n-j],...,x[n-1] do not change. It remains to show that 
// x[n-j-1] gets its final value in this iteration, that is, a biggest
// element y among x[0],...,x[n-j-1] gets switched to x[n-j-1]. So 
// assume that y is at position x[l] at the beginning of the iteration.
// If l=n-j-1 there is nothing to show. Now, when i=l in the for-loop, 
// then either x[i]=x[i+1] or x[i] and x[i+1] are switching neighbours 
// (because x[i]= y is a biggest element among x[0],...,x[n-j-1]) and 
// they get switched. So then x[i+1]=x[l+1]= y. Similarly, after the 
// for-loop iteration with i=l+1 we have that x[l+2]= y and so on until
// we get x[n-j-1]= y and we are done. This proves that the loop 
// invariant holds throughout the while-loop.
// In particular, after the n-th iteration, we obtain that x[0], ...,
// x[n-1] are in their correct position, that is, the array is sorted,
// switch becomes false, and the while loop terminates. So now we know 
// that there are at most n+1 = O(n) iterations of the while loop, with
// each iteration taking time O(n). So in total we have a running time 
// of O(n)O(n)=O(n^2).
//
// If we take the sequence 2,3,4,...,n,1, the while loop will iterate
// n times, with the inner for loop also taking time cn for each 
// iteration. This gives a running time of at least cn^2.
// 
// If we have an already ordered sequence 1,2,3,...,n, then the while 
// loop will iterate only once, and the time taken will be cn.
//


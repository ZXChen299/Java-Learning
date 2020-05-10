package Seminar3;
//  MA407, solution to ex. 3, 2019
//
//  An implementation of Binary Search in integer arrays.
//  test with "java BinSearch"
//
// a solution to Exercise 3.1 can be found at the end of this file

class BinarySearch {

    static int counter;   // counts the number of iterations in a binary search
    static boolean found; // true, if the key that is searched was found
    
    // Recursive implementation of binary search.
    // Check position of  key  in  x[i] ... x[j-1],
    // which are sorted, by binary search. 
    // Return the index of the first occurence of key (if present),
    // or the index of the first element bigger than key (if not present).
    static int binsearch(int key, int[] x, int i, int j) {
        counter++;
        if ( j - i <= 1) {
            if (x[i]==key) {
                found = true;
            }
            if (x[i]>=key) {
                return i;  
            } else {
                return i+1;
            }
        }
        int m = (i+j)/2;
        if (key > x[m-1]) {
            return binsearch(key, x, m, j);
        } else {
            return binsearch(key, x, i, m);
        }
    }

    // perform binary search on the (whole) array x
    static int binsearch(int key, int[] x) {
        return binsearch(key, x, 0, x.length);
    }

    // optional exercise: insert the value key into x
    // this cannot be done with a void method
    public static int[] insert( int key, int[] x) {
        int position = binsearch(key,x);
        int[] y = new int[x.length+1];
        // copy x to a new bigger array y, 
        // inserting key at the right position
        for(int i=0, j=0; i<y.length; i++) {
            if (i==position) {
                y[i]=key;
            } else {
                y[i]=x[j];
                j++;
            }
        }
        return y;
    }

    // test and document the presence of key in x.
    static void testSearch( int key, int[] x) {
        counter = 0; 
        found = false;
        int k = binsearch (key,x);
        System.out.print("Key "+ key );
        if (found) {
            System.out.print(" found");
        } else {
            System.out.print(" not found, should be");
        }
        System.out.print(" at index " + k );
        System.out.print(", after "+ counter);
        System.out.println(" binary search iterations."); 
    }

    // print an array
    public static void printlnArray(int[] x) {
      for (int i=0; i< x.length; i++) {
          System.out.print(x[i] + " ");
      }
      System.out.println();
    }

    // the main methods performs some binary search tests
    public static void main (String[] args) {
        // test the array 1 3 6 7 7 7 9
        System.out.print("Testing a small array: ");
        int[] sortedArray = {1, 3, 6, 7, 7, 7, 9};
        printlnArray(sortedArray);
        for (int i=0; i <= 10; i++) {
            testSearch(i,sortedArray);
        }
        System.out.print("Inserting 7: ");
        sortedArray = insert(7,sortedArray);
        printlnArray(sortedArray);
        System.out.print("Inserting 8: ");
        sortedArray = insert(8,sortedArray);
        printlnArray(sortedArray);
        System.out.println();

        // test the array 0 2 ... 9999998
        int BIGSIZE = 10000000; // 10 million entries
        System.out.println("Testing a large array with " + BIGSIZE 
                           + " entries: 0 2 4 6 ... " + (2*(BIGSIZE-1)) );
        sortedArray = new int[BIGSIZE];
        for (int i=0; i < sortedArray.length; i++) {
            sortedArray[i] = 2 * i; 
        }
        int key = sortedArray.length / 2;
        testSearch(key,sortedArray);
        testSearch(key+1,sortedArray);
        System.out.println();
        
        // test the array 10 10 ... 10
        System.out.println("Testing a large array with " + BIGSIZE 
                           + " identical entries: 10 10 10 ... 10");
        for (int i=0; i < sortedArray.length; i++) {
            sortedArray[i] = 10; 
        }
        testSearch(10,sortedArray);
        testSearch(9,sortedArray);
        testSearch(11,sortedArray);
    }

}

/////////////////////////////////////////////////////////////////////////////////

/* Solution to 3.1:
- in each recursive call the search space is halved:
1. the first call to binsearch considers the whole array of length n
2. the second (recursive) call to binsearch considers only a part of the
    array of length n/2
3. the third a part of the array of length n/4
4. the fourth a part of the array of length n/8,
    and so on, in general, the k-th call considers a part of the array of
    length n/2^{k-1}.

- we stop when the part of the array has length 1, that is, in round k such
   that n/2^{k-1}=1. Hence k=log(n)+1.
- So binsearch needs roughly log(n) comparisons.
 */

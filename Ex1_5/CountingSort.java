package Ex1_5;

/*
Zixuan Chen
201917656
 */
/*
Ex5.1
(a)for both An and Bn, the running time T(n) = O(n^2)
   for both arrays, the first time of method needs c*n,
   the second time of method is quickSort(x, 1, n) or (x, 0, n-1), both needs c*(n-1)
   etc...
   so T(n) = T(n-1) + c*n, which is O(n^2)
(b)I put the median (which is the floor((n-1)/2)th position of the sorting array) of the array as pivot, then the recursion
   have the formula T(n) = T(n/2-1) + T(n/2-1) + n
                         >= 2T(n/2) + n
   By the master theorem, the T(n) = O(n*log(n))
(c) median = floor((n-1)/2)
    for i in range 0 to n-1 do
      pivotPosition = 0
      for j in range 0 to n-1 do
         if x[i] > x[j] pivotPosition++
         if pivotPosition > median break
         else if pivotPosition == median && j is n-1
            position = pivotPosition
            break
      return position
     The running time T(n) = O(n^2)
 */
/*
Ex5.2
(c) The running time for the max = m is T(n) = n*m
    for each for-loop, I need to check n, n-1,..., 1 entries of array to find
    the number i such that c[i] = true, then I need m for-loops for boolean c
    so the running is m*n(n-1)/2 = O(n^2)
(d) Since for large number of n, we almost use n different numbers in the array,
    So the running time is close to O(n^2)
 */
public class CountingSort {

    private static void countingSort(int[] x){
        int max = 0;
        // find the maximum number
        for (int value : x) if (value > max) max = value;
        boolean[] c = new boolean[max+1];
        //change the boolean array c and also print the sorted array
        for(int i = 0; i < max + 1; i++)
            for (int value : x)
                if (value == i){
                    c[i] = true;
                    System.out.print(i + " "); //can sort the array even if it is not distinct
                }
    }

    private static int[] input(String[] args){
        int[] x = new int[args.length];
        for(int i = 0; i < x.length; i++)
            x[i] = Integer.parseInt(args[i]);
        return x;
    }

    public static void main(String[] args){
        countingSort(input(args));
    }
}

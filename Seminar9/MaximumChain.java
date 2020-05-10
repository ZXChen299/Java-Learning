package Seminar9;
// Konrad Swanepoel 2018
// Bernhard von Stengel 2019 (shorter solution, see end)
// Usage: java MaximumChain x1 x2 ... xn
// Finds a subsequence of consecutive elements of the sequence 
// with maximum sum using dynamic programming

class MaximumChain {

    public static void main(String[] args) {
        if (args.length < 1) {
            return;
        }
        int n = args.length;
        int[] x = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = Integer.parseInt(args[i]);
        }
        int[] max = new int[n];
        // max[i] contains the largest sum of a subsequence of consecutive 
        // elements ending with x[i].
        int[] start = new int[n];
        // start[i] contains the start of a subsequence with sum max[i]
        max[0] = x[0];
        start[0] = 0;
        // if max[i-1] < 0, then it means that all subsequences of consecutive
        // elements ending with x[i-1] are negative, so x[i] on its own has a
        // larger sum than would be obtained by adding anything in front of it.
        // Otherwise we have to add the subsequence with maximum sum ending 
        // in x[i-1] to x[i] to obtain the maximum.
        // So max[i] = x[i] if max[i-1] < 0, else max[i] = x[i] + max[i-1].
        for (int i = 1; i < n; i++) {
            if (max[i-1] < 0) {
                max[i] = x[i];
                start[i] = i;
            } else {
                max[i] = x[i] + max[i-1];
                start[i] = start[i-1];
            }
        }
        // Now find the maximum among all max[i].
        int maxSum = 0; // Default maximum sum of chain is 0 (empty chain)
        int end = -1; // Default last element is invalid -1 (empty chain)
        for (int i = 1; i < n; i++) {
            if (maxSum < max[i]) {
                maxSum = max[i];
                end = i;
            }
        }
        System.out.println("Maximum sum of chain is " + maxSum);
        System.out.print("A corresponding chain is ");
        if (end != -1) { // if not empty chain
            for (int i = start[end]; i <= end; i++) {
                System.out.print(x[i] + " ");
            }
        }
        System.out.println();
    // Time complexity: 
    // 1) Initisialisation (creating arrays etc.) takes O(n).
    // 2) The first loop that calculates all max[i] and start[i] takes time O(n)
    // 3) The second loop that finds the maximum max[i] takes time O(n).
    // 4) In total: O(n)
    
        // the same again, using only 6 variables, no extra array
        int maxsum = 0;
        int maxstart = 0; // first element of maximum chain
        int maxend = 0; // index of last element - 1
        // apart from the currently largest chain, we record
        // the currently largest TAIL chain
        int tailsum = 0;
        int tailstart = 0; // first element of tailsum
        int tailend = 0; // index of last element of tailsum - 1
        // repeatedly extend the tail
        while (tailend < n) { // not yet at end of array
            if (tailsum + x[tailend] < 0) { // reset tailsum
                tailstart = tailend+1; // create empty tailend
                tailsum = 0;
            } else // compute current maximum tailchain
                tailsum += x[tailend];
            tailend ++; // extend the tail
            if (tailsum > maxsum) { // update maximum chain
                maxsum = tailsum;
                maxstart = tailstart;
                maxend = tailend;
            }
        }
        System.out.println("-------shorter direct computation-------");
        System.out.println("Maximum sum of chain is " + maxsum);
        System.out.print("A corresponding chain is ");
        for (int i = maxstart; i < maxend; i++) 
            System.out.print(x[i] + " ");
        System.out.println();
    }
}

/* Example:
java MaximumChain 5 15 -30 10 -5 40 10
maximum sum of a chain: 55
a corresponding chain: 10 -5 40 10 
*/


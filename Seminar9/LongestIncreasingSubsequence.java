package Seminar9;
// Julia Boettcher 2013; updated 2014, 2016, 2019
//
// An example of a dynamic programming solution.
//
// java LongestIncreasing Subsequence x0 x1 x2 x3 ...
// determines the longest increasing subsequence in the sequence x0 x1 x2
// x3 ...

public class LongestIncreasingSubsequence {

    public static void main (String[] args) {
        if (args.length == 0) { 
            return;
        }
        int n = args.length;
        int[] x = new int[n];
        for (int i=0; i<n; i++) {
            x[i] = Integer.parseInt(args[i]);
        }
        int[] len = new int[n]; // len[i] = length of LIS ending in x[i]
        int[] pre = new int[n]; // pre[i] = index of the previous element
                                // in the LIS ending in x[i]
                                // (and -1 if this LIS is only x[i])
        // len[i] = 1 + max_{x[j]<x[i]} len[j]
        for (int i=0; i<n; i++) {
            pre[i] = -1;
            // determine max_{x[j]<x[i]} len[j]
            for (int j=0; j<i; j++) {
                if (x[j]<x[i] && len[j]>len[i]) {
                    len[i]=len[j];
                    pre[i]=j;
                }
            }
            len[i]++;
        }
        int max = 0; // length of LIS = max_{i<n} len[i]
        int end = 0; // index of last element of LIS
        for (int i=0; i<n; i++) {
            if (len[i]>max) {
                max = len[i];
                end = i;
            }
        }
        System.out.println( "the length of the longest increasing subsequence is " + max );
        // the LIS now is the reverse of x[end], x[ pre[end] ], x[ pre[pre[end]] ], ...
        String s = "";
        while (end!=-1) {
            s = x[end] + " " + s;
            end = pre[end];
        }
        System.out.println( "the subsequence is " + s);
    }

}

/* Example:
$ java LongestIncreasingSubsequence 5 2 8 6 3 6 9 7
the length of the longest increasing subsequence is 4
the subsequence is 2 3 6 9 
*/

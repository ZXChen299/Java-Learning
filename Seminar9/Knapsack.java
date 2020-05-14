package Seminar9;
// solve knapsack with dynamic programming
// usage java Knapsack W w1 v1 w2 v2 ...
// W = total weight, wi = weight of item i, vi = value of item i
// java Knapsack 10 3 6 4 5 5 10 
// total weight: 10
// item 1: weight 3 value 6 (pack)
// item 2: weight 4 value 5 (don't pack)
// item 3: weight 5 value 10 (pack)
// packed weight: 8
// packed value:  16, should be 16 

public class Knapsack {
// comes from input
public int W; // maximum weight
public int n; // number of items
public int[] weight; // weight of item 0...n-1
public int[] value;  // value of  item 0...n-1
// found by solve()
public boolean[] pack;  // pack   item 0...n-1
public int achieved_value; // achieved_value

public Knapsack(String[] args) { // set up parameters, args nonempty
    W = Integer.parseInt(args[0]);
    n = (args.length-1)/2; // even argslength: ignore last item (no weight)
    weight = new int[n];
    value = new int[n];
    pack = new boolean[n];
    for (int i=0; i<n; i++) {
        weight[i] = Integer.parseInt(args[2*i+1]);
        value[i] = Integer.parseInt(args[2*i+2]);
    }
}

public void print() {
    System.out.println ("maximum weight "+W);
    int sum = 0, v=0;
    for (int i=0; i<n; i++) {
        System.out.print("item: "+(i+1));
        System.out.print(" weight "+ weight[i]);
        System.out.print(" value "+ value[i]);
        if (pack[i]) {
            System.out.println(" (pack)");
            sum += weight[i];
            v += value[i];
        } else
            System.out.println(" (don't pack)");
    }
    System.out.println ("packed weight: "+sum);
    System.out.print ("packed value:  "+ v);
    System.out.println (", should be "+ achieved_value);
}

private int mval[][]; // mval[i][c] = max value for items 0...i-1 up to weight c
private boolean use[][]; // use[i][c] = use item i for weight c

public void solve() { // solve Knapsack
    if (n==0) return; // no items
    mval = new int[n][W+1];
    use = new boolean[n][W+1];
    // fill in row 0
    for (int c=0; c<=W; c++) 
        if (weight[0] <= c) { // item 0 fits 
            mval[0][c] = value[0];
            use [0][c] = true;
        }
    // fill in rows 1...n-1
    for (int i=1; i<n; i++) 
        for (int c=0; c<=W; c++) 
            if (weight[i] > c) // cannot fit
                mval[i][c] = mval[i-1][c] ;
            else { // weight[i] <= c
                int possible = mval[i-1][c-weight[i]] + value[i];
                if (mval[i-1][c] < possible) {
                    mval[i][c] = possible; 
                    use [i][c] = true;
                } else
                    mval[i][c] = mval[i-1][c] ;
            }
    // testprint();
    // now decide the packing, starting from last item
    achieved_value = mval[n-1][W];
    int usedweight = W;
    for (int i = n-1; i>=0; i--) 
        if (use[i][usedweight]) {
            pack[i] = true;
            usedweight -= weight[i];
        }
}

public static void main(String[] args) {
    if (args.length == 0)
        return;
    Knapsack K = new Knapsack(args);
    // System.out.println("-------- first print");
    // K.print();
    // System.out.println("-------- solve and testprint");
    K.solve();
    // K.testprint();
    // System.out.println("-------- print solution"); 
    K.print();
}
}
/*
java Knapsack 25 12 24 7 13 11 23 8 15 9 16
maximum weight 25
item: 1 weight 12 value 24 (pack)
item: 2 weight 7 value 13 (don't pack)
item: 3 weight 11 value 23 (pack)
item: 4 weight 8 value 15 (don't pack)
item: 5 weight 9 value 16 (don't pack)
packed weight: 23
packed value:  47, should be 47
*/

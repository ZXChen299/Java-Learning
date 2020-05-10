package Ex9;

/*
Zixuan Chen
201917656
 */

public class Knapsack {
    public int W; // maximum weight
    public int n; // number of items
    public int[] weight; // weight of item 0...n-1
    public int[] value;  // value of  item 0...n-1
    // found from the solution
    public boolean[] pack;     // pack   item 0...n-1
    public int achieved_value; // achieved_value
    public int achieved_weight;
    public Knapsack(String[] args) { // set up parameters, args nonempty
        W = Integer.parseInt(args[0]);
        n = ( args.length - 1 ) / 2;
        weight = new int[n+1];
        value = new int[n+1];
        // enter the weight and value
        for(int i = 1; i < args.length; i++)
            if( i % 2 == 0 ) value[i/2] = Integer.parseInt(args[i]);
            else weight[(i+1)/2] = Integer.parseInt(args[i]);

        pack = new boolean[n+1];
        mval = new int[n+1][W+1];
        use = new boolean[n+1][W+1];
    }
    public void print() {
        //these two arguments are found from solution
        achieved_value = 0;
        achieved_weight = 0;
        System.out.println("maximum weight " + W);

        for(int i = 1; i <= n; i++){
            if(pack[i]) {
                System.out.println("item: " + i + " weight " + weight[i] + " value " + value[i] + " (pack)");
                achieved_value += value[i];
                achieved_weight += weight[i];
            }
            else
                System.out.println("item: " + i + " weight " + weight[i] + " value " + value[i] + " (don't pack)");
        }

        System.out.println("packed weight: " + achieved_weight);
        System.out.println("packed value: " + achieved_value + ", should be " + mval[n][W]);
    }
    public void solve() { // solve Knapsack
        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= W; j++)
                if (weight[i] <= j)
                    if (value[i] + mval[i - 1][j - weight[i]] > mval[i - 1][j]) { //max{c(i-1,j),value[i] + c(i-1,j-weight[i]))}
                        mval[i][j] = value[i] + mval[i-1][j - weight[i]];
                        use[i][j] = true;
                    }
                    else mval[i][j] = mval[i-1][j];
                else mval[i][j] = mval[i-1][j];

        achieved_value = mval[n][W];
        for(int i = n; i >= 1; i--)
            for(int j = W; j >= 1; j--)
                if(use[i][j] == true && mval[i][j] == achieved_value){
                    achieved_value -= value[i];
                    pack[i] = true;
                }

    }
    private int mval[][]; // mval[i][c] = max value for items 0...i-1 up to weight c
    private boolean use[][]; // use[i][c] = use item i for weight c
    private void testprint() {
        for(int i = 1; i <= n; i++){
            for(int j = 0; j <= W; j++)
                if(!use[i][j]) System.out.print("- ");
                else System.out.print("P ");
                System.out.println();
            for(int j = 0; j <= W; j++)
                System.out.print(mval[i][j] + " ");
            System.out.println();
    }

}
    public static void main(String[] args) {
        Knapsack pack = new Knapsack(args);
        pack.solve();
        pack.print();
    }

}

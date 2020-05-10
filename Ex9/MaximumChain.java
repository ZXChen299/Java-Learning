package Ex9;

/*
Zixuan Chen
201917656
 */

public class MaximumChain {

    public static void main(String[] args){
        int[] x = new int[args.length];
        for(int i = 0; i < args.length; i++) {
            x[i] = Integer.parseInt(args[i]);
        }
        int[] sum = new int[x.length]; // the maximum sum of chain that ends with x[i]
        int[] pre = new int[x.length]; // the previous item of x[i] in the maximum chain
        sum[0] = x[0];
        for(int i = 1; i < x.length; i++){
            int tempSum = sum[i - 1] + x[i];
            if( tempSum > x[i] ) {
                sum[i] = tempSum;
                pre[i] = i-1;
            }
            else {
                sum[i] = x[i];
                pre[i] = -1;
            }
        }

        int max = sum[0];
        int end = 0;
        for(int i = 0; i < sum.length; i++)
            if( max < sum[i] ) {
                max = sum[i];
                end = i;
            }

        System.out.println("maximum sum of a chain: " + max);
        System.out.print("a corresponding chain: ");
        while(end != -1){
            System.out.print(x[end] + " ");
            end = pre[end];
        }
    }
}

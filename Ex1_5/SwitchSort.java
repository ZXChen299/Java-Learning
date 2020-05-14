package Ex1_5;

public class SwitchSort {

    static long counter = 0;

    static void switchSort(int[] x){
        int temp = 0;
        int j = 1;
        for (int k = 0; k < x.length; k++) {
            j++;
            for(int i = x.length-j; i >= 0; i--){
                if(x[i] > x[i+1]){
                    temp = x[i];
                    x[i] = x[i+1];
                    x[i+1] = temp;
                    counter++;
                }
            }
        }
    }

    static int[] randomFill(int n){
        int[] x = new int[n];
        for(int i = 0; i < n; i++) x[i] =(int)(Math.random()*(n+1));
        return x;
    }

    static void printInfo(int n, long time){
        System.out.println("Sorting " + n + " numbers: ");
        System.out.println("Needed " + time + " ms.");
        System.out.println("Needed " + counter + " switches.");
        System.out.println();
    }

    static void printArray(int[] x, String info){
        System.out.print(info);
        for(int i = 0; i < x.length; i++) System.out.print(x[i] + " ");
        System.out.println();
    }

    static void test (int n) {
        int[] x = randomFill(n);
        if(x.length <= 100) printArray(x, "Before sorting: ");
        long time = System.currentTimeMillis();
        switchSort(x);
        time = System.currentTimeMillis() - time;
        if(x.length <= 100) printArray(x, "After  sorting: ");
        printInfo(n, time);
        counter = 0;
    }

    public static void main(String[] args){
        test(5);
        test(10000);
        test(20000);
        test(40000);
    }

}

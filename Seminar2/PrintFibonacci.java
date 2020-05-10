package Seminar2;

// Usage: java PrintFibonacci n
// Prints out the first n Fibonacci numbers if n > 2.
class PrintFibonacci {

    static void printArray(int[] a) {
        for (int i =0; i < a.length - 1; i++) {
            System.out.print(a[i] + ", ");
        }
        System.out.println(a[a.length-1]);
    }
    
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        if (n < 3) {
            return;
        }
        int[] f = new int[n];
        f[0] = 1;
        f[1] = 1;
        for (int i=2; i < n; i++) {
            f[i] = f[i-1] + f[i-2];
        }
        printArray(f);
    }
}

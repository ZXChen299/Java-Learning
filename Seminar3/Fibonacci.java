package Seminar3;

// Usage: java Fibonacci n
// Prints the n-th Fibonacci number.
class Fibonacci {
    static int level = 0;

    static long fibonacci(int n) {
        for (int i = 0; i <= level; i++) System.out.print("   ");
        if (n < 3) {
            System.out.println("fibonacci(" + n + "): return 1");
            return 1;
        }
        else {
            System.out.println("fibonacci(" + n + "): calculate fibonacci(" + (n-1) + ")+fibonacci(" + (n-2) + ")");
            level++;
            long f1 = fibonacci(n-1);
            long f2 = fibonacci(n-2);
            long f = f1+f2;
            for (int i = 0; i <= level; i++) System.out.print("   ");
            System.out.println("return fibonacci(" + n + ") = " + f1 + " + " + f2 + " = " + f);
            level--;
            return f;
        }
    }

    public static void main (String[] args) {
        int n = Integer.parseInt(args[0]);
        System.out.println( fibonacci(n) );
    }
}

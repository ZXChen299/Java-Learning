package Seminar1;

// Usage: java Swap2 num1 num2
// Attempts to print out num2 num1, but fails.
class Swap2 {

    static void swap(int first, int second) {
        int temp = first;
        first = second;
        second = temp;
    }

    public static void main (String[] args) {
        if (args.length < 2) {
            return;
        }
        int a = Integer.parseInt( args[0] );
        int b = Integer.parseInt( args[1] );
        swap(a,b);
        System.out.println(a + " " + b);
    }

}

            

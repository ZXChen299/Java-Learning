package Seminar1;

// Usage: java Swap num1 num2
// Prints out num2 num1
class Swap {

    public static void main (String[] args) {
        if (args.length < 2) {
            return;
        }
        int a = Integer.parseInt( args[0] );
        int b = Integer.parseInt( args[1] );
        int temp = a;
        a = b;
        b = temp;
        System.out.println(a + " " + b);
    }

}

            

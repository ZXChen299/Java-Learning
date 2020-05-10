package Seminar1;// java PrimeAtLeast n
// prints the next prime bigger or equal to the integer n

class PrimeAtLeast {

    // check if k is a prime number
    static boolean isPrime (long k) {
        // If k is no prime number then it has a factor which is at most sqrt(k)
        for (long i = 2; i <= Math.sqrt(k); i++) {
            if (k%i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main (String[] args) {
        // if no argument is given, we return the smallest prime, 2
        if (args.length == 0) {
            System.out.println("2");
            return;
        }
        long lowerBound = Integer.parseInt( args[0] );
        if (lowerBound<=2) {
            System.out.println("2");
            return;
        }
        // find the next prime after lowerBound
        long prime = lowerBound;
        while ( !isPrime(prime) ) { 
            prime++;
        }
        System.out.println( prime );
    }
}

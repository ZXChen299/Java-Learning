package Ex1_5;

/*Zixuan Chen
id: 2019176568*/
/*
Ex 3.1
for n entries, the maximum number of comparisons is n/2 + 1
 */
public class BinarySearch {

    static int counter = 0; // to count the number of iterations
    static boolean found; // this boolean is useless in my programme, just add it for exercise 3
    // the recursive method
    static int binsearch( int key, int[]x, int i, int j ){
        counter++;
        //whether reach the smallest interval or not
        if( Math.abs( i - j ) == 1 ) {
            if ( key == x[i] || key == x[j] ) { found = true; found( key, x, i, j ); }
            else { found = false; notFound( key, x, i, j ); }
        }
        //to do the recursion (obtain the smaller interval)
        else{
            int n = i + (int)Math.floor( ( j - i )/2 );
            if( x[n] < key ) return binsearch( key, x, n, j );
            else return binsearch( key, x, i, n );
        }
        return 0;
    }
    // if the key is found in the array
    static void found(int key, int[] x, int i, int j){
        if ( key == x[i] )  System.out.println( "Key "+ key + " found at index " + i + ", after " + counter + " binary search iterations." );
        else System.out.println( "Key "+ key + " found at index " + j + ", after " + counter + " binary search iterations." );
    }
    // if the key is not found in the array
    static void notFound(int key, int[] x, int i, int j){
        // if not found, create a inserted array
        // to avoid print large array, I only print the array whose length <= 10 for test
        if ( key < x[i] ) {
            System.out.println( "key " + key + " not found, should be at index " + i + ", after " + counter + " binary search iterations." );
            if( x.length < 10 ) { System.out.print( "The new inserted array is: " ); print( insert( key, x, i ) ); }
        }
        else if ( key > x[i] && key < x[j] ) {
            System.out.println( "key " + key + " not found, should be at index " + j + ", after " + counter + " binary search iterations." );
            if( x.length < 10 ) { System.out.print( "The new inserted array is: " ); print( insert( key, x, j ) ); }
        }
        else if ( key > x[j] ) {
            j++;
            System.out.println( "key " + key + " not found, should be at index " + j + ", after "+ counter + " binary search iterations." );
            if( x.length < 10 ) { System.out.print( "The new inserted array is: " ); print( insert( key, x, j ) ); }
        }
    }
    // insert method, to insert the not-found number
    static int[] insert(int key, int[] x, int p){
        int len = x.length;
        int[] y = new int[ len+1 ];
        // did not change the array before pth
        for( int i = 0; i < p; i ++ ){
            y[i] = x[i];
        }
        y[p] = key; // insert
        for( int i = p; i < len; i++ ){
            y[i+1] = x[i];
        }
        return y;
    }
    // to change the args to int array
    static int[] input(String[] args){
        int len = args.length;
        int[] x = new int[ len ];
        for( int i =0 ; i < len; i++ ){
            x[i] = Integer.parseInt( args[i] );
        }
        return x;
    }
    // create the first large array 0 2 4 6 ... 19999998
    static int[] largeArray1(){
        int[] x = new int[ 10000000 ];
        x[0] = 0;
        for( int i = 1; i < 10000000; i++ ){
            x[i] = x[i-1] + 2;
        }
        return x;
    }
    // create the second large array 10 10 ... 10
    static int[] largeArray2(){
        int[] x = new int[ 10000000 ];
        for( int i = 0; i < 10000000; i++ ){
            x[i] = 10;
        }
        return x;
    }
    // to print a array
    static void print(int[] x){
        int len = x.length;
        for ( int i = 0; i < len; i++ ){
            System.out.print( x[i] + " " );
        }
        System.out.println();
    }

    public static void main(String[] args){
        // I only do the test for the three array given by example, and 4 for the first one, 3 & 3 for the remained ones as example
        int len = args.length;
        if( len < 0 ) return;
        int[] test = input( args );
        int[] x1 = new int[] { 1,3,6,7,7,7,9 };
        int[] x2 = largeArray1();
        int[] x3 = largeArray2();
        // same as example, test the first 4 number in array 1
        System.out.println( "Testing a small array：1 3 6 7 7 7 9" );
        for( int i = 0; i < 4; i++ ){
            found = false;
            int ans = binsearch( test[i], x1, 0, 6 );
            counter = 0;
        }
        System.out.println();
        // same as example, test 5th, 6th number in array 2
        System.out.println( "Testing a large array with 10000000 entries：0 2 4 6 ... 19999998" );
        for( int i = 4; i < 6; i++ ){
            found = false;
            int ans =  binsearch( test[i], x2, 0, 9999999 );
            counter = 0;
        }
        System.out.println();
        // same as example, test 7th, 8th, 9th number in array 3
        System.out.println( "Testing a large array with 10000000 identical entries：10 10 10 ... 10" );
        for( int i = 6; i < 9; i++ ){
            found = false;
            int ans =  binsearch( test[i], x3, 0, 9999999 );
            counter = 0;
        }
        System.out.println();
    }

}

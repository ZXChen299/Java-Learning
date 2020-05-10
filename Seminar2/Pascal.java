package Seminar2;
// javac Pascal x
// calculates the first x rows of Pascal's triangle
class Pascal {

    static void calculatePascal (int[][] t) {
        for (int i=0; i<t.length; i++) {
            // the first entry in each row is 1
            t[i][0] = 1;
            for (int j=1; j<t[i].length-1; j++) {
                // all other entries are the sum of two entries in the
                // previous row
                t[i][j] = t[i-1][j-1] + t[i-1][j];
            }
            // the last entry in each row is 1
            t[i][ t[i].length-1 ] = 1;
        }
    }

    static void printTriangle (int[][] t) {
        for (int i=0; i<t.length; i++) {
            for (int j=0; j<t[i].length; j++) {
                System.out.print( t[i][j] + " " );
            }
            System.out.println();
        }
    }

    public static void main (String[] args) {
        int lines = Integer.parseInt( args[0] );
        int[][] triangle = new int[lines][];
        for (int i=0; i<lines; i++) {
            triangle[i] = new int[ i+1 ];
        }
        calculatePascal(triangle);
        printTriangle(triangle);
    }

}

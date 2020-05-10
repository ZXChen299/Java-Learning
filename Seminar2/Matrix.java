package Seminar2;
// java Matrix n m max
// generates the random n times m matrix a and the random m times m matrix b,
// both with integer values in [0,max] and calculates a*b and b^T

class Matrix {

    // generate a random nxm matrix, with entries betwen 0 and max
    static int[][] randomMatrix (int n, int m, int max) {
        // see exercise 2.1
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = (int) (Math.random() * (max+1));
            }
        }
        return matrix;
    }

    // calculate the matrix product of the two matrices a and b
    static int[][] product ( int[][] a, int[][] b) {
        // see exercise 2.2
        if (a[0].length != b.length) {
            return null;
        }
        int[][] result = new int[a.length][b[0].length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = 0;
                for (int k = 0; k < a[0].length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    // transpose the matrix, in place (assuming it is square)
    static void transpose (int[][] matrix) {
        // see exercise 2.3
        if (matrix.length != matrix[0].length) {
            return;
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    static void printSpaces ( int n)
    {
        for (int i = 0; i < n; i++) {
            System.out.print(" ");
        }
    }

    // print: name = matrix, with column entries of the matrix aligned
    static void printMatrix ( String name, int[][] matrix) {
        // see exercise 2.4
        if (matrix == null) {
            System.out.println( "null" );
            return;
        }
        // find length of largest entry
        int maxLength = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int length = Integer.toString( matrix[i][j] ).length();
                if (length > maxLength) {
                    maxLength = length;
                }
            }
        }
        // print the matrix
        for (int i=0; i<matrix.length; i++) {
            if (i == matrix.length/2) {
                System.out.print( name + " = " );
            } else {
                printSpaces( name.length() + 3 );
            }
            System.out.print("( ");
            for (int j=0; j<matrix[0].length; j++) {
                String entry = Integer.toString( matrix[i][j] );
                printSpaces( maxLength - entry.length() );
                System.out.print(entry + " ");
            }
            System.out.println(")");
        }
    }

    public static void main (String[] args) {
        if (args.length<3) {
            return;
        }
        int n = Integer.parseInt( args[0] );
        int m = Integer.parseInt( args[1] );
        int max = Integer.parseInt( args[2] );
        int[][] a = randomMatrix( n, m, max );
        int[][] b = randomMatrix( m, m, max );
        printMatrix( "a", a );
        System.out.println();
        printMatrix( "b", b );
        System.out.println();
        printMatrix( "a*b", product(a,b) );
        System.out.println();
        transpose(b);
        printMatrix( "b^T", b );
    }

}


// example: java Matrix 3 6 20
//      ( 10 13 15 19 12 15 )
//  a = (  5  2 17 12  9  9 )
//      (  0  8  1 20  6  9 )
//
//      (  1  1 13 19 11 19 )
//      ( 17 19 14  5  6 17 )
//      (  1 13  6 13 17 20 )
//  b = ( 16  2  9 19  6 15 )
//      (  0 15  5  8  1  2 )
//      ( 12 15 20  1  9 17 )
//
//        (  730  895  933  922  704 1275 )
//  a*b = (  356  558  528  635  518  820 )
//        (  565  430  508  490  272  621 )
//
//        (  1 17  1 16  0 12 )
//        (  1 19 13  2 15 15 )
//        ( 13 14  6  9  5 20 )
//  b^T = ( 19  5 13 19  8  1 )
//        ( 11  6 17  6  1  9 )
//        ( 19 17 20 15  2 17 )


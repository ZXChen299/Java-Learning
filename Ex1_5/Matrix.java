package Ex1_5;/*
ZixuanChen
201917656
 */

import java.util.Random;

public class Matrix {


    static void printWhite(int n){ // to print the suitable numbers of blank
        for(int i = 0; i < n; i++){
            System.out.print(" ");
        }
    }

    static int findMaxNumber(int[][] matrix, int n, int m){ // find the maximum number of the whole matrix
        int maxNumber = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(maxNumber < matrix[i][j]){
                    maxNumber = matrix[i][j];
                }
            }
        }
        return maxNumber;
    }

    static void printMatrix(String name, int[][] matrix){ // to print the matrix in a suitable way
        int n = matrix.length;
        int m = matrix[0].length;

        //compute the maximum number and the length of this number
        int maxNumber = findMaxNumber(matrix, n, m);
        String maxN = "" + maxNumber;
        int numberLen = maxN.length();

        //compute the length of the name and " = "
        String name2 = name + " = ";
        int nameLine = 0;
        int nameLen = name2.length();

        // to print the name and " = " in a suitable place (if the numbers of row is even, then print the name in the (n/2-1)th row)
        if(n % 2 == 0){ nameLine = n / 2 - 1; }
        else { nameLine = (n - 1) / 2; }
        for(int i = 0; i < n; i ++){

            //decide to print blank or the name
            if( i != nameLine) {printWhite(nameLen);}
            else {System.out.print(name2);}
            System.out.print("( ");

            //main codes to print
            for(int j = 0; j < m; j++){
                if(matrix[i][j] < maxNumber){
                    String a = "" + matrix[i][j];
                    printWhite(numberLen - a.length());
                }
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(")");
        }
        System.out.println();
    }

    static int[][] randomMatrix (int n, int m, int max){ // create random matrix
        int[][] matrix = new int[n][m];
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < m; j++){
                matrix[i][j] = new Random().nextInt(max); // create random number
            }
        }
        return matrix;
    }

    static int[][] product(int[][] a, int[][] b){ // calculate a * b
        int n = a.length;
        int m = b[0].length;
        int p = a[0].length;
        int[][] c = new int[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                c[i][j] = 0;
                for(int k = 0; k < p; k++){
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return c;

    }

    static int[][] transpose(int[][] matrix){ // calculate b ^ T without creating a new matrix
        int n = matrix.length;
        int m = matrix[0].length;
        int temp = 0; // temporary memory
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < m; j++){
                    temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
            }
        }
        return matrix;
    }

    public static void main(String[] args){
        int n = Integer.parseInt( args[0] );
        int m = Integer.parseInt( args[1] );
        int max = Integer.parseInt( args[2] );

        //create two random matrices
        int[][] matrix1 = randomMatrix(n, m, max);
        int[][] matrix2 = randomMatrix(m, m, max);
        printMatrix("a", matrix1);
        printMatrix("b", matrix2);

        //compute a*b
        int[][] matrix3 = product(matrix1, matrix2);
        printMatrix("a * b", matrix3);

        //compute b^T
        int[][] matrix4 = transpose(matrix2);
        printMatrix("b ^ T", matrix4);

    }

}

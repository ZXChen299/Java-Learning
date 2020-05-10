package Seminar9;
// Julia Boettcher, 2015; updated 2016
//
// Calculating edit distances: An example for dynamic programming.
//
// java EditDistance word1 word2
// determines the edit distance between these two words
// and also prints the matrix m with m[i][j]=edit distance between
// word1[0..i-1] and word2[0..j-1], and the solution

public class EditDistanceSol {
    
    // printing a matrix nicely formatted
    // taken from solution to exercise set 2
    static void printMatrix (int[][] matrix) {   
        // determine the width of the widest entry
        int colWidth = 0;
        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[0].length; j++) {
                String entry = Integer.toString( matrix[i][j] );
                if (entry.length() > colWidth) {
                    colWidth = entry.length();
                }
            }
        }
        // print the matrix
        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[0].length; j++) {
                String entry = Integer.toString( matrix[i][j] );
                // right align the entries in each column
                for (int k=0; k < colWidth-entry.length(); k++) {
                    System.out.print( " " );
                }
                System.out.print(entry + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        if (args.length<2) return;
        char[] x = args[0].toCharArray();
        char[] y = args[1].toCharArray();
        // eDist[i][j] = dist(x[0..i-1],x[0..j-1])
        int[][] eDist = new int[x.length+1][y.length+1];
        // minimzer stores which decission was taken when determining the min. below
        // its values are:  0: base case; 1: 1st term, 2: 2nd term, 3: 3rd term
        int[][] minimizer = new int[x.length+1][y.length+1];
        for (int i=1; i<x.length+1; i++) { // base case
            eDist[i][0] = i;
        }
        for (int j=1; j<y.length+1; j++) { // base case
            eDist[0][j] = j;
        }
        // solving larger subproblems, via the relation
        // eDist[i][j] = 
        //   min{eDist[i-1][j]+1,eDist[i][j-1]+1,eDist[i-1][j-1]+diff(i-1,j-1)}
        for (int i=1; i<x.length+1; i++) {
            for (int j=1; j<y.length+1; j++) {
                int term1 = eDist[i-1][j]+1;    // 1st term in min
                int term2 = eDist[i][j-1]+1;    // 2nd term in min
                int term3 = eDist[i-1][j-1];    // 3rd term in min
                if (x[i-1]!=y[j-1]) {           // incorporate diff(i-1,j-1)
                    term3++;
                }
                // determine the minimum among the three terms
                if (term1<=term2 && term1<=term3) {
                    eDist[i][j] = term1;
                    minimizer[i][j] = 1;
                } else if (term2<=term3) {
                    eDist[i][j] = term2;
                    minimizer[i][j] = 2;
                } else {
                    eDist[i][j] = term3;
                    minimizer[i][j] = 3;
                }
            }
        }
        System.out.println( "Edit distance: " + eDist[x.length][y.length]);
        System.out.println();
        System.out.println( "eDist = " );
        printMatrix(eDist);
        System.out.println();
        // determine and print the solution
        String xAlign = "";
        String yAlign = "";
        int i=x.length;
        int j=y.length;
        while (i>0 || j>0) {
            if (i==0 || minimizer[i][j]==2) {
                xAlign = "- " + xAlign;
                yAlign = y[j-1] + " " + yAlign;
                j--;
            }
            else if (j==0 || minimizer[i][j]==1) {
                xAlign = x[i-1] + " " + xAlign;
                yAlign = "- " + yAlign;
                i--;
            } else if (minimizer[i][j]==3) {
                xAlign = x[i-1] + " " + xAlign;
                yAlign = y[j-1] + " " + yAlign;
                i--;
                j--;
            }
        }
        System.out.println("Optimal alignment:");
        System.out.println(xAlign);
        System.out.println(yAlign);
    }

}


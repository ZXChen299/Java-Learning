package Seminar2;

class Vectors {

    // calculates the vector sum of two vectors
    static int[] sum (int[] u, int[] v) {
        // not a valid operation if the vectors have different dimensions
        if (u.length != v.length) {
            return null;
        }
        int[] w = new int[ v.length ];
        for (int i=0; i<w.length; i++) {
            w[i] = u[i] + v[i];
        }
        return w;
    }

    // calculates the dot product of two vectors
    static int product (int[] u, int[] v) {
        // not a valid operation if the vectors have different dimensions
        if (u.length != v.length) {
            return 0;
        }
        int s = 0;
        for (int i=0; i<u.length; i++) {
            s += u[i]*v[i];
        }
        return s;
    }

    // calculates the Euclidean distance between two vectors
    static double distance(int[] u, int[] v) {
        // not a valid operation if the vectors have different dimensions
        if (u.length != v.length) {
            return -1;
        }
            int s = 0;
            for (int i =0; i < u.length; i++) {
                s += (u[i] - v[i]) * (u[i] - v[i]);
            }
            return Math.sqrt(s);
    }

    static void printArray(int[] a) {
        if (a == null || a.length == 0) {
            System.out.println();
            return;
        }
        System.out.print("( ");
        for (int i = 0; i < a.length - 1; i++) {
            System.out.print(a[i] + ", ");
        }
        System.out.println(a[a.length-1] + " )");
    }

    public static void main (String[] args) {
        int dim = args.length/2;
        int[] u = new int[dim];
        int[] v = new int[dim];
        for (int i=0; i<dim; i++) {
            u[i] = Integer.parseInt( args[i] );
            v[i] = Integer.parseInt( args[dim+i] );
        }
        System.out.print( "u = " );
        printArray(u);
        System.out.print( "v = " );
        printArray(v);
        System.out.print( "u+v = " );
        printArray( sum(u,v) );
        System.out.println( "uv = " + product(u,v) );
        System.out.println( "dist(u,v) = " + distance(u,v) );        
    }

}

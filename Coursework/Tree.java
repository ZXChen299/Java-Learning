package Coursework;

/*
candidate number: 36609
Tree.java
for question for (c) to (g)
 */

public class Tree {
    private Item root = null; // the root of the tree

    public int[] x; // x label
    public int[] y; // y label
    public int[] sorted; // numbers in order
    public int[] unsorted; // numbers not in order
    public int[] replace; // numbers which are deleted

    public int total_depth = 0; // total depth, to obtain average depth
    public int count = 0; // count the number of numbers
    public int[] grid = new int[4]; // max_x = grid[0]; max_y = grid[1]; min_x = grid[2]; min_y = grid[3]

    // Item for binary search tree
    private class Item {
        private int  value;
        private Item left, right, parent;

        
        // create a new item with parent p
        private Item (int x, Item p) { 
            value = x; 
            parent = p;
        }

        // a string representation of the item
        public String toString() {
            String s = value + ", left: ";
            if (left==null) {
                s += "null";
            } else {
                s += left.value;
            }
            s += ", right: ";
            if (right==null) {
                s += "null";
            } else {
                s += right.value;
            }
            return s;
        }
    }

    // inserts x in the tree unless present;
    // returns pointer to item with value x
    public Item insert(int x) {
        if (root == null) {  // if the tree is empty
            root = new Item(x,null);
            return root;
        }
        Item p = root, q = null; // q stores the parent of p
        // find x, or the place where it should be inserted
        while (p != null) {
            q = p;
            if (x == p.value) { // value found
                return p;
            } else if (x < p.value) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        // insert x as child of q
        p = new Item(x,q);
        if (x < q.value) {
            q.left = p;  // q.left was null before
        } else {
            q.right = p; // q.right was null before
        }
        return p;
    }

    // which generates a random permutation of the numbers 1, . . . , n , and inserts them into the tree
    public int[] randomperm(int n, int d){
        int min = 1;
        int max = n + d;
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        int[] result = new int[n];
        int count = 0;
        while(count < n) {
            int num = (int) (Math.random() * (max - min + 1)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if(num == result[j]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                result[count] = num;
                count++;
            }
        }
        return result;
    }

    // replace method
    public int[] replace(int n, int d){
        int[] miss = miss(n,d);
        int[] random = randomperm(n,0);
        int indices;
        replace = new int[d];

        for( int i = 0; i < d; i++ ){
            indices = random[i] - 1;
            replace[i] = unsorted[indices];
            unsorted[indices] = miss[i];
        }

        return miss;

    }

    // the tree with known numbers
    public void normal( String[] args ){
        int x;

        // set up public variables
        unsorted = new int[args.length];
        setup(args.length);

        for (int i=0; i < args.length; i++) {
            x = Integer.parseInt(args[i]);
            unsorted[i] = x;
            insert(x);
        }

        // print latex
        outheader();
        outinfo();
        outgraph();
        outfooter();
    }

    // tests the generation of a random permutation, i.e. 20 random permutations for n = 5
    public void randomtest(){
        int n = 5;
        int min = 1;
        int max = n;

        int[] result = new int[n];
        int count = 0;
        for(int t = 1; t <= 20; t++){
            while(count < n) {
                int num = (int) (Math.random() * (max - min + 1)) + min;
                boolean flag = true;
                for (int j = 0; j < n; j++) {
                    if( num == result[j] ){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    System.out.print(num + " ");
                    count++;
                }
            }
            result = new int[n];
            count = 0;
            System.out.println();
        }
    }

    // generates a random permutation of the numbers 1, . . . , n , and inserts them into the tree and displays it
    public void randominsert (int n){
        unsorted = new int[n];
        setup(n);
        // create a tree
        unsorted = randomperm(n, 0);
        for (int i=0; i < unsorted.length; i++) {
            insert( unsorted[i] );
        }
        // print latex
        outheader();
        outinfo();
        outgraph();
        outfooter();
    }

    // generates n random numbers to be inserted, and subsequently replaces d further numbers
    public void randomreplace(int n, int d){
        unsorted = new int[n];
        setup(n);
        unsorted = randomperm(n , d);

        for (int i=0; i < unsorted.length; i++) {
            insert( unsorted[i] );
        }
        // print latex
        outheader();
        System.out.println("— initial graph: —");
        System.out.println();
        outinfo();
        outgraph();
        System.out.println();

        System.out.println("— alternate delete/insert: —");
        System.out.println();

        int[] miss = replace(n,d);

        for(int i = 0; i < replace.length; i++){
            System.out.print(replace[i] + " ");
        }

        System.out.println("are deleted");
        System.out.println();
        for(int i = 0; i < miss.length; i++){
            System.out.print(miss[i] + " ");
        }
        System.out.println("are newly inserted");
        System.out.println();

        // new graph
        clear();
        setup(n);
        for (int i=0; i < unsorted.length; i++) {
            insert( unsorted[i] );
        }
        System.out.println("— new graph: —");
        System.out.println();
        outinfo();
        outgraph();

        outfooter();
    }

    // repeats this r times and prints a table with r columns to compare the resulting average tree depths
    public void randomrepeats(int n, int d, int r){
        String[] old_depth = new String[r];
        String[] new_depth = new String[r];
        double x;
        int times  = 0;
        while( times < r ){
            clear();
            setup(n);
            unsorted = new int[n];
            unsorted = randomperm(n , d);

            for (int i=0; i < unsorted.length; i++) {
                insert( unsorted[i] );
            }

            getTotal_depth(root,0);
            x =  (total_depth + 0.0) / n;
            old_depth[times] = String .format("%.2f",x);

            replace(n,d);

            clear();
            setup(n);
            for (int i=0; i < unsorted.length; i++) {
                insert( unsorted[i] );
            }

            getTotal_depth(root,0);
            x = (total_depth + 0.0) / n;
            new_depth[times] = String .format("%.2f",x);

            times++;

        }

        String old_depth_latex = "Old depth:";
        String new_depth_latex = "New depth:";
        for(int i = 0; i < r; i++){
            old_depth_latex += "& " + old_depth[i];
            new_depth_latex += "& " + new_depth[i];
        }
        old_depth_latex += "\\\\";
        new_depth_latex += "\\\\";


        outheader();
        System.out.println("n = " + n + ", d = " + d + ", r = " + r);
        System.out.println();
        System.out.println("\\begin{tabular}{|l|c|c|c|c|c|c|c|c|c|c|}");
        System.out.println("\\hline");
        System.out.println(old_depth_latex);
        System.out.println("\\hline");
        System.out.println(new_depth_latex);
        System.out.println("\\hline");
        System.out.println("\\end{tabular}");
        outfooter();
    }

    // find the missed number
    public int[] miss(int n, int d){
        int[] miss = new int[d];
        int k = 0;
        for(int i = 1; i <= n + d; i++){
            boolean found = false;
            for(int j = 0; j < sorted.length; j++) {
                if (sorted[j] == i) {
                    found = true;
                    break;
                }
            }
            if(!found){
                miss[k] = i;
                k++;
            }

        }
        return miss;
    }

    // prints the whole tree
    public void print() {  
        print(root, 0);
    }
    
    // recursively prints the items of the tree, indenting by depth
    public void print(Item p, int depth) {
        if (p == null) {
            return;
        }
        for ( int k=0; k < depth; k++) {
            System.out.print("   ");
        }
        System.out.println(p);
        print(p.left, depth+1);
        print(p.right, depth+1);
    }

    // print sorted values
    public void printsorted(Item p, int depth){
        if (p == null) {
            return;
        }

        printsorted(p.left, depth+1);
        System.out.print(p.value + " ");
        sorted[count] = p.value;
        y[count] = -depth * 2;
        count++;
        total_depth += depth;
        printsorted(p.right, depth+1);

    }

    // get the total_depth for average depth
    public void getTotal_depth(Item p, int depth){
        if ( p == null ) {
            return;
        }

        getTotal_depth(p.left, depth+1);
        sorted[count] = p.value;
        count++;
        total_depth += depth;
        getTotal_depth(p.right, depth+1);

    }

    // print edges in latex
    public void printEdge(Item p){
        if(p == null) return;

        if(p.left != null)
            System.out.println("\\draw [->, thick] ("
                               + Integer.toString(p.value) + ") to ("
                               + Integer.toString(p.left.value) + ");");

         if(p.right != null)
             System.out.println("\\draw [->, thick] ("
                                + Integer.toString(p.value) + ") to ("
                                + Integer.toString(p.right.value) + ");");

         printEdge(p.left);
         printEdge(p.right);
    }

    // print latex header
    public void outheader(){
        System.out.println("\\documentclass[a4paper,11pt]{article} %%%%%%%%%%%% start of LaTeX file");
        System.out.println("\\usepackage{mathpazo}");
        System.out.println("\\usepackage{tikz}");
        System.out.println("\\usetikzlibrary{shapes}");
        System.out.println("\\oddsidemargin -0.54cm");
        System.out.println("\\textwidth 17.0cm");
        System.out.println("\\textheight 24cm");
        System.out.println("\\topmargin -1.3cm");
        System.out.println("\\parindent 0pt");
        System.out.println("\\parskip 1ex");
        System.out.println("\\pagestyle{empty}");
        System.out.println("\\begin{document} %%%%%%%%%%%% end of LaTeX preamble, start of text");
        System.out.println("\\medskip\\hrule\\medskip");
    }

    // print latex footer
    public void outfooter(){
        System.out.println("\\medskip\\hrule\\medskip");
        System.out.println("\\end{document}");
    }

    // find the suitable grid
    public void outgrid(){
        grid = new int[4];
        for(int i = 0; i < x.length; i++){
            if( x[i] > grid[0] ) grid[0] = x[i];
            if( x[i] < grid[2] ) grid[2] = x[i];
            if( y[i] > grid[1] ) grid[1] = y[i];
            if( y[i] < grid[3] ) grid[3] = y[i];
        }
    }

    // print the information of the array
    public void outinfo(){

        for(int i = 0; i < unsorted.length; i++) System.out.print(unsorted[i] + " ");
        System.out.println("are inserted");
        System.out.println();
        System.out.print("in sorted order: ");
        printsorted(root,0);
        System.out.println();
        System.out.println();
        double average_depth = ( total_depth + 0.0 ) / sorted.length;
        System.out.printf("average depth: %.3f, ", average_depth);
        System.out.println("size " + unsorted.length);
        System.out.println();
        System.out.println();

    }

    // print the graph in latex
    public void outgraph(){

        double a = 17.0 / unsorted.length;
        System.out.print("\\begin{tikzpicture}");
        if( a < 0.3 ) System.out.printf("[scale=0.3]");
        else if( a > 0.6 ) System.out.printf("[scale=0.6]");
        else System.out.printf("[scale=%.3f]", a);

        outgrid();
        String min = "(" + Integer.toString(grid[2]) + "," + Integer.toString(grid[3]) + ")";
        String max = "(" + Integer.toString(grid[0]) + "," + Integer.toString(grid[1]) + ")";
        System.out.println("\\draw [help lines, color=green] " + min + " grid " + max + " ;");

        for(int i =0; i < y.length; i++){
            String corrdinate = "(" + Integer.toString(x[i]) + "," + Integer.toString(y[i]) + ")";
            String label = Integer.toString(sorted[i]);
            System.out.println("\\draw [thick] " + corrdinate
                               +" node[draw, rounded rectangle] (" + label +") {" + label + "};");
        }

        printEdge(root);
        System.out.println("\\end{tikzpicture}");

    }

    // clear the tree
    public void clear(){
        count = 0;
        total_depth = 0;
        root = null;
    }

    // setup the variables for a tree
    public void setup(int n){    // without unsorted array
        sorted = new int[n];
        y = new int[n];
        x = new int[n];
        for(int i = 0; i < x.length; i++)
            x[i] = i;
    }

    public static void main (String[] args) {

        // random test for (e)
        if( Integer.parseInt(args[0]) == -1 & args.length == 1) {
            Tree tree = new Tree();
            tree.randomtest();
            tree.clear();
            return;
        }

        // random insert for (e)
        else if(args.length == 1 & Integer.parseInt(args[0]) > 0){
            int n = Integer.parseInt(args[0]);
            Tree tree = new Tree();
            tree.randominsert(n);
            tree.clear();
            return;
        }

        // random insert and replace for (f)
        else if( args.length == 2 ){
            int n = Integer.parseInt(args[0]);
            int d = Integer.parseInt(args[1]);
            if( d > n) d = n;
            Tree tree = new Tree();
            tree.randomreplace(n,d);
            tree.clear();
            return;
        }

        // random insert and repeat replace for (g)
        else if(args.length == 3 ){
            int n = Integer.parseInt(args[0]);
            int d = Integer.parseInt(args[1]);
            int r = Integer.parseInt(args[2]);
            if( d > n) d = n;
            Tree tree = new Tree();
            tree.randomrepeats(n,d,r);
            tree.clear();
            return;

        }

        // normal insert with known numbers for (c) and (d)
        else if( args.length > 3 ){
            Tree tree = new Tree();
            tree.normal(args);
            tree.clear();
            return;
        }

        else return;






    }
    
}


package Coursework;

/*
candidate number: 36609
Graph.java
for question (a) and (b)
 */

public class Graph {

    private Node top = null; // top item
    private Node bottom = null; // bottom item
    private int maxcoordinate = 100; // maximum coordinate

    public int count = 0; // count the number of nodes
    public String[] edge; // the edge info in latex
    public int[] grid = new int[4];// max_x = grid[0]; max_y = grid[1]; min_x = grid[2]; min_y = grid[3]

    public double textwidth = 17.0;
    public double oddsidemargin = -0.54;

    // Item for Graph
    private class Node { // a private class of node
        private String label;
        private int x;
        private int y;
        private boolean present;
        private Node next;

        private Node (int x, int y, int count, Node Node) {
            label = count + "";
            this.x = x;
            this.y = y;
            if( Math.abs(x) <= maxcoordinate & Math.abs(y) <= maxcoordinate ) present = true;
            else present = false;
            next = Node;
        }
    }

    // create the graph
    public void addNode( int x, int y, int count ){
        Node p = new Node(x, y, count, null);

        if( top == null ) top = p;
        else bottom.next = p;

        bottom = p;
    }

    // the edge info in latex
    public String[] addEdge(){
        String[] edge = new String[count];
        int i = -1;
        for(Node node = top; node.next!= null; node = node.next ){
            i++;
            if( node.present & node.next.present)
                edge[i] = "\\draw [->, thick] (" + node.label + ") to (" + node.next.label + ");";
        }
        return edge;
    }

    // Output the header of the LaTeX file
    public void outheader(){
        System.out.println("\\documentclass[a4paper,11pt]{article} %%%%%%%%%%%% start of LaTeX file");
        System.out.println("\\usepackage{mathpazo}");
        System.out.println("\\usepackage{tikz}");
        System.out.println("\\usetikzlibrary{shapes}");
        System.out.println("\\oddsidemargin " + oddsidemargin + "cm");
        System.out.println("\\textwidth " + textwidth + "cm");
        System.out.println("\\textheight 24cm");
        System.out.println("\\topmargin -1.3cm");
        System.out.println("\\parindent 0pt");
        System.out.println("\\parskip 1ex");
        System.out.println("\\pagestyle{empty}");
        System.out.println("\\begin{document} %%%%%%%%%%%% end of LaTeX preamble, start of text");
        System.out.println("\\medskip\\hrule\\medskip");
    }

    // Output the footer of the LaTeX file
    public void outfooter(){
        System.out.println("\\medskip\\hrule\\medskip");
        System.out.println("\\end{document}");
    }

    // calculate the size of the grid
    public void outgrid(){
        grid[0] = -maxcoordinate;
        grid[2] = maxcoordinate;
        grid[1] = -maxcoordinate;
        grid[3] = maxcoordinate;

        for(Node node = top; node!= null; node = node.next ){
            if(node.present){
                if(node.x > grid[0]) grid[0] = node.x;
                if(node.x < grid[2]) grid[2] = node.x;
                if(node.y > grid[1]) grid[1] = node.y;
                if(node.y < grid[3]) grid[3] = node.y;
            }
        }
    }

    // print graph in latex with suitable scale
    public void outgraph(){
        int grid_number = grid[0] - grid[2];
        double a = 17.0 / grid_number;
        System.out.print("\\begin{tikzpicture}");
        if( a < 0.3 ) System.out.printf("[scale=0.3]");
        else if( a > 0.6 ) System.out.printf("[scale=0.6]");
        else System.out.printf("[scale=%.3f]", a);

        System.out.println();

        String min = "(" + Integer.toString(grid[2]) + "," + Integer.toString(grid[3]) + ")";
        String max = "(" + Integer.toString(grid[0]) + "," + Integer.toString(grid[1]) + ")";
        System.out.println("\\draw [help lines, color=green] " + min + " grid " + max + " ;");

        // print the nodes
        // need to update the label of the node firstly
        String[] corrdinate = new String[count];
        String[] print_label = new String[count];
        int j = -1;
        for(Node node = top; node!= null; node = node.next ){
            j++;
            if(node.present){
                corrdinate[j] = "(" + Integer.toString(node.x) + "," + Integer.toString(node.y) + ")";
                print_label[j] = node.label;
            }
            else{
                corrdinate[j] = "";
                print_label[j] = "";
            }
        }

        for(int i = 0; i < count; i++)
            for(int k = i + 1; k < count; k++)
                if(corrdinate[k].equals(corrdinate[i]))
                    print_label[i] = print_label[k];

        // print the nodes
        j = -1;
        for(Node node = top; node!= null; node = node.next ){
            j++;
            if(node.present){
                if( print_label[j].equals(Integer.toString(j+1)) )
                    System.out.println("\\draw [thick] " + corrdinate[j]
                                       + " node[draw, rounded rectangle] (" + node.label
                                       + ") {" + print_label[j] + "};");
                else
                    System.out.println("\\draw [thick] " + corrdinate[j]
                                       + " node[rounded rectangle] (" + node.label + ") {"
                                       + print_label[j] + "};");
            }
            else{ // the nodes out of scale
                if( node.x > maxcoordinate )
                    System.out.println("% coordinate "
                                       + Integer.toString(node.x) + " in node ("
                                       + Integer.toString(node.x) + ","
                                       + Integer.toString(node.y) + ") too large");
                else if( node.y > maxcoordinate )
                    System.out.println("% coordinate "
                                       + Integer.toString(node.y) + " in node ("
                                       + Integer.toString(node.x) + ","
                                       + Integer.toString(node.y) + ") too large");
                else if( node.x < -maxcoordinate )
                    System.out.println("% coordinate "
                                       + Integer.toString(node.x) + " in node ("
                                       + Integer.toString(node.x) + ","
                                       + Integer.toString(node.y) + ") too small");
                else if( node.y < -maxcoordinate )
                    System.out.println("% coordinate "
                                       + Integer.toString(node.y) + " in node ("
                                       + Integer.toString(node.x) + ","
                                       + Integer.toString(node.y) + ") too small");
            }
        }

        // print the edge
        edge = addEdge();
        for( int i = 0; i < edge.length; i++ )
            if( edge[i] != null ) System.out.println(edge[i]);

        System.out.println("\\end{tikzpicture}");
    }

    // Empty the node and edge list
    public void clear(){
        top = null;
        bottom = null;
        count = 0;
        edge = null;
        grid = null;
    }

    public static void main (String[] args){
        if (args.length <= 0 || args.length%2 != 0) return;
        Graph graph = new Graph();
        int[] coordinate = new int[args.length];
        for(int i = 0; i < args.length; i++)
            coordinate[i] = Integer.parseInt(args[i]);


        // add the nodes
        for(int i = 0; i < coordinate.length - 1; i++){
            graph.count ++;
            graph.addNode( coordinate[i], coordinate[i+1] , graph.count);
            i++;
        }

        // print the pdf
        graph.outheader();

        graph.outgrid();

        graph.outgraph();

        graph.outfooter();

        graph.clear();


    }

}

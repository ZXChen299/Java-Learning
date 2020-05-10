package Seminar8;

public class HuffmanTree // Huffmann Tree
{
    public HuffmanNode root; // the root of the Huffman Tree

    public HuffmanTree(FreqItem start) { // create the tree
        makeHeap(start);
        makeTree();
    }

    private int numchars() { // number of different characters
        return root.label.length();
    }

    public void printbitsize() { // inform about the bit size of the code
        System.out.print("Total string length: "+root.count);
        System.out.println(" with " + numchars() + " different characters.");
        // calculate bit size for standard encoding
        int bits = 1, base = 2;
        while ( base < numchars() ) {
            bits++; base *=2 ;
        }
        System.out.print("Normal  encoding requires " + bits);
        System.out.print(" bits per character, ");
        System.out.println(bits * root.count + " in total.");
        System.out.print("Huffman encoding requires ");
        System.out.print(recursivebitsize (root, 0));
        System.out.println(" bits in total.");
        return;
    }

    private int recursivebitsize (HuffmanNode node, int level) {
    // recursively compute size for characters in subtree
         if (node.left == null && node.right == null) // leaf, done
            return level * node.count ;
         return recursivebitsize (node.left, level+1) +
             recursivebitsize (node.right, level+1);
    }

    private int size; // number of elements in the heap
    private HuffmanNode[] A;  // array for storing the heap elements
    // in A[1] ... A[size]
    // the children of heap element A[i] are A[2*i] and A[2*i+1]
    // heap property: A[i].count <= A[2*i].count
    //                A[i].count <= A[2*i+1].count

    private boolean notempty() { // the heap is not empty
        return size>0; 
    }

    private boolean notsingle() { // the heap has more than one element
        return size>1; 
    }

    private HuffmanNode extract_min() {
        // extract the smallest element from the heap
        HuffmanNode min = A[1];  // this is the smallest element
        A[1] = A[size];
        size-- ;
        heapify(1);
        return min;
    }

    private HuffmanNode get_min() {
        // give the smallest element from the heap
        return A[1];  
    } 

    private void replace_min(HuffmanNode node) {
        // replace the smallest element of the heap and put
        // it in the right place
        A[1] = node;
        heapify(1);
    } 

    private void makeHeap(FreqItem start) {
        size = 0;
        // first, count the items
        for (FreqItem p = start; p!=null; p=p.next)
            size ++;
        A = new HuffmanNode[size+1]; // stored in A[1] ... A[size]

        int i=1;
        for (FreqItem p = start; p!=null; p=p.next) {
            A[i] = new HuffmanNode(""+p.c, p.count, null, null);
            i++;
        }

        for (i = size/2; i>=1; i--) // create the heap
            heapify (i);
        return;
    }

    // recursive version of heapify, at position i of the heap
    // heap means:  children's values >= node value
    // children of  i  are  2i  and  2i+1  if  <= size
    private void heapify(int i)
    {
        if (2*i <= size) // there is a left child, otherwise done
        {
            int c = 2*i;  // left child
            HuffmanNode min = A[c]; // min = the currently smallest child
            if (2*i+1 <= size)   // there is a right child
            {
                if ( A[2*i+1].count < min.count) // the right child is smaller
                {
                    c = 2*i+1 ; min = A[c];
                }
            }
            if (min.count < A[i].count) // need to modify the heap, o/w done
            {
                A[c] = A[i]; // move A[i] downwards
                A[i] = min;  // and A[c] into A
                heapify(c);
            }
        }
    }

    private void makeTree() {
        while (notsingle()) {
            HuffmanNode p = extract_min();
            HuffmanNode q = get_min();
            HuffmanNode parent = new HuffmanNode (
                p.label + q.label, p.count + q.count, p, q);
            replace_min(parent);
        }
        root = A[1];
    }

    private void recprint(boolean fulltree, 
        HuffmanNode node, int level, String codeword) {
    // recursively print tree at current level
        if (node != null) { // otherwise done
            if (fulltree) // indent 3 spaces per level
                for (int i=0; i<level; i++)
                    System.out.print("   ");
            if (fulltree || (node.left == null && node.right == null)) { 
                System.out.print(codeword + " = ");
                System.out.println("\""+ node.label + "\"(" + node.count +")");
            }
            recprint(fulltree, node.left, level+1, codeword+"0");
            recprint(fulltree, node.right, level+1, codeword+"1");
        }
    } 

    private void print(boolean fulltree) {
        recprint(fulltree, root, 0, "");
    }

    public static void main (String[] args)
    {
        if (args.length == 0) {
            System.out.println ("Huffman code generator. Please input some text.");
            return;
        }
        FreqCount f = new FreqCount(args) ;
        HuffmanTree tree = new HuffmanTree(f.start) ;
        tree.print(true);
        System.out.println("----------- codewords -------------");
        tree.print(false);
        tree.printbitsize();
    }

}

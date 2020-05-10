package Ex8;
/*
Zixuan Chen
201917656
 */
public class HuffmanNode // Node for Huffman tree
{
    public String label; // a label with all the characters in the subtree
      // of the Huffman tree with this as root, contains
      // more than one character if not a leaf
    public int count;  // frequency count
    public HuffmanNode left, right; // left and right child; null if leaf

    public HuffmanNode(String label, int count, HuffmanNode left, HuffmanNode right ){
        this.label = label;
        this.count = count;
        this.left = left;
        this.right = right;
    }

    public String toString(){ return "'" + label + "'(" + count + ")"; }

}

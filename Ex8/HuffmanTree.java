package Ex8;
/*
Zixuan Chen
201971656
 */

class HuffmanTree // Huffman Tree
{
    private int size = 0;
    private FreqItem[] A; // a array to create Heap
    private HuffmanNode[] Q; // a array to create Huffman tree
    private int count = 0; // count the number of characters
    private int wordCount = 0; // count the number of words
    private int bits = 0; // count the number pf bits in Huffman method
    public HuffmanNode root;

    public void makeHeap(FreqItem start){

        for(FreqItem item = start; item!= null; item =item.next){ size++; }
        A = new FreqItem[size+1]; // create a new FreqItem array, make it like pointers
        int position = 1;
        for(FreqItem item = start; item != null;item=item.next){
            A[position] = item;
            position++;
        }
        for(int i = size/2; i >= 1; i--){ heapify(i); }// create the heap
    }

    private void heapify(int i){ // the standard heapify method
        if (2 * i <= size){
            int c = 2 * i;
            FreqItem min = A[c];
            if(2*i+1 <= size){
                if(A[2*i+1].count < min.count){
                    c = 2*i+1;
                    min = A[c];
                }
            }
            if( min.count < A[i].count ){
                A[c] = A[i];
                A[i] = min;
                heapify(c);
            }
        }
    }

    public boolean notempty(){
        return size > 0;
    }

    public FreqItem extract_min(){
        FreqItem min = A[1];
        A[1] = A[size];
        size--;
        heapify(1);
        return min;
    }

    public void makeTree(){
        Q = new HuffmanNode[size+1]; // a new HuffmanNode array, similar to A
        int position = 1;
        while(notempty()){
            FreqItem temp = extract_min(); // make Q sorted by frequency
            Q[position] = new HuffmanNode(temp.c+"", temp.count,null,null);
            position++;
        }
        root = Huffman(Q);
    }

    private HuffmanNode Huffman(HuffmanNode[] Q){ // create the huffman tree
        HuffmanNode[] T = Q;
        for(int i = 1; i < T.length-1; i++){
            HuffmanNode x = extractMin(T);
            HuffmanNode y = extractMin(T);
            HuffmanNode z = new HuffmanNode(x.label+y.label,x.count+y.count,null,null);
            z.left = x;
            z.right = y;
            insert(T,z); // insert z to T
        }
        return extractMin(T);
    }

    private void insert(HuffmanNode[] T, HuffmanNode z){
        for(int i = 1; i < T.length; i++){
            if(T[i].count == 0){
                T[i] = z;
                break;
            }
        }
    }

    private HuffmanNode extractMin( HuffmanNode[] T){ // obtain the smallest node and delete it
        HuffmanNode temp = null;
        boolean setMin = false;
        for(int i = 1; i <T.length; i++){
            if( T[i].count != 0 && !setMin ){
                temp = T[i];
                setMin = true;
            }
            if( temp != null && temp.count > T[i].count && T[i].count > 0 && setMin){
                temp = T[i];
            }
        }
        HuffmanNode min = new HuffmanNode(temp.label,temp.count,temp.left,temp.right);
        for(int i = 1; i < T.length; i++){
            if(T[i] == temp){
                T[i].count = 0; // I define that if T[i].count == 0, it means it's deleted
            }
        }
        return min;

    }

    public HuffmanTree(FreqItem start) { // create the tree
        makeHeap(start);
        makeTree();
    }

    public void recursiveprint (HuffmanNode node, int level, String bit) { // print the whole tree
        if (node != null) {
            for(int i = 1; i < level; i++) System.out.print("  ");
            System.out.print(bit);
            System.out.println( " = " + node);
            recursiveprint(node.left, level+1,bit+"0");
            recursiveprint(node.right, level+1,bit+"1");
        }
    }

    public void printbitsize(HuffmanNode node, int level, String bit){
        if (node != null) {
            if( node.right == null && node.left == null ){
                count += node.count;
                wordCount++;
                int s = bit.length();
                bits += s * node.count;
                System.out.print(bit);
                System.out.println( " = " + node);
            }
            printbitsize(node.left, level+1,bit+"0");
            printbitsize(node.right, level+1,bit+"1");
        }
    }

    public void printInfo(){
        System.out.println("Total string length: " + count + " with " +
                           wordCount + " different characters.");
        System.out.println("Normal  encoding requires " + wordCount/2 +
                           " bites per character, " + count/2*wordCount +
                           " in total.");
        System.out.println("Huffman encoding requires " + bits + " in total.");
    }

    public static void main(String[] args){
        FreqCount list = new FreqCount(args);
        HuffmanTree tree = new HuffmanTree( list.start );
        tree.recursiveprint(tree.root,1,"");
        System.out.println("----------- codewords -----------");
        tree.printbitsize(tree.root,1,"");
        tree.printInfo();
    }

}

/* sample output
java HuffmanTree test test test case
 = 'esac t'(19)
   0 = 'es'(8)
      00 = 'e'(4)
      01 = 's'(4)
   1 = 'ac t'(11)
      10 = 'ac '(5)
         100 = 'ac'(2)
            1000 = 'a'(1)
            1001 = 'c'(1)
         101 = ' '(3)
      11 = 't'(6)
----------- codewords -------------
00 = 'e'(4)
01 = 's'(4)
1000 = 'a'(1)
1001 = 'c'(1)
101 = ' '(3)
11 = 't'(6)
Total string length: 19 with 6 different characters.
Normal  encoding requires 3 bits per character, 57 in total.
Huffman encoding requires 45 bits in total.
*/

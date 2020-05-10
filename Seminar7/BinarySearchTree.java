package Seminar7;
// Bernhard von Stengel, Tugkan Batu. Updated by Julia Boettcher, 2013, 2016.
//
// Implementation of a binary search tree
//
// java BinarySearchTree x1 x2 x3 ....
// interprets the integer command line arguments as follows:
// 0: print  >0:  insert  <0:  delete 

public class BinarySearchTree { 
    private Item root = null; // the root of the tree

    private class Item {  // Item for binary search tree
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
    
    // returns the minimum, which is in the left-most item;
    // returns -1 if the tree is empty
    public int min() { 
        Item p = root;
        if (p==null) {
            return -1;
        }
        while (p.left != null) {
            p = p.left;
        }
        return p.value;
    }
    
    // returns the maximum, which is in the right-most item;
    // returns -1 if the tree is empty
    public int max() { 
        Item p = root;
        if (p==null) {
            return -1;
        }
        while ( p.right != null) {
            p = p.right;
        }
        return p.value;
    }
    
    // finds the value x in the tree, returns item containing it;
    // if not present, returns null
    public Item find(int x) {
        Item p = root;
        while (p != null && p.value != x) {
            if (x < p.value) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return p;
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
    
    // finds an item with value x and deletes it
    public void delete(int x) {
        Item p = find(x);
        if (p == null) {  // item was not found, end
            return;
        } else if (p.right == null) { // no right child, easy to delete
            deleteWithoutRight(p);
        } else if (p.left == null) {  // no left child, easy to delete
            deleteWithoutLeft(p);
        } else { 
            // otherwise find a smallest item q in right subtree of p
            // replace p by q and delete q in the right subtree
            Item q = p.right;
            while (q.left != null)
                q = q.left;
            p.value = q.value;
            deleteWithoutLeft(q); // the smallest element in a subtree has
                                  // no left child
        }
    }
    
    // deletes an item p which has no left child
    private void deleteWithoutLeft(Item p) {
        Item q = p.parent;
        if (q == null) {          // if p is the root
            root = p.right;          // delete p
            if (root != null) {      // tree did not get empty
                root.parent = null;
            }
        } else if (q.left == p) { // p is left child of q
            q.left = p.right;        // delete p
            if (q.left != null) {    // update left subtree of q
                q.left.parent = q;
            }
        } else {                  // p is right child of q
            q.right = p.right;       // delete p
            if (q.right != null) {   // update right subtree of q
                q.right.parent = q;
            }
        }
    }

    // deletes an item p which has no right child
    private void deleteWithoutRight(Item p) {
        Item q = p.parent;
        if (q == null) {          // if p is the root
            root = p.left;          // delete p
            if (root != null) {     // tree did not get empty
                root.parent = null;
            }
        } else if (q.left == p) { // p is left child of q
            q.left = p.left;         // delete p
            if (q.left != null) {    // update left subtree of q
                q.left.parent = q;
            }
        } else {                  // p is right child of q
            q.right = p.left;        // delete p
            if (q.right != null) {   // update right subtree of q
                q.right.parent = q;
            }
        }
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
    
    // for test purposes only
    // interprets the integer command line arguments as follows:
    // 0: print  >0:  insert  <0:  delete 
    public static void main (String[] args) { 
        if (args.length<=0) return;
        BinarySearchTree tree = new BinarySearchTree();
        int x=0;
        for (int i=0; i < args.length; i++) {
            x = Integer.parseInt(args[i]);
            if (x == 0) {     // print
                tree.print();
                System.out.println();
            } else if (x>0) { // insert; only if x not present yet
                tree.insert(x);
            } else {          // if x < 0, delete
                tree.delete(-x);
            }
        }
        System.out.println("Minimum: "+ tree.min());
        System.out.println("Maximum: "+ tree.max());
    }
    
}

/* output:

java BinarySearchTree 5 7 8 2 1 9 4 6 3 0 -1 0 -5 0 -3 0 -6 0 -9
5, left: 2, right: 7
   2, left: 1, right: 4
      1, left: null, right: null
      4, left: 3, right: null
         3, left: null, right: null
   7, left: 6, right: 8
      6, left: null, right: null
      8, left: null, right: 9
         9, left: null, right: null

5, left: 2, right: 7
   2, left: null, right: 4
      4, left: 3, right: null
         3, left: null, right: null
   7, left: 6, right: 8
      6, left: null, right: null
      8, left: null, right: 9
         9, left: null, right: null

6, left: 2, right: 7
   2, left: null, right: 4
      4, left: 3, right: null
         3, left: null, right: null
   7, left: null, right: 8
      8, left: null, right: 9
         9, left: null, right: null

6, left: 2, right: 7
   2, left: null, right: 4
      4, left: null, right: null
   7, left: null, right: 8
      8, left: null, right: 9
         9, left: null, right: null

7, left: 2, right: 8
   2, left: null, right: 4
      4, left: null, right: null
   8, left: null, right: 9
      9, left: null, right: null

Minimum: 2
Maximum: 8
*/

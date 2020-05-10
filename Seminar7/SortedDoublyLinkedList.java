package Seminar7;
// Bernhard von Stengel 2019
// A sorted doubly linked list for integers
// Usage: java SortedDoublyLinkedList x_1 x_2 x_3 ...
//      If x_i > 0, insert x_i into the list
//      If x_i < 0, delete all instances of -x_i
//      If x_i == 0, print out the queue in order and in reverse order

public class SortedDoublyLinkedList {

    private Item start, end;
    
    // Exercise 7.2(a)
    private class Item {
        int value;
        Item next, prev;
        
        private Item(int x, Item n, Item p) {
            value = x;
            next = n;
            prev = p;
        }
    }

    // Exercise 7.2(b)
    public void insert(int x) {
        // Inserts x into list so that list stays sorted.
        if (start == null) { // empty list
            start = new Item(x, null, null);
            end = start;
            return;
        } // list not empty
        Item p = start;
        while (p!= null && p.value < x) {
            p = p.next;
        } // either p==null and x should be inserted at end,
          // or p is first item with p.value >= x
        if (p==null) { // insert x at end
            end.next = new Item(x,null,end);
            end = end.next;
            return;
        } // insert x before p
        Item item = new Item(x, p, p.prev);
        if (p == start) {
            start = item;
        } else {
            p.prev.next = item;
        }
        p.prev = item;
    }


    // Exercise 7.2(c)
    public void delete(int x) {
        // deletes all items with value x.
        Item p = start;
        while (p!=null && p.value < x) {
            p = p.next;
        } // either p==null and there is no x in list 
          // or p is first entry with p.value >= x
        if (p==null || p.value > x) { // nothing to delete
            return;
        } // p is first item to delete
        Item q = p;
        while (q!=null && q.value == x) {
            q = q.next;
        } // either q==null or q is first entry with q.value > x
        if (q==null) { // delete from p up to end
            if (p==start) { // have to delete everything
                start = null;
                end = null;
            } else {
                end = p.prev;
                end.next = null;
            }
            return;
        } // delete from p to before q
        if (p == start) {
            start = q;
            start.prev = null;
            return;
        }
        p.prev.next = q;
        q.prev = p.prev;
    }

    // Exercise 7.2(d)
    public String toString() {
        // returns a String representation of list.
        if (start == null) {
            return "null";
        }
        String s = "null <- ";
        for (Item p = start; p != end; p = p.next) {
            s += p.value + " <-> ";
        }
        s += end.value + " -> null";
        return s;
    }

    // Exercise 7.2(d)
    public String reverseString() {
        // returns a String representation of list in reverse order.
        if (start == null) {
            return "null";
        }
        String s = "null <- ";
        for (Item p = end; p != start; p = p.prev) {
            s += p.value + " <-> ";
        }
        s += start.value + " -> null";
        return s;
    }

    public static void main(String[] args) {
        SortedDoublyLinkedList list = new SortedDoublyLinkedList();
        for (int i = 0; i < args.length; i++) {
            int x = Integer.parseInt(args[i]);
            if (x>0) {
                //System.out.println("inserting " + x);
                list.insert(x);
            } else if (x < 0) {
                //System.out.println("deleting " + -x);
                list.delete(-x);
            } else if (x == 0) {
                System.out.println(list.toString());
                System.out.println(list.reverseString());
                System.out.println();                
            }
        }
    }
}

/*
java SortedDoublyLinkedList 1 5 1 3 2 6 2 2 5 0 -2 0 -7 0 -1 0 -6 -5 0 -3 0
null <- 1 <-> 1 <-> 2 <-> 2 <-> 2 <-> 3 <-> 5 <-> 5 <-> 6 -> null
null <- 6 <-> 5 <-> 5 <-> 3 <-> 2 <-> 2 <-> 2 <-> 1 <-> 1 -> null

null <- 1 <-> 1 <-> 3 <-> 5 <-> 5 <-> 6 -> null
null <- 6 <-> 5 <-> 5 <-> 3 <-> 1 <-> 1 -> null

null <- 1 <-> 1 <-> 3 <-> 5 <-> 5 <-> 6 -> null
null <- 6 <-> 5 <-> 5 <-> 3 <-> 1 <-> 1 -> null

null <- 3 <-> 5 <-> 5 <-> 6 -> null
null <- 6 <-> 5 <-> 5 <-> 3 -> null

null <- 3 -> null
null <- 3 -> null

null
null
*/

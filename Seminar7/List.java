package Seminar7;
// Bernhard von Stengel, Tugkan Batu. Updated by Julia Boettcher, 2013, 2016.
//
// Implementation of a singly linked list for integer values with
// insertion, deletion and find
//
// test with: java List

public class List {   // singly linked list

    public Item start = null;  // start of the list

    private class Item { // items for a linked list

        private int  value;
        private Item next;

        private Item (int x, Item item) {
            value = x;
            next = item;
        }
    
        public String toString() {
            return Integer.toString(value);
        }
    }

    // insert a new element after item
    // if item==null, insert at front of list
    public void insertAfter(Item item, int x) {
        if (item == null) {
            start = new Item(x, start);
        } else {
            item.next = new Item(x, item.next);
        }
    }

    // delete the element following item
    // if  item==null, delete first element of list
    // item must not be the last list element, and list must not be empty
    public void deleteAfter(Item item) {
        if (item == null) {
            start = start.next;
        } else {
            item.next = item.next.next;
        }
    } 

    // find the value x in the list, return item containing it
    // if not present, return null
    public Item findValue(int x) {
        Item p = start;
        while (p != null && p.value != x) {
            p = p.next;
        }
        return p;
    }

    // find a value in the list and delete its first appearance
    // do nothing if not found
    public void deleteValue (int x) {
        Item previous = null;
        for (Item item = start; item!= null; item = item.next) {
            if (item.value == x) {
                deleteAfter(previous);
                break;
            } else {
                previous = item; 
            }
        }
    } 

    // string representation of the list
    public String toString() {
        String s = "";
        for (Item p=start; p!=null; p=p.next) {
            s += p + " -> ";
        }
        s += "null";
        return s;
    }

    // for test purposes only
    public static void main (String[] args) {
        List list = new List();  // create a new empty list
        list.insertAfter(null, 7); // insert 7 at the beginning 
        list.insertAfter(null, 4); // insert 4 at the beginning 
        list.insertAfter(null, 5); // insert 5 at the beginning 
        list.insertAfter(null, 7); // insert 7 at the beginning 
        list.insertAfter(null, 9); // insert 9 at the beginning 
        Item item;

        System.out.println("The initial list: ");
        System.out.println(list);
     
        item = list.findValue(7);
        System.out.print("The list ");
        if (item != null) {
            System.out.print("contains ");
        } else {
            System.out.print("doesn not contain ");
        }
        System.out.println("an element with value 7.");
                
        list.deleteAfter(item);
        System.out.println("Deleting the item after the (first) item with value 7. The resulting list:");
        System.out.println(list);
        
        list.insertAfter(item, 15);
        System.out.println("Inserting 15 after the (first) item with value 7. The resulting list:");
        System.out.println(list);
        
        list.deleteValue(7);
        System.out.println("Deleting the value 7. The resulting list:");
        System.out.println(list);
        
        System.out.println("Deleting the value 7 again.The resulting list:");
        list.deleteValue(7);
        System.out.println(list);        
    } 
}

/* output generated:
The initial list: 
9 -> 7 -> 5 -> 4 -> 7 -> null
The list contains an element with value 7.
Deleting the item after the (first) item with value 7. The resulting list:
9 -> 7 -> 4 -> 7 -> null
Inserting 15 after the (first) item with value 7. The resulting list:
9 -> 7 -> 15 -> 4 -> 7 -> null
Deleting the value 7. The resulting list:
9 -> 15 -> 4 -> 7 -> null
Deleting the value 7 again.The resulting list:
9 -> 15 -> 4 -> null
*/

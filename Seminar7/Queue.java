package Seminar7;
// Konrad Swanepoel 2018
// Implements a queue of type int
// Usage: java Queue x_1 x_2 x_3 ...
//      if x_i > 0, enqueue x_i
//      if x_i < 0, dequeue an int
//      if x_i == 0, print out queue

class Queue {
    
    // Exercise 7.1(a)
    private Item head, tail;

    // Exercise 7.1(a)
    private class Item {
        int value;
        Item next;
    }

    // Exercise 7.1(b)
    public void enqueue(int x) {
        Item item = new Item();
        item.value = x;
        if (head == null ) { // Queue empty
            head = item;
            tail = item;
        } else {
            tail.next = item;
            tail = tail.next;
        }
    }

    // Exercise 7.1(c)
    public int dequeue() {
        int x = head.value;
        head = head.next;
        return x;
    }

    // Exercise 7.1(d)
    public String toString() {
        String s = "";
        for (Item p = head; p != null; p = p.next) {
            s = s + p.value + " -> ";
        }
        return s + "null";
    }

    public static void main(String[] args) {
        Queue q = new Queue();
        for (int i = 0; i < args.length; i++) {
            int x = Integer.parseInt(args[i]);
            if (x > 0) {
                System.out.println("enqueue " + x);
                q.enqueue(x);
            } else if (x < 0) {
                System.out.println("dequeue " + q.dequeue());
            } else { // x == 0
                System.out.println(q);
            }
        }
    }
}

/* Sample output:

java Queue 1 0 2 0 3 0 4 0 -1 0 -1 0 5 0
enqueue 1
1 -> null
enqueue 2
1 -> 2 -> null
enqueue 3
1 -> 2 -> 3 -> null
enqueue 4
1 -> 2 -> 3 -> 4 -> null
dequeue 1
2 -> 3 -> 4 -> null
dequeue 2
3 -> 4 -> null
enqueue 5
3 -> 4 -> 5 -> null

*/

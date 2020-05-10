package Seminar7;
// Implementation of a stack for values of type char.
//
// java Stack x1 x2 x3 x4 ...
// interprets the integer command line arguments as follows:
// 0: print  >0: push xi  <0: pop 

public class Stack {
    
    private Item top = null;  // top of the stack

    private class Item {
        private char  value;
        private Item next;
    }

    // takes the top item off the stack and returns its value
    // if stack is empty, returns -1
    public char pop() {
        char x = top.value;
        top = top.next;
        return x;
    }
    
    // creates a new item with value x and puts it on top of the stack
    public void push(char x) {
        Item p = new Item();
        p.value = x;
        p.next = top;
        top = p;
    }
    
	public boolean isEmpty() {
		return top==null;
	}
	
    // string representation of the stack
    public String toString() {
        String s = "";
        for (Item p=top; p!=null; p=p.next) {
            s += p.value + " -> ";
        }
        s += "null";
        return s;
    }
    
}

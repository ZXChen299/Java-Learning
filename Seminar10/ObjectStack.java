package Seminar10;
// Julia Boettcher 2013; 2014, 2015, 2016
// Updated 2018 Konrad Swanepoel
// Implementation of a stack for any type of values:
// all values are of type Object.
//
// test with: java ObjectStack

public class ObjectStack {
    
    private Item top = null;  // top of the stack

    private class Item {
        private Object value;
        private Item next;
    }

    // takes the top item off the stack and returns its value
    // if stack is empty, returns null
    public Object pop() {
        if (top == null) {
            return null;
        }
        Object x = top.value;
        top = top.next;
        return x;
    }
    
    // creates a new item with value x and puts it on top of the stack
    public void push(Object x) {
        Item p = new Item();
        p.value = x;
        p.next = top;
        top = p;
    }
    
    // string representation of the stack
    public String toString() {
        String s = "";
        for (Item p=top; p!=null; p=p.next) {
            s += p.value.toString() + " -> ";
        }
        s += "null";
        return s;
    }
    
    public static void main (String[] args) {
        ObjectStack stack = new ObjectStack();
        stack.push("first");
        stack.push("second");
        stack.push('t');   // wrapper classes are used implicitly
        stack.push(4.4);
        stack.push(true);  // ANYTHING can be pushed on an ObjectStack
        System.out.println( "stack: " + stack);
        System.out.println( "pop " + stack.pop() );
        System.out.println( "stack: " + stack);
        System.out.println( "pop " + stack.pop() );
        System.out.println( "stack: " + stack);
        stack.push(5);
        int n = (Integer) stack.pop();
    } 
    
}

/* output:
stack: true -> 4.4 -> t -> second -> first -> null
pop true
stack: 4.4 -> t -> second -> first -> null
pop 4.4
stack: t -> second -> first -> null
*/

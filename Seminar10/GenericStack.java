package Seminar10;
// Julia Boettcher 2013; 2014, 2015, 2016
//
// Implementation of a typed stack, using Java generics.
//
// test with: java GenericStack

public class GenericStack <ValueType> {
    
    private Item top = null;  // top of the stack

    private class Item {
        private ValueType value;
        private Item next;
    }

    // takes the top item off the stack and returns its value
    // if stack is empty, returns null
    public ValueType pop() {
        if (top == null) {
            return null;
        }
        ValueType x = top.value;
        top = top.next;
        return x;
    }
    
    // creates a new item with value x and puts it on top of the stack
    public void push(ValueType x) {
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
    
    // test this class by creating a string stack and a double stack
    public static void main (String[] args) {
        GenericStack<String> stringStack = new GenericStack<String>();
        GenericStack<Double> doubleStack = new GenericStack<Double>();
        stringStack.push("first");
        stringStack.push("second");
        stringStack.push("third");
        System.out.println( "stringStack: " + stringStack);
        System.out.println( "pop " + stringStack.pop() );
        System.out.println( "stringStack: " + stringStack);
        String s = stringStack.pop();
        System.out.println();
        doubleStack.push(1.1);
        doubleStack.push(2.2);
        doubleStack.push(3.3);
        System.out.println( "doubleStack: " + doubleStack);
        System.out.println( "pop " + doubleStack.pop() );
        System.out.println( "doubleStack: " + doubleStack);
        double d = doubleStack.pop();
    } 
    
}

/* output:
stringStack: third -> second -> first -> null
pop third
stringStack: second -> first -> null

doubleStack: 3.3 -> 2.2 -> 1.1 -> null
pop 3.3
doubleStack: 2.2 -> 1.1 -> null
*/

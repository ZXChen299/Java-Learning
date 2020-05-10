package Ex7;

public class Queue {
    private Item top = null;
    private Item bottom = null;

    private class Item {
        private int value;
        private Item next;
        private Item (int x, Item item) { value = x; next = item; }
    }

    public void enqueue( int x ){
        Item p = new Item( x, null );
        if( bottom == null ) { top = p; bottom = p; }
        else{
            bottom.next = p;
            bottom = p;
        }
        System.out.println( "enqueue " + x );
    }

    public int dequeue(){
        if ( top == null ) return -1;
        int x = top.value;
        top = top.next;
        return x;
    }

    public String toString(){
        String print = "";
        for( Item item = top; item!= null; item = item.next )
            print = print + item.value + " -> ";
        print = print + "null";
        return print;
    }

    public static void main(String[] args){
        Queue queue = new Queue();
        for(int i = 0; i < args.length; i++){
            if( Integer.parseInt(args[i]) == 0 )
                System.out.println( queue );
            else if( Integer.parseInt(args[i]) == -1 )
                System.out.println( "dequeue " + queue.dequeue() );
            else
                queue.enqueue( Integer.parseInt(args[i]) );
        }
    }

}

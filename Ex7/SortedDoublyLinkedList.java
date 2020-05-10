package Ex7;

public class SortedDoublyLinkedList {
    private Item max = null, min = null;

    private class Item{
        private int value;
        private Item right, left;
        private Item (int x, Item a, Item b) { value = x; right = a; left = b; }
    }

    public void insert( int x ) {
        Item p = new Item(x, null, null);
        boolean simple = ( max == null && min == null ) || x >= max.value || x <= min.value;
        if ( simple ) insertSimple( p );
        else
            for (Item item = max; item != null; item = item.left) {
                if (item.value < x) {
                    p.left = item;
                    p.right = item.right;
                    item.right.left = p;
                    item.right = p;
                    break;
                }
            }
    }

    public void insertSimple( Item p ){
        if ( max == null && min == null ) { max = p; min = p; }
        else if ( p.value >= max.value ) {
            max.right = p;
            p.left = max;
            max = p;
        }
        else if ( p.value <= min.value ) {
            min.left = p;
            p.right = min;
            min = p;
        }
    }

    public void delete( int x ){
        Item item = max;
        while( item != null ) {
            if( x == item.value ) {
                if( item == max ) {
                    max = item.left;
                    if( max == null ) { min = null; break;}
                    else max.right = null;
                }
            else if (item.left == null) {
                item.right.left = null;
                max = item.right;
                min = item.right;
                break;
            }
            else {
                item.left.right = item.right;
                item.right.left = item.left;
            }
            }
            item = item.left;
        }
    }

    public String toString(){
        String print = "null <-> ";
        for( Item item = max; item!= null; item = item.left )
            print = print + item.value + " <-> ";
        print = print + "null";
        return print;
    }

    public String reverseString(){
        String print = "null <-> ";
        for( Item item = min; item!= null; item = item.right )
            print = print + item.value + " <-> ";
        print = print + "null";
        return print;
    }

    public static void main(String[] args){
        SortedDoublyLinkedList list = new SortedDoublyLinkedList();
        for(int i = 0; i < args.length; i++){
            if( Integer.parseInt(args[i]) == 0 ) {
                System.out.println( list.reverseString() );
                System.out.println( list );
            }
            else if( Integer.parseInt(args[i]) < 0 ) {
                list.delete( -Integer.parseInt(args[i]) );
                System.out.println();
            }
            else list.insert( Integer.parseInt(args[i]) );
        }
    }

}

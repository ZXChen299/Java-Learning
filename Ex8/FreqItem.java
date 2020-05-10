package Ex8;
/*
Zixuan Chen
201917656
 */

public class FreqItem {
    public char c;
    public int count;
    public FreqItem next;

    public FreqItem(char c, int count, FreqItem next) {
        this.c = c;
        this.count = count;
        this.next = next;
    }

    public String toString() { return c + ":" + count + " " ; }

    public void print() { // print current item
        System.out.print( toString() );
    }
}

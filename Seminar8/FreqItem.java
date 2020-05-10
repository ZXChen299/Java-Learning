package Seminar8;

public class FreqItem // list Item for character count
{
    public char c;
    public int count;
    public FreqItem next;

    public FreqItem(char c, int count, FreqItem next) {
        this.c = c;
        this.count = count;
        this.next = next;
    }

    public String toString() { 
        return c + ":" + count ;
    }
}

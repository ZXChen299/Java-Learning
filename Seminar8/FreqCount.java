package Seminar8;

public class FreqCount {
    
    public FreqItem start; 

    public void print() {
        for (FreqItem p = start; p!=null; p = p.next)
            System.out.print(p + " ");
        System.out.println();
        return;
    }

    private int[] count = new int [256]; // array of counters

    private void resetcount() { // reset counters
        for (int i = 0; i < count.length; i++) 
            count[i] = 0;
        return;
    }

    private void increment(char c) { // increment the count for character c
        count [(int) c ] ++ ;
        return;
    }

    private void countchars (String s) {
    // creates list with frequencies of characters in s
        resetcount();
        for (int k = 0; k < s.length(); k++) 
            increment(s.charAt(k)); 
        start = null;
        // insert in alphabetical order by reverse insert-at-front
        for (int i = count.length-1; i>=0; i--) 
            if ( count[i]>0 ) 
                start = new FreqItem ( (char)i, count[i], start);
    }

    private String join (String[] args) { // join with blanks
        if (args.length == 0)
            return "";
        String s = args[0];
        for (int i=1; i < args.length; i++) 
            s = s + " " + args[i];
        return s ;
    }

    public FreqCount (String s) {
        countchars(s);
    }

    public FreqCount(String[] args) {
        countchars(join(args));
    }

    public static void main (String[] args) {

        if (args.length == 0) {
            System.out.println ("Please enter some text for character frequency count.");
            return;
        }

        FreqCount p = new FreqCount(args);
        p.print();
    }
}

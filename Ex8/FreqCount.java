package Ex8;
/*
Zixuan Chen
201917656
 */

public class FreqCount { // constructing a list of character frequencies
    
    public FreqItem start = null; // first list item

    public void print() { // print all the FreqItem
        for (FreqItem Item = start; Item != null; Item = Item.next){ Item.print(); }
    }

    private void countchars (String s) {
        if(s.length() == 0) return; // avoid null
        if(start == null){ // add first item
            FreqItem p = new FreqItem(s.charAt(0), 1, null);
            start = p;
        }
        if(s.length() > 1)
            for(int i = 1; i < s.length(); i++) increment(s.charAt(i));
    }

    private void increment(char c){
        FreqItem item = start;
        boolean haveAdded = false;

        while( item.next != null ){ // consider about equal of between two
            if(item.c == c){
                item.count++;
                haveAdded = true;
                break;
            }
            if(item.c < c && item.next.c > c){
                FreqItem p = new FreqItem(c,1,item.next);
                item.next = p;
                haveAdded = true;
                break;
            }
            item = item.next;
        }

        if(!haveAdded) //three exceptions from the while loop
            if(item.c == c) item.count++;
            else if(item.c < c){
                FreqItem p = new FreqItem(c,1,null);
                item.next = p;
            }
            else{
                FreqItem p = new FreqItem(c,1,start);
                start = p;
            }
    }

    public FreqCount (String s) { countchars(s); }

    public FreqCount(String[] args) {
        String s = ""; // change args[] into a string
        for(int i = 0; i < args.length-1; i++) s += args[i] + " ";
        s += args[args.length-1];
        FreqCount list = new FreqCount( s );
        start = list.start;
    }

    public static void main (String[] args) {
        if(args.length == 0) return;
        FreqCount word = new FreqCount( args );
        word.print();
    }
}

/* sample output
java MinQueue test test test case
 :3 a:1 c:1 e:4 s:4 t:6
 */
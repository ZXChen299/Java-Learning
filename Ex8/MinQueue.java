package Ex8;
/*
Zixuan Chen
201917656
*/

public class MinQueue {

    public FreqItem start; // the first item in MinQueue

    public MinQueue (FreqItem start) { this.start = start; }

    public boolean notempty() { return start != null; }

    public FreqItem extract_min() { // find the smallest item and delete it
        FreqItem min = start;
        for(FreqItem item = start; item != null; item = item.next)
            if( item.count < min.count ) min = item;
        remove(min);
        return min;
    }

    private void remove(FreqItem min){
        if( min == start ) start = min.next;
        else
            for(FreqItem item = start; item.next!= null; item = item.next)
                if( item.next == min ){
                    if(item.next.next != null) item.next = item.next.next;
                    else item.next = null;
                    break; }
    }

    public static void main (String[] args) {
        FreqCount f = new FreqCount(args) ;
        MinQueue q = new MinQueue(f.start) ;
        while (q.notempty()) // keep deleting until there is no item
            System.out.print(q.extract_min() + " ");
        System.out.println();
    }
}

/* sample output
java MinQueue test test test case
a:1  c:1   :3  e:4  s:4  t:6
 */

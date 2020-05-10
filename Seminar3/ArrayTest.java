package Seminar3;
// What does this program do?
// array could swap number in function
class ArrayTest {

    public static void main (String[] args) {

        // first part
        int[] a = new int [10];
        System.out.println(a);
        for(int i = 0; i<a.length; i++) {
            System.out.print (a[i] + " ");
        }
        System.out.println();

        // second part
        a = new int[2];
        a[0] = 23;
        a[1] = 42;
        for(int i = 0; i<a.length; i++) {
            System.out.print (a[i] + " ");
        }
        System.out.println();
        swap(a);
        for(int i = 0; i<a.length; i++) {
            System.out.print (a[i] + " ");
        }
        System.out.println();
    }

    static void swap(int[] x) {
        int temp = x[0];
        x[0] = x[1];
        x[1] = temp;
    }

}

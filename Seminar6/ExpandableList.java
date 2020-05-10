package Seminar6;

public class ExpandableList {
// API
//  public void print()
//      Constructs an array of length 1 and sets the number of entries
//      equal to 0.
//
//  public int getEntries() {
//      Returns the number of entries already added to the array 
//
//  public void addByDoubling(int number)
//      Adds number to next open position in the array. If there is no
//      open position, creates a new array of twice the size of the 
//      current array, copies the entries of the old array into the 
//      new one, and adds number to the next open position.
//
//  public void addByAppending(int number) {
//      Adds number to next open position in the array. If there is no
//      open position, creates a new array of size one more than the 
//      current array, copies the entries of the old array into the 
//      new one, and adds number to the new open position.
//

    private int[] value;
    private int entries;
    
    public ExpandableList() {
        entries = 0;
        value = new int[1];
    }
    
    public int getEntries() {
        return entries;
    }

    public void print() {
        for (int i = 0; i < entries; i++) {
            System.out.print(value[i] + " ");
        }
    }
    
    public void addByDoubling(int number) {
        if (entries == value.length) {
            int[] new_value = new int[2*value.length];
            for (int i = 0; i < value.length; i++) {
                new_value[i] = value[i];
            }
            value = new_value;
        }
        value[entries] = number;
        entries++;
    }

    public void addByAppending(int number) {
        if (entries == value.length) {
            int[] new_value = new int[value.length+1];
            for (int i = 0; i < value.length; i++) {
                new_value[i] = value[i];
            }
            value = new_value;
        }
        value[entries] = number;
        entries++;
    }


}

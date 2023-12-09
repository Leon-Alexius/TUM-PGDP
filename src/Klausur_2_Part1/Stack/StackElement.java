package Klausur_2_Part1.Stack;

public class StackElement {
    private int value;
    private StackElement next;

    /*
    ====================================================================================================================
                                                  Constructor
    ====================================================================================================================
     */
    public StackElement(int value) {
        this.value = value;
        this.next = null;
    }

    public StackElement(int value, StackElement next) {
        this.value = value;
        this.next = next;
    }

    /*
    ====================================================================================================================
                                                Setter and Getter
    ====================================================================================================================
     */
    public int getValue() {
        return value;
    }

    public StackElement getNext() {
        return next;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setNext(StackElement next) {
        this.next = next;
    }
}

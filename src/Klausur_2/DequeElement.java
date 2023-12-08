package Klausur_2;

public class DequeElement {
    // same as Double Chained List
    private DequeElement prev;
    private DequeElement next;
    private int value;

    /*
    ====================================================================================================================
                                                  Constructor
    ====================================================================================================================
     */
    DequeElement(int v) {
        this.prev = null;
        this.value = v;
        this.next = null;
    }

    DequeElement(int v, DequeElement next) {
        this.prev = null;
        this.value = v;
        this.next = next;
    }

    DequeElement(DequeElement prev, int v) {
        this.prev = prev;
        this.value = v;
        this.next = null;
    }

    /*
    ====================================================================================================================
                                                Setter and Getter
    ====================================================================================================================
     */

    public int getValue() {
        return value;
    }

    public DequeElement getNext() {
        return next;
    }

    public DequeElement getPrev() {
        return prev;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setNext(DequeElement next) {
        this.next = next;
    }

    public void setPrev(DequeElement prev) {
        this.prev = prev;
    }
}
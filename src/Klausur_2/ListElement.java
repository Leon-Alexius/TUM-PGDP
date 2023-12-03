package Klausur_2;

public class ListElement {
    private int value;
    private ListElement next;

    /*
    ====================================================================================================================
                                                  Constructor
    ====================================================================================================================
     */
    public ListElement(int value, ListElement next) {
        this.value = value;
        this.next = next;
    }

    public ListElement(int value) {
        this.value = value;
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

    public ListElement getNext() {
        return next;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setNext(ListElement next) {
        this.next = next;
    }
}

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
                                                Other Methods
    ====================================================================================================================
     */

    /**
     * Get current value as String
     * @return String value
     */
    public String toString() {
        return String.valueOf(value);
    }

    /**
     * Search for the last element in List Chain starting from current Element (Recursive)
     * @return last Element in List Chain
     */
    public ListElement getTail_fromElementPerspective(){
        return getTail_fromElementPerspective(this);
    }

    private ListElement getTail_fromElementPerspective(ListElement listElement){
        if(this.next == null){
            return this;
        }
        else {
            return getTail_fromElementPerspective(this.next);
        }
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

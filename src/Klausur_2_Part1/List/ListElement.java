package Klausur_2_Part1.List;

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

    /**
     * Get List Size counted from caller element (inclusive)
     * <ul>
     *     <li>
     *         List ex = [0, 10, -77, 1, 11, -100, 2, 12, 3, 13, 4, 14, 5]
     *     </li>
     *     <li>
     *         ex.getListElementAtIndex(0).getSize_fromElementPerspective() = 13
     *     </li>
     *     <li>
     *         ex.getListElementAtIndex(12).getSize_fromElementPerspective() = 1
     *     </li>
     *     <li>
     *         ex.getSize() = 13
     *     </li>
     * </ul>
     * @return List Size from Element Perspective
     */
    public int getSize_fromElementPerspective() {
        int result = 1;
        for(ListElement t = next; t != null; t = t.next) {
            result++;
        }
        return result;
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

package Klausur_2;

public class DoubleChainedListElement {
    private int value;
    private DoubleChainedListElement prev;
    private DoubleChainedListElement next;

    /*
    ====================================================================================================================
                                                  Constructor
    ====================================================================================================================
     */
    public DoubleChainedListElement(int value) {
        this.value = value;
        this.prev = null;
        this.next = null;
    }

    public DoubleChainedListElement(int value, DoubleChainedListElement prev) {
        this.value = value;
        this.prev = prev;
        this.next = null;
    }

    /*
    ====================================================================================================================
                                             Recursive Helper Methods
    ====================================================================================================================
     */

    /**
     * Add new Element at the end of List, recursively iterate through the List to find tail starting from Caller Element
     * @param value new Value
     * @return new Element
     */
    public DoubleChainedListElement add_fromElementPerspective(int value) {
        if (next != null) {
            return next.add_fromElementPerspective(value);
        } else {
            next = new DoubleChainedListElement(value, this);
            return next;
        }
    }

    /**
     * add a new Element at the specified index (calculated inclusive from caller Element)
     * @param value new Element value
     * @param idx target Index
     */
    protected void insertAt_fromElementPerspective(int value, int idx) {
        if(idx == 0){
            // chain up newElement
            DoubleChainedListElement newElement = new DoubleChainedListElement(value, this.prev);
            newElement.setNext(this);

            // re-chain oldElements
            if(this.prev != null){
                this.prev.setNext(newElement);
            }
            this.setPrev(newElement);
            return;
        }
        next.insertAt_fromElementPerspective(value, idx - 1);
    }

    /**
     * get an element with index counted from caller element (inclusive)
     * <ol>
     *     <li>{1, 2, 3, 4, 5}</li>
     *     <li>index = 0 -> 1, caller element = 1</li>
     *     <li>index = 1 -> 3, caller element = 2</li>
     * </ol>
     * @param idx index
     * @return DoubleChainedListElement or null
     */
    public DoubleChainedListElement getElementByIndex_FromElementPerspective(int idx) {
        if (idx == 0) {
            return this;
        }
        if (next == null) {
            return null;
        }
        return next.getElementByIndex_FromElementPerspective(idx - 1);
    }

    /**
     * Calculates List Size starting from caller Element (inclusive)
     * @return List Size (int)
     */
    public int calculateSize_fromElementPerspective() {
        if (next == null) {
            return 1;
        }
        return 1 + next.calculateSize_fromElementPerspective();
    }

    /**
     * Get the Head Element of current List (starting point for search is caller Element)
     * @return Head Element
     */
    public DoubleChainedListElement getHead(){
        if(this.prev == null){
            return this;
        }
        return this.prev.getHead();
    }

    /**
     * Get the Tail Element of current List (starting point for search is caller Element)
     * @return Tail Element
     */
    public DoubleChainedListElement getTail(){
        if(this.next == null){
            return this;
        }
        return this.next.getTail();
    }

    /**
     * normal toString method - value of current Element
     * @return value of current Element
     */
    @Override
    public String toString(){
        return String.valueOf(this.getValue());
    }

    /**
     * String representation of Element chain
     * @return String representation of Element chain
     */
    protected String toString_Recursive() {
        StringBuilder sb = new StringBuilder();
        DoubleChainedListElement tmp = this;
        do {
            sb.append(tmp.value).append(", ");
            tmp = tmp.next;
        } while (tmp != null);
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    /*
    ====================================================================================================================
                                                Setter and Getter
    ====================================================================================================================
     */

    public int getValue() {
        return value;
    }

    public DoubleChainedListElement getNext() {
        return next;
    }

    public DoubleChainedListElement getPrev() {
        return prev;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setNext(DoubleChainedListElement next) {
        this.next = next;
    }

    public void setPrev(DoubleChainedListElement prev) {
        this.prev = prev;
    }
}

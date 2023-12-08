package Klausur_2_Part1;

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
     * remove an Element at given index
     * @param idx Index of Element to be removed
     * @return an Element that is guaranteed to be in List (can be null if <code>List.size</code> = 1)
     */
    protected DoubleChainedListElement removeAt_fromElementPerspective(int idx){
        if(idx == 0){
            DoubleChainedListElement prevElement = this.prev;
            DoubleChainedListElement nextElement = this.next;
            boolean returnPrevElement = true;

            if(prevElement != null){
                prevElement.setNext(nextElement);
            }
            if(nextElement != null){
                returnPrevElement = false;
                nextElement.setPrev(prevElement);
            }

            // return an Element inside the List
            if(returnPrevElement){
                return prevElement;
            }
            else {
                return nextElement;
            }
        }
        return this.next.removeAt_fromElementPerspective(idx - 1);
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
     * Get Next Element from current Element (recursive, caller = start element = index 0)
     * <ul>
     *     <li>[a, b, c, d, e]</li>
     *     <li>index = 1 with caller = c, returns d</li>
     * </ul>
     * @param idx index
     * @return target element
     */
    public DoubleChainedListElement getNextElementByIndex_FromElementPerspective(int idx) {
        // return the element's next
        if (idx == 0) {
            return this;
        }
        if (next == null) {
            throw new RuntimeException("Error: Index out of Bounds");
        }
        return next.getNextElementByIndex_FromElementPerspective(idx - 1);
    }

    /**
     * Get Prev Element from current Element (recursive, caller = start element = index 0)
     * <ul>
     *     <li>[a, b, c, d, e]</li>
     *     <li>index = 1 with caller = c, returns b</li>
     * </ul>
     * @param idx index
     * @return target element
     */
    public DoubleChainedListElement getPreviousElementByIndex_FromElementPerspective(int idx) {
        // return the element's previous
        if (idx == 0) {
            return this;
        }
        if(prev == null){
            throw new RuntimeException("Error: Index out of Bounds");
        }
        return next.getPreviousElementByIndex_FromElementPerspective(idx - 1);
    }

    /**
     * Recursively adding the value of List Chain, starting from current Element
     * @return total value of List Chain, starting from current Element
     */
    public int getSum_FromElementPerspective() {
        if(next == null) {
            return value;
        }

        return value + next.getSum_FromElementPerspective();
    }

    /**
     * Recursively adds the List Chain Elements to the List Parameter
     * @param copySoFar List Chain (Copy)
     * @return Parameter List Chain
     */
    public DoubleChainedList createCopy_FromElementPerspective(DoubleChainedList copySoFar) {
        copySoFar.add(value);
        if(next == null) {
            return copySoFar;
        }
        return next.createCopy_FromElementPerspective(copySoFar);
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
                                                Other Methods
    ====================================================================================================================
     */

    /**
     * remove Elements from current List that doesn't comply to rule (ascending or descending)
     * @param increasing true/ false
     */
    public void removeSort(boolean increasing) {
        if (next == null) {
            return;
        }
        if (increasing && next.value < this.value || !increasing && next.value > this.value) {
            // remove element (break chain)
            next = next.next;
            if (next != null) {
                next.prev = this;
                removeSort(increasing);
            }
        }
        else {
            next.removeSort(increasing);
        }
    }

    /**
     * counts the total value of elements following a set of criteria
     * @param threshold threshold value
     * @return long Array [<, =, >]
     */
    public long[] countThreshold(int threshold) {
        long[] result = new long[3];
        this.countThreshold(threshold, result);
        return result;
    }

    private void countThreshold(int threshold, long[] counter) {
        if (value < threshold) {
            counter[0] += value;
        } else if (value > threshold) {
            counter[2] += value;
        } else {
            counter[1] += value;
        }
        if (next != null) {
            next.countThreshold(threshold, counter);
        }
    }

    /**
     * Reverse the Element chain with new tail = caller element
     */
    public void reverse() {
        this.reverseHelper(null);
    }

    private void reverseHelper(DoubleChainedListElement prev) {
        DoubleChainedListElement tmp = this.next;
        this.next = prev;
        this.prev = tmp;
        if (tmp == null) {
            return;
        }
        tmp.reverseHelper(this);
    }

    /**
     * zip 2 element chains together (l1, l2 each represents their own chain)
     * <br>
     * usage: l1 (part of List1), l2 (part of List2) -> combined chain (l1 = edited l1)
     * @param l1 element 1
     * @param l2 element 2
     */
    public static void zip(DoubleChainedListElement l1, DoubleChainedListElement l2) {
        zipHelper(l1, l2, l1.next);
    }

    private static void zipHelper(DoubleChainedListElement l, DoubleChainedListElement l1, DoubleChainedListElement l2) {
        if (l1 == null) {
            if (l2 == null) {
                return;
            }
            l.next = l2;
            l2.prev = l;
            return;
        }
        if (l2 == null) {
            l.next = l1;
            l1.prev = l;
            return;
        }
        l.next = l1;
        l1.prev = l;
        zipHelper(l.next, l2, l1.next);
    }

    /**
     * Simple method to check if an Element has same value with other Element
     * @param other other Element
     * @return boolean
     */
    public boolean isEqual(DoubleChainedListElement other) {
        return other != null && this.value == other.value;
    }

    /**
     * normal toString method - value of current Element
     * @return value of current Element
     */
    @Override
    public String toString(){
        return String.valueOf(this.getValue());
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

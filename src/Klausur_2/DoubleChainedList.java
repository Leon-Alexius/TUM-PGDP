package Klausur_2;

public class DoubleChainedList {
    private DoubleChainedListElement head;
    private DoubleChainedListElement tail;
    private int size;

    /*
    ====================================================================================================================
                                               Constructor and Reset
    ====================================================================================================================
     */
    public DoubleChainedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Reset current List
     */
    public void reset() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /*
    ====================================================================================================================
                                                Recursive Methods
    ====================================================================================================================
     */

    /**
     * add a new Element at the end of current List
     * @param value new Element value to be added
     */
    public void add_Recursive(int value) {
        if (head == null) {
            head = new DoubleChainedListElement(value);
        } else {
            head.add_fromElementPerspective(value);
        }

        // update value
        head = head.getHead();
        tail = head.getTail();
        size = calculateSize_Recursive();
    }

    /**
     * add a new Element at the specified index
     * @param value new Element's value
     * @param idx target index (0 to size)
     */
    public void insertAt_Recursive(int value, int idx) {
        if(idx > size || idx < 0){
            throw new RuntimeException("Error: Index out of Bounds or invalid");
        }
        if (head == null) {
            add_Recursive(value);
        }
        else{
            head.insertAt_fromElementPerspective(value, idx);
        }

        // update value
        head = head.getHead();
        tail = head.getTail();
        size = calculateSize_Recursive();
    }

    /**
     * removes an Element at given index, returns RuntimeException if index invalid
     * @param idx index of an Element
     */
    public void removeAt_Recursive(int idx){
        if(idx > size || idx < 0){
            throw new RuntimeException("Error: Index out of Bounds or invalid");
        }
        if(size == 1){
            reset();
        }
        else {
            DoubleChainedListElement pointer = head.removeAt_fromElementPerspective(idx);

            // update value
            head = pointer.getHead();
            tail = pointer.getTail();
            size = calculateSize_Recursive();
        }
    }

    /**
     * Get the Element at given index
     * @param idx target index
     * @return target element
     */
    public DoubleChainedListElement getElementByIndex_Recursive(int idx) {
        if (head == null) {
            throw new RuntimeException("Error: list is empty!");
        }
        DoubleChainedListElement result = head.getElementByIndex_FromElementPerspective(idx);
        if(result == null){
            throw new RuntimeException("Error: Index out of Bounds");
        }
        return result;
    }

    /**
     * Calculates current List Size recursively
     * @return current List Size
     */
    private int calculateSize_Recursive() {
        return head == null ? 0 : head.calculateSize_fromElementPerspective();
    }

    /**
     * String representation of current List
     * @return String representation of current List
     */
    public String toString_Recursive(){
        if (head != null) {
            return "[" + head.toString_Recursive() + "]";
        } else {
            return "Empty list";
        }
    }

    /*
    ====================================================================================================================
                                                 Others Methods
    ====================================================================================================================
     */

    /**
     * get a Long Array, which counts the total value of elements following a set of criteria <code>< and = and ></code>
     * <ul>
     *     <li>Input: [1,2,3,4,5], threshold = 3</li>
     *     <li>Output: [3,3,9]</li>
     * </ul>
     * @param threshold single int value
     * @return long array, size = 3
     */
    public long[] countThreshold(int threshold) {
        if (head == null) {
            return new long[3];
        }
        return head.countThreshold(threshold);
    }

    /**
     * Remove Elements from current List that doesn't comply to rule (ascending or descending)
     * <ul>
     *     <li>[3,2,4,7,1,6,5,9,8]</li>
     *     <li>(increasing = true): [3,4,7,9]</li>
     *     <li>(increasing = false): [3,2,1]</li>
     * </ul>
     * @param increasing true/ false
     */
    public void removeSort(boolean increasing) {
        if (head == null) {
            return;
        }
        head.removeSort(increasing);

        // update value
        tail = head.getTail();
        size = calculateSize_Recursive();
    }

    /**
     * reverse current List Object
     */
    public void reverse() {
        if (head == null) {
            return;
        }
        head.reverse();

        // update value
        DoubleChainedListElement tmp = head;
        head = tail;
        tail = tmp;
    }

    /**
     * Zip two List together (original list will be edited)
     * <ul>
     *     <li>l1: [a,b,c,d,e]</li>
     *     <li>l2: [2,4,6]</li>
     *     <li>Result -> l1: [a,2,b,4,c,6,d,e]</li>
     * </ul>
     * @param l1 input list 1
     * @param l2 input list 2
     */
    public static void zip(DoubleChainedList l1, DoubleChainedList l2) {
        // edge case
        if (l1.head == null) {
            if (l2.head == null) {
                return;
            }
            l1.head = l2.head;
            return;
        }

        DoubleChainedListElement.zip(l1.head, l2.head);

        // update l1 chain
        l1.tail = l1.head.getTail();
        l1.size = l1.calculateSize_Recursive();
    }

    /**
     * example output: [] or [a] or [a, b, c, d, e]
     * @return String representation
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        if(head != null){
            stringBuilder.append(head.getValue());

            if(head.getNext() != null){
                for (DoubleChainedListElement current = head.getNext(); current != null; current = current.getNext()) {
                    stringBuilder.append(", ");
                    stringBuilder.append(current.getValue());
                }
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    /*
    ====================================================================================================================
                                                Setter and Getter
    ====================================================================================================================
     */
    public int getSize() {
        return size;
    }

    public DoubleChainedListElement getHead() {
        return head;
    }

    public DoubleChainedListElement getTail() {
        return tail;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setHead(DoubleChainedListElement head) {
        this.head = head;
    }

    public void setTail(DoubleChainedListElement tail) {
        this.tail = tail;
    }
}

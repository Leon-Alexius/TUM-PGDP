package Klausur_2;

import jdk.jfr.Experimental;

public class List {
    private ListElement head;
    private ListElement tail;
    private int size;

    /*
    ====================================================================================================================
                                               Constructor and Reset
    ====================================================================================================================
     */
    public List(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Reset current List (this is enough) + releasing its chain Elements manually (kinda excessive)
     */
    public void reset() {
        // release ListElement chain (actually not needed)
        int indexPointer = size - 2; // start from 2nd from back

        while(indexPointer > 0){
            ListElement currentElement = getListElementAtIndex(indexPointer--);
            currentElement.setNext(null);
        }
        if(size >= 1){
            this.head.setNext(null);
        }

        // reset List pointer (only this part is enough)
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /*
    ====================================================================================================================
                                              Add - Remove Methods
            Notes - Important: addElement(), insertElement() will set .next of input ListElement accordingly
            Notes - Important: therefore, input ListElement will be "broken"
    ====================================================================================================================
     */

    /**
     * add a new Element at the end of current List
     * @param value new Element value to be added
     */
    public void add(int value) {
        if (head == null) {
            head = new ListElement(value);
            tail = head;
        }
        else {
            tail.setNext(new ListElement(value));
            tail = tail.getNext();
        }
        size++;
    }

    /**
     * add a new Element at the specified index
     * <ul>
     *     <li> minIndex = 0 </li>
     *     <li> maxIndex = size/ tail </li>
     * </ul>
     * @param index index specified
     * @param value new Element's value
     */
    public void insertAt(int index, int value) {
        if (index > size || index < 0) {
            return;
        }
        if (head == null || index == size) {
            add(value);
            return;
        }
        else if (index == 0) {
            head = new ListElement(value, head);
        }
        else {
            ListElement prev = null;
            ListElement current = head;
            for (int i = 0; i < index; i++) {
                prev = current;
                current = current.getNext();
            }
            prev.setNext(new ListElement(value, current));
        }
        size++;
    }

    /**
     * Insert one new element but sorted in ascending order
     * <br>
     * {0, 2, 5, 6} insert 3 -> {0, 2, 3, 5, 6}
     * @param value value of new element
     */
    public void insertSorted(int value){
        int index = getIndexAfterLastSmallerElement(value);
        insertAt(index, value);
    }

    /**
     * Modified <code>add()</code> Method, now accept ListElement
     * @param listElement new Element
     */
    public void addElement(ListElement listElement){
        listElement.setNext(null);
        if (head == null) {
            head = listElement;
            tail = head;
        }
        else {
            tail.setNext(listElement);
            tail = tail.getNext();
        }
        size++;
    }

    /**
     * Modified <code>insertAt()</code> Method, now accept ListElement
     * @param index index specified
     * @param listElement new Element
     */
    public void insertElementAt(int index, ListElement listElement){
        if (index > size || index < 0) {
            return;
        }
        if (head == null || index == size) {
            addElement(listElement);
            return;
        }
        else if (index == 0) {
            listElement.setNext(head);
            head = listElement;
        }
        else {
            ListElement prev = null;
            ListElement current = head;
            for (int i = 0; i < index; i++) {
                prev = current;
                current = current.getNext();
            }
            listElement.setNext(current);
            prev.setNext(listElement);
        }
        size++;
    }

    /**
     * Modified <code>insertSorted()</code> Method, now accept ListElement
     * @param listElement new Element
     */
    public void insertElementSorted(ListElement listElement){
        int index = getIndexAfterLastSmallerElement(listElement.getValue());
        insertElementAt(index, listElement);
    }

    /**
     * remove the last element in the list
     * @return the removed element's value
     */
    public int remove(){
        if (head == null) {
            return -1;
        }
        else {
            return removeAt(size - 1);
        }
    }

    /**
     * remove element at given index
     * @param index target index
     * @return value of removed element
     */
    public int removeAt(int index){
        if (head == null || index >= size || index < 0) {
            return -1;
        }

        ListElement ElementToBeRemoved = getListElementAtIndex(index);
        if(size == 1){
            // remove single element
            head = tail = null;
            size--;
        }
        else if(index == 0){
            // remove head
            head = ElementToBeRemoved.getNext();
            ElementToBeRemoved.setNext(null);
            size--;
        }
        else if(index == size -1){
            // remove tail
            ListElement prevOf_ElementToBeRemoved = getListElementAtIndex(index - 1);
            tail = prevOf_ElementToBeRemoved;
            prevOf_ElementToBeRemoved.setNext(null);
            size--;
        }
        else {
            // remove between head and tail
            ListElement nextOf_ElementToBeRemoved = ElementToBeRemoved.getNext();
            ListElement prevOf_ElementToBeRemoved = getListElementAtIndex(index - 1);
            prevOf_ElementToBeRemoved.setNext(nextOf_ElementToBeRemoved);
            size--;
        }

        return ElementToBeRemoved.getValue();
    }

    /**
     * removes all element with value in current List
     * @param value value as int
     */
    public void removeValue(int value){
        int index = Integer.MAX_VALUE;
        while (index != -1){
            // even if index = -1, removeAt(-1) will just also simply return -1
            index = getListElementIndexByValue(value);
            removeAt(index);
        }
    }

    /*
    ====================================================================================================================
                                              (static) Methods
     Important Notes:
     > If you want to preserve original input List, then make a copy of the input List and use it as input instead
    ====================================================================================================================
     */

    /**
     * Create a new sorted List based on the values
     * @param values (multiple) value(s), null, or empty argument
     * @return new List
     */
    public static List addMultipleValueSorted_newList(int... values){
       List result = new List();

       // edge case - addMultipleValueSorted(null);
       if(values == null){
           return result;
       }

       for(int value : values){
           result.insertSorted(value);
       }

       return result;
    }

    /**
     * add values to current List (sorted Insert - modifiable)
     * @param values (multiple) value(s), null, or empty argument
     */
    public void addMultipleValueSorted_currentList(int... values){
        // edge case - addMultipleValueSorted(null);
        if(values == null){
            return;
        }

        for(int value : values){
            this.insertSorted(value);
        }
    }

    /**
     * Create new List using input elements (no new element generated) - input Element will be edited
     * <br>
     * Note: we use the input elements as elements of output List rather than creating new element based on the input elements' value
     * @param elements input elements
     * @return new List
     */
    public static List addMultipleElementsSorted_newList(ListElement... elements){
        List result = new List();

        // edge case - addMultipleElements(null);
        if(elements == null){
            return result;
        }

        for(ListElement listElement : elements){
            result.insertElementSorted(listElement);
        }

        return result;
    }

    /**
     * Adds ListElements to current List (sorted Insert - modifiable)
     * @param elements input elements
     */
    public void addMultipleElementsSorted_currentList(ListElement... elements){
        // edge case - addMultipleElements(null);
        if(elements == null){
            return;
        }

        for(ListElement listElement : elements){
            this.insertElementSorted(listElement);
        }
    }

    /**
     * Merge given Lists into one List (sorted ascending) - IMPORTANT WARNING (READ MORE)
     * <br>
     * <br>
     * this method will break the original List inputs, since it "chains" the elements together,
     * but doesn't update the <code>.head</code> and <code>.tail</code> bzw. <code>.size</code> from the ori. input
     * <br>
     * <br>
     * Example: given a = {1, 2, 4} and b = {-2, 3} -> combined as = {-2, 1, 2, 3, 4}
     * <ul>
     *     <li>a = {1, 2, 3, 4} since it's head stays 1</li>
     *     <li>b = {-2, 1, 2, 3, 4} since it's head stays -1</li>
     * </ul>
     * @param lists input lists
     * @return new List
     */
    public static List addMultipleListsSorted_newList(List... lists){
        List result = new List();

        // edge case - addMultipleLists(null);
        if(lists == null){
            return result;
        }

        for(List list : lists){
            if(list == null || list.head == null){
                continue;
            }

            ListElement currentElement = list.head;
            while(currentElement != null){
                // need nextElement since insertElementSorted will assign .next = null
                ListElement nextElement = currentElement.getNext();

                result.insertElementSorted(currentElement);
                currentElement = nextElement;
            }
        }

        return result;
    }

    /**
     * Adds multiple lists to current List (also destroy input Lists)
     * @param lists input lists
     */
    public void addMultipleListsSorted_currentList(List... lists){
        // edge case - addMultipleLists(null);
        if(lists == null){
            return;
        }

        for(List list : lists){
            if(list == null || list.head == null){
                continue;
            }

            ListElement currentElement = list.head;
            while(currentElement != null){
                // need nextElement since insertElementSorted will assign .next = null
                ListElement nextElement = currentElement.getNext();

                this.insertElementSorted(currentElement);
                currentElement = nextElement;
            }
        }
    }

    /**
     * Zip multiple input List without destroying the original List (created new Elements) - modifiable
     * <ul>
     *     <li>list = [0, 1, 2, 3, 4, 5]</li>
     *     <li>list1 = [10, 11, 12, 13, 14]</li>
     *     <li>list2 = [-77, -100]</li>
     *     <li>List.zipMultipleLists(list, list1, list2); = [0, 10, -77, 1, 11, -100, 2, 12, 3, 13, 4, 14, 5]</li>
     * </ul>
     * @param lists input lists
     * @return a new List with new Elements
     */
    public static List zipMultipleLists(List... lists){
        List result = new List();
        // edge case
        if(lists == null){
            return result;
        }

        // set max Length and edge case check
        int totalElement = 0;
        for(List list : lists){
            totalElement += list.getSize();
        }
        if(totalElement == 0){
            return result;
        }

        // start here
        int takenElement = 0;
        int indexPointer = 0;
        while(takenElement < totalElement){
            for(List list : lists){
                if(indexPointer > list.getSize() - 1){
                    continue;
                }
                result.add(list.getListElementAtIndex(indexPointer).getValue());
                takenElement++;
            }
            indexPointer += 1;
        }

        return result;
    }

    /*
    ====================================================================================================================
                                           Get List Elements & Index
    ====================================================================================================================
     */

    /**
     * gets the ListElement at given index
     * @param index target index
     * @return ListElement at given index
     */
    public ListElement getListElementAtIndex(int index){
        if(index >= size){
            throw new RuntimeException("Index out of bound, can't be equal/ greater than size");
        }

        ListElement currentElement = head;
        while(index > 0){
            currentElement = currentElement.getNext();
            index--;
        }

        return currentElement;
    }

    /**
     * Recursive version of getListElementAtIndex
     * @param index target index
     * @return ListElement at given index
     */
    public ListElement getListElementAtIndexRecursive(int index){
        return getListElementAtIndexRecursive(head, index);
    }

    private ListElement getListElementAtIndexRecursive(ListElement currentElement, int index){
        if(index >= size){
            throw new RuntimeException("Index out of bound, can't be equal/ greater than size");
        }
        if(index == 0){
            return currentElement;
        }
        return getListElementAtIndexRecursive(currentElement.getNext(), index - 1);
    }

    /**
     * Get List Element (first occurrence) by value, returns null if none found
     * @param value given Value
     * @return Liste Element or null
     */
    public ListElement getListElementByValue(int value){
        boolean found = false;
        ListElement currentElement = this.head;

        // included edge case where List = null
        while(currentElement != null){
            if(currentElement.getValue() == value){
                found = true;
                break;
            }
            currentElement = currentElement.getNext();
        }

        if(found){
            return currentElement;
        }
        else {
            return null;
        }
    }

    /**
     * get index of List Element (first occurrence) by value, returns -1 if none found
     * @param value List Element Value
     * @return index or -1
     */
    public int getListElementIndexByValue(int value){
        boolean found = false;
        int index = 0;
        ListElement currentElement = this.head;

        // included edge case where List = null
        while(currentElement != null){
            if(currentElement.getValue() == value){
                found = true;
                break;
            }
            currentElement = currentElement.getNext();
            index++;
        }

        if(found){
            return index;
        }
        else {
            return -1;
        }
    }

    /**
     * Help Method for public void insertSorted(int value)
     * <br>
     * {0, 4, 7, 8} with value = 5, will return 2
     * @param value value of new element
     * @return (index + 1) of the last smaller element
     */
    private int getIndexAfterLastSmallerElement(int value) {
        int index = 0;
        ListElement currentElement = head;
        while (currentElement != null && currentElement.getValue() < value) {
            index++;
            currentElement = currentElement.getNext();
        }
        return index;
    }

    /*
    ====================================================================================================================
                                                Other Methods
    ====================================================================================================================
     */

    /**
     * reverse current List Object
     */
    public void reverse(){
        if(head == null){
            return;
        }

        ListElement targetListElement = null;
        ListElement currentListElement = head;
        ListElement temp = tail;

        // switch head and tail
        head = temp;
        tail = currentListElement;

        // start reversing the chain
        while(currentListElement != null) {
            temp = currentListElement.getNext();
            currentListElement.setNext(targetListElement);
            targetListElement = currentListElement;
            currentListElement = temp;
        }
    }

    /**
     * check if current List is empty
     * @return boolean
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * example output: [] or [a] or [a, b, c, d, e]
     * @return String representation
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        if(head != null){
            stringBuilder.append(head.getValue());

            if(head.getNext() != null){
                for (ListElement current = head.getNext(); current != null; current = current.getNext()) {
                    stringBuilder.append(", ");
                    stringBuilder.append(current.getValue());
                }
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    /**
     * Create new List with filtered based on range (all-inclusive, modifiable)
     * <br>
     * <b>Modifiable</b>
     * <ul>
     *     <li>inclusivity -> edit <code>if()</code> condition</li>
     *     <li>sorted output -> edit <code>output.add()</code> to <code>output.insertSorted()</code></li>
     * </ul>
     * @param lowerBound lower Value (inclusive)
     * @param upperBound upper Value (inclusive)
     * @return new List (can be null)
     */
    public List getFilteredList(int lowerBound, int upperBound){
        List output = new List();

        ListElement currentElement = this.head;
        while(currentElement != null){
            int valueOfCurrentElement = currentElement.getValue();
            if(lowerBound <= valueOfCurrentElement && valueOfCurrentElement <= upperBound){
                output.add(valueOfCurrentElement);
            }
            currentElement = currentElement.getNext();
        }

        return output;
    }

    /*
    ====================================================================================================================
                                                 Recursive Methods
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

        long[] result = new long[3];
        return countThreshold(head, threshold, result);
    }

    public long[] countThreshold(ListElement currentElement, int threshold, long[] result){
        int value = currentElement.getValue();
        if (value < threshold) {
            result[0] += value;
        } else if (value > threshold) {
            result[2] += value;
        } else {
            result[1] += value;
        }
        if (currentElement.getNext() != null) {
            countThreshold(currentElement.getNext(), threshold, result);
        }

        return result;
    }


    /*
    ====================================================================================================================
                                                Setter and Getter
    ====================================================================================================================
     */
    public int getSize() {
        return size;
    }

    public ListElement getHead() {
        return head;
    }

    public ListElement getTail() {
        return tail;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setHead(ListElement head) {
        this.head = head;
    }

    public void setTail(ListElement tail) {
        this.tail = tail;
    }
}

package Klausur_2;

public class List {
    private ListElement head;
    private ListElement tail;
    private int size;

    /*
    ====================================================================================================================
                                                  Constructor
    ====================================================================================================================
     */
    public List(){
        this.head = null;
        this.tail = null;
        size = 0;
    }

    /*
    ====================================================================================================================
                                              Add - Remove Methods
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

    // TODO: Remove Method

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

    // TODO: Zip Method

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

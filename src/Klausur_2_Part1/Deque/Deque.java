package Klausur_2_Part1.Deque;

/**
 * Deque = Stack + Queue; you can choose to remove/ add elements from back/ front
 * @author DarkRosaleen
 */
public class Deque {
    private DequeElement head;
    private DequeElement tail;
    private int size;

    /*
    ====================================================================================================================
                                               Constructor and Reset
    ====================================================================================================================
     */
    public Deque() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void reset(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /*
    ====================================================================================================================
                                              Add - Remove Methods
                                        Front (head), ..., ..., Back (tail)
    > {a, b, c} -> head = a, tail = c, a.getNext() = b
    > addFront(x) -> {x, a, b, c}
    > addBack(x) -> {a, b, c, x}
    ====================================================================================================================
     */

    /**
     * Add an element to the Front of Deque
     * @param value new Element's value
     */
    public void addFront(int value) {
        if (isEmpty()) {
            head = new DequeElement(value);
            tail = head;
        } else {
            head = new DequeElement(value, head);
            head.getNext().setPrev(head);
        }
        size++;
    }

    /**
     * Remove an element from the Front of Deque
     * @return value of removed element
     */
    public int removeFront() {
        // not possible to remove anything
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        }

        int value = getHead().getValue();
        if (getSize() == 1) {
            reset();
        }
        else {
            head = head.getNext();
            head.setPrev(null);
            size--;
        }
        return value;
    }

    /**
     * Add an element to the Back of Deque
     * @param value new Element's value
     */
    public void addBack(int value) {
        if (isEmpty()) {
            head = new DequeElement(value);
            tail = head;
        } else {
            tail = new DequeElement(tail, value);
            tail.getPrev().setNext(tail);
        }
        size++;
    }

    /**
     * Remove an element from the Back of Deque
     * @return value of removed element
     */
    public int removeBack() {
        // not possible to remove anything
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        }

        int value = getTail().getValue();
        if (getSize() == 1) {
            reset();
        }
        else {
            tail= tail.getPrev();
            tail.setNext(null);
            size--;
        }
        return value;
    }

    /*
    ====================================================================================================================
                                                 Others Methods
    ====================================================================================================================
     */

    /**
     * Simple check if current Deque is empty
     * @return boolean
     */
    public boolean isEmpty() {
        return getSize() == 0;
    }

    /**
     * String representation of current Deque -> <code>{elements}</code>
     * @return String representation of current Deque
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (DequeElement current = head; current != null; current = current.getNext()) {
            sb.append(current.getValue()).append(", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append("}");
        return sb.toString();
    }

    /*
    ====================================================================================================================
                                                Setter and Getter
    ====================================================================================================================
     */
    public int getSize() {
        return size;
    }

    public DequeElement getHead() {
        return head;
    }

    public DequeElement getTail() {
        return tail;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setTail(DequeElement tail) {
        this.tail = tail;
    }

    public void setHead(DequeElement head) {
        this.head = head;
    }
}

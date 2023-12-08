package Klausur_2;

/**
 * Stack = LIFO Principle (Last In First Out)
 * <br>
 * We use first (top Frame) and last (bottom Frame) pointers
 * <ol>
 *     <li>{a} -> first = last = a</li>
 *     <li>push: {a} -> {b, a} -> first = b, last = a, b.next = a</li>
 *     <li>push: {b, a} -> {c, b, a} -> first = c, last = a, c.next.next = a</li>
 *     <li>pop: {c, b, a} -> {b, a} -> first = b, last = a</li>
 * </ol>
 * @author DarkRosaleen
 */
public class Stack {
    private StackElement first;
    private StackElement last;
    private int size;

    /*
    ====================================================================================================================
                                               Constructor and Reset
    ====================================================================================================================
     */
    public Stack(){
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public void reset(){
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    /*
    ====================================================================================================================
                                              Add - Remove Methods
    ====================================================================================================================
     */

    /**
     * Push (add) an element to current Stack
     * @param number int value
     */
    public void push(int number) {
        StackElement element = new StackElement(number);
        if(first == null) {
            first = last = element;
        }
        else {
            element.setNext(first);
            first = element;
        }
        size++;
    }

    /**
     * Pop (remove) an element of current Stack and return its value
     * @return removed element value
     */
    public int pop() {
        if(first == null) {
            return Integer.MIN_VALUE;
        }

        int poppedValue = first.getValue();
        first = first.getNext();
        size--;
        return poppedValue;
    }

    /*
    ====================================================================================================================
                                                 Others Methods
    ====================================================================================================================
     */

    /**
     * Represents current Stack as Integer Array
     * @return new int[]
     */
    public int[] toArray() {
        int[] queueAsArray = new int[size];
        StackElement current = first;
        for(int i = 0; current != null; current = current.getNext(), i++) {
            queueAsArray[i] = current.getValue();
        }
        return queueAsArray;
    }

    /**
     * Simple check if current Stack is empty
     * @return true/ false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /*
    ====================================================================================================================
                                                Setter and Getter
    ====================================================================================================================
     */
    public StackElement getFirst() {
        return first;
    }

    public StackElement getLast() {
        return last;
    }

    public int getSize() {
        return size;
    }

    public void setFirst(StackElement first) {
        this.first = first;
    }

    public void setLast(StackElement last) {
        this.last = last;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

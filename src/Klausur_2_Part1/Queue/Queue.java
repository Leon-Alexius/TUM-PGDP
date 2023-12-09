package Klausur_2_Part1.Queue;

/**
 * Queue = FIFO Principle (First In - First Out)
 * @author DarkRosaleen
 */
public class Queue {
    private QueueElement first;
    private QueueElement last;
    private int size;

    /*
    ====================================================================================================================
                                               Constructor and Reset
    ====================================================================================================================
     */
    public Queue(){
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
     * Push (add) an element to current Queue
     * @param number int value
     */
    public void push(int number) {
        QueueElement element = new QueueElement(number);
        if(first == null) {
            first = last = element;
        } else {
            last.setNext(element);
            last = element;
        }
        size++;
    }

    /**
     * Pop (remove) an element of current Queue and return its value
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
     * Get an Element's index (first occurrence) with current "first" = index 0
     * @param value value of the Element
     * @return index or -1
     */
    public int getElementIndex(int value){
        int index = 0;
        QueueElement currentElement = first;
        while(currentElement != null){
            if(currentElement.getValue() == value){
                return index;
            }
            index++;
            currentElement = currentElement.getNext();
        }

        return -1;
    }

    /**
     * Checks if a value exists inside current Queue
     * @param value value to be found
     * @return true/ false
     */
    public boolean isValueExist(int value){
        return getElementIndex(value) != -1;
    }

    /**
     * Represents current Queue as Integer Array
     * @return new int[]
     */
    public int[] toArray() {
        int[] queueAsArray = new int[size];
        QueueElement current = first;
        for(int i = 0; current != null; current = current.getNext(), i++) {
            queueAsArray[i] = current.getValue();
        }
        return queueAsArray;
    }

    /*
    ====================================================================================================================
                                                Setter and Getter
    ====================================================================================================================
     */
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}

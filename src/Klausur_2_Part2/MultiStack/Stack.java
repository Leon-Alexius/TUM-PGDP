package Klausur_2_Part2.MultiStack;

public class Stack {
    // normal Stack attributes
    private StackElement first;
    private StackElement last;
    private int size;

    // extended Stack attributes
    private final int capacity;
    private Stack nextStack;

    /*
    ====================================================================================================================
                                                    Constructor
    ====================================================================================================================
     */
    public Stack(int capacity){
        this.first = null;
        this.last = null;
        this.size = 0;

        this.capacity = capacity;
        this.nextStack = null;
    }

    /*
    ====================================================================================================================
                                              Add - Remove Methods
    ====================================================================================================================
     */

    public boolean push(int number) {
        // check if current stack is full
        if(this.isFull()){
            return false;
        }

        // if current stack is not full, then add
        StackElement element = new StackElement(number);
        if(first == null) {
            first = last = element;
        }
        else {
            element.setNext(first);
            first = element;
        }
        size++;

        return true;
    }

    public int pop() {
        // check if current Stack is empty
        if(isEmpty()) {
            return Integer.MIN_VALUE;
        }

        // if not empty, then remove element
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
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int getTopValue(){
        // kinda useless since empty stack will be removed from chain anyway
        if(first != null){
            return first.getValue();
        }
        return Integer.MIN_VALUE;
    }

    @Override
    public String toString(){
        StringBuilder output = new StringBuilder();
        StackElement currentStackElement = first;

        output.append("{");

        do {
            output.append(currentStackElement.getValue());
            currentStackElement = currentStackElement.getNext();

            // case when there is element is only one element
            if(currentStackElement == null){
                break;
            }
            output.append(", ");
        } while (currentStackElement.getNext() != null);

        // case when there is element is only one element
        if(currentStackElement != null){
            output.append(currentStackElement.getValue());
        }
        output.append("}");

        return String.valueOf(output);
    }

    /*
    ====================================================================================================================
                                                Setter and Getter
    ====================================================================================================================
     */
    public Stack getNextStack() {
        return nextStack;
    }

    public void setNextStack(Stack nextStack) {
        this.nextStack = nextStack;
    }

    public int getCapacity() {
        return capacity;
    }
}

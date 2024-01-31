package Klausur_3.AboutThreads.MultiStackThreadSafe;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Stack {
    // Stack attributes
    private StackElement elementPointer;
    private int size;

    // MultiStack attributes
    private final int capacity;
    private Stack nextStack;
    private final ReentrantReadWriteLock readWriteLock; // Thread-Safe Lock (must be same as MultiStack)

    /**
     * Default Constructor
     */
    protected Stack(int capacity, ReentrantReadWriteLock readWriteLock){
        this.elementPointer = null; // default value of stack
        this.size = 0;

        this.capacity = capacity;
        this.nextStack = null;
        this.readWriteLock = readWriteLock;
    }

    /**
     * Attempts to push a value to this stack
     * @return true if successful
     */
    public boolean push(int number) {
        readWriteLock.writeLock().lock();
        try{
            // check if current stack is full
            if(this.isFull()){
                return false;
            }

            // if current stack is not full, then add
            StackElement element = new StackElement(number, readWriteLock);
            if (elementPointer != null) {
                element.setNext(elementPointer);
            }
            elementPointer = element;
            size++;

            return true; // return confirmation
        }
        finally {
            readWriteLock.writeLock().unlock();
        }
    }

    /**
     * Attempts to remove the current top element pointed by the elementPointer
     */
    public int pop() {
        readWriteLock.writeLock().lock();
        try {
            // check if current Stack is empty
            if (isEmpty()) {
                return Integer.MIN_VALUE;
            }

            // if not empty, then remove element
            int poppedValue = elementPointer.getValue();
            elementPointer = elementPointer.getNext();
            size--;
            return poppedValue;
        }
        finally {
            readWriteLock.writeLock().unlock();
        }
    }

    /**
     * Get the top element pointed by the elementPointer without removing it from the stack
     */
    public int getTopValue(){
        readWriteLock.readLock().lock();
        try{
            if(elementPointer != null){
                return elementPointer.getValue();
            }
            return Integer.MIN_VALUE;
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    /**
     * Check if a value is in current Stack
     */
    public boolean exist(int value){
        readWriteLock.readLock().lock();
        try{
            if(isEmpty()){
                return false; // if empty, then false
            }

            // start check from top-most element
            StackElement currentElement = elementPointer;
            do{
                if(currentElement.getValue() == value){
                    return true;
                }
                currentElement = currentElement.getNext();
            } while(currentElement != null);

            return false;
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    /**
     * using readLock()
     */
    public boolean isEmpty() {
        readWriteLock.readLock().lock();
        try{
            return size == 0;
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    /**
     * using readLock()
     */
    public boolean isFull() {
        readWriteLock.readLock().lock();
        try{
            return size == capacity;
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    /**
     * using readLock()
     */
    public Stack getNextStack() {
        readWriteLock.readLock().lock();
        try{
            return nextStack;
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    /**
     * using writeLock()
     */
    public void setNextStack(Stack nextStack) {
        readWriteLock.writeLock().lock();
        try{
            this.nextStack = nextStack;
        }
        finally {
            readWriteLock.writeLock().unlock();
        }
    }

    /**
     * using readLock()
     */
    public int getCapacity() {
        readWriteLock.readLock().lock();
        try{
            return capacity;
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    /**
     * using readLock()
     */
    public int getSize() {
        readWriteLock.readLock().lock();
        try{
            return size;
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    /**
     * using readLock()
     */
    @Override
    public String toString(){
        readWriteLock.readLock().lock();
        try{
            StringBuilder output = new StringBuilder();
            StackElement currentStackElement = elementPointer;

            output.append("{");

            do {
                // case when there is element is no element
                if(currentStackElement == null){
                    break;
                }

                output.append(currentStackElement.getValue());
                currentStackElement = currentStackElement.getNext();

                // case when there is no more element
                if(currentStackElement == null){
                    break;
                }

                output.append(", ");
            } while (currentStackElement.getNext() != null);

            output.append("}");

            return String.valueOf(output);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }
}

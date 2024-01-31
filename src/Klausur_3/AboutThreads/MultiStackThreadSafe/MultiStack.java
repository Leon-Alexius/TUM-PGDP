package Klausur_3.AboutThreads.MultiStackThreadSafe;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MultiStack {
    private final Stack initialStack;
    private final ReentrantReadWriteLock readWriteLock; // Thread-Safe Lock

    /**
     * default constructor
     */
    protected MultiStack(){
        readWriteLock = new ReentrantReadWriteLock();
        // always start with a stack of capacity = 1
        initialStack = new Stack(1, readWriteLock);
    }

    /**
     * push a value to stack-chain, if all stack is full, make new Stack with 2*capacity
     */
    public void push(int val) {
        readWriteLock.writeLock().lock();
        try{
            // start push
            Stack currentStack = initialStack;
            boolean success = currentStack.push(val);

            // if not successful, iterate through the stacks (add new Stack if end reached)
            while(!success){
                if(currentStack.getNextStack() == null){
                    Stack newStack = new Stack(currentStack.getCapacity() * 2, readWriteLock); // new capacity = 2 * old
                    currentStack.setNextStack(newStack);
                }
                currentStack = currentStack.getNextStack();
                success = currentStack.push(val);
            }
        }
        finally {
            readWriteLock.writeLock().unlock();
        }
    }

    /**
     * remove top-element and remove it
     */
    public int pop(){
        readWriteLock.writeLock().lock();
        try {
            // check if current MultiStack is empty
            if(initialStack.isEmpty()){
                return Integer.MIN_VALUE;
            }

            // start pop
            Stack currentStack = initialStack;
            Stack previousStack = null;
            while(currentStack.getNextStack() != null){
                previousStack = currentStack;
                currentStack = currentStack.getNextStack();
            }
            int popValue = currentStack.pop();

            // check if stack is empty after pop, if yes, then unchain the stack
            // We never remove initialStack even if it is empty!
            if(currentStack.isEmpty() && previousStack != null){
                previousStack.setNextStack(null);
            }
            return popValue;
        }
        finally {
            readWriteLock.writeLock().unlock();
        }
    }

    /**
     * Get top-element without removing it
     */
    public int getTopStackValue(){
        readWriteLock.readLock().lock();
        try {
            Stack currentStack = initialStack;

            while (currentStack.getNextStack() != null){
                currentStack = currentStack.getNextStack();
            }

            return currentStack.getTopValue();
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    /**
     * Get total size of current MultiStack
     */
    public int getSize(){
        readWriteLock.readLock().lock();
        try {
            int totalSize = 0;
            Stack currentStack = initialStack;
            totalSize += currentStack.getSize();

            while (currentStack.getNextStack() != null){
                currentStack = currentStack.getNextStack();
                totalSize += currentStack.getSize();
            }

            return totalSize;
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    /**
     * Check whether a value exist in current MultiStack
     */
    public boolean exist(int value){
        readWriteLock.readLock().lock();
        try {
            // start check
            Stack currentStack = initialStack;
            boolean found;

            do{
                found = currentStack.exist(value);
                if(found){
                    return found; // break if true
                }
                currentStack = currentStack.getNextStack();
            } while(currentStack != null);

            return found;
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }
}

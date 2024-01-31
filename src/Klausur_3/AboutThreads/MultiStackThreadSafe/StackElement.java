package Klausur_3.AboutThreads.MultiStackThreadSafe;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * A Thread-Safe StackElement
 */
public class StackElement {
    private int value;
    private StackElement next;
    private final ReentrantReadWriteLock readWriteLock; // must be same as Stack

    /**
     * Constructor 1 (<code>StackElement next = null</code>)
     */
    protected StackElement(int value, ReentrantReadWriteLock readWriteLock) {
        this.value = value;
        this.next = null;
        this.readWriteLock = readWriteLock;
    }

    /**
     * Constructor 2
     */
    protected StackElement(int value, StackElement next, ReentrantReadWriteLock readWriteLock) {
        this.value = value;
        this.next = next;
        this.readWriteLock = readWriteLock;
    }

    /**
     * using readLock()
     */
    @Override
    public String toString() {
        readWriteLock.readLock().lock();
        try{
            return String.valueOf(value);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    /**
     * using readLock()
     */
    public int getValue() {
        readWriteLock.readLock().lock();
        try{
            return value;
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    /**
     * using readLock()
     */
    public StackElement getNext() {
        readWriteLock.readLock().lock();
        try{
            return next;
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    /**
     * using writeLock()
     */
    public void setValue(int value) {
        readWriteLock.writeLock().lock();
        try{
            this.value = value;
        }
        finally {
            readWriteLock.writeLock().unlock();
        }
    }

    /**
     * using writeLock()
     */
    public void setNext(StackElement next) {
        readWriteLock.writeLock().lock();
        try{
            this.next = next;
        }
        finally {
            readWriteLock.writeLock().unlock();
        }
    }
}

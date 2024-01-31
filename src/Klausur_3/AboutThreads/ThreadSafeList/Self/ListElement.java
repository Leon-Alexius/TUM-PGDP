package Klausur_3.AboutThreads.ThreadSafeList.Self;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ListElement<T> {
    private T value;
    private ListElement<T> next;
    private final ReentrantReadWriteLock readWriteLock; // must be same as List

    public ListElement(T value, ListElement<T> next, ReentrantReadWriteLock readWriteLock){
        this.value = value;
        this.next = next;
        this.readWriteLock = readWriteLock;
    }

    public ListElement(T value, ReentrantReadWriteLock readWriteLock){
        this.value = value;
        this.next = null;
        this.readWriteLock = readWriteLock;
    }

    @Override
    public String toString() {
        readWriteLock.readLock().lock();
        try {
            return value.toString();
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    /*
    ====================================================================================================================
                                                Getter and Setter
    ====================================================================================================================
    */
    public T getValue() {
        readWriteLock.readLock().lock();
        try {
            return value;
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public ListElement<T> getNext() {
        readWriteLock.readLock().lock();
        try {
            return next;
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void setValue(T value) {
        readWriteLock.writeLock().lock();
        try {
            this.value = value;
        }
        finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void setNext(ListElement<T> next) {
        readWriteLock.writeLock().lock();
        try {
            this.next = next;
        }
        finally {
            readWriteLock.writeLock().unlock();
        }
    }
}

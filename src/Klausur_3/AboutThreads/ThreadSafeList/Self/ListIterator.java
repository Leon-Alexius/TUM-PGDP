package Klausur_3.AboutThreads.ThreadSafeList.Self;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Must always override hasNext() and next()
 */
public class ListIterator<T> implements Iterator<T> {
    private ListElement<T> currentElement;
    private final ReentrantReadWriteLock readWriteLock;

    protected ListIterator(List<T> list, ReentrantReadWriteLock lock){
        lock.readLock().lock();
        try {
            this.readWriteLock = lock;
            // Create a deep copy of the list
            List<T> copy = new List<>();
            for (T item : list) {
                copy.add(item);
            }
            this.currentElement = copy.getHead();
        }
        finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public boolean hasNext() {
        readWriteLock.readLock().lock();
        try {
            return currentElement != null;
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public T next() {
        readWriteLock.writeLock().lock();
        try {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T currentValue = currentElement.getValue();
            currentElement = currentElement.getNext();
            return currentValue;
        }
        finally {
            readWriteLock.writeLock().unlock();
        }
    }
}

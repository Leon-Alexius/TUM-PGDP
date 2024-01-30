package Klausur_3.AboutThreads.ThreadSafeList.Self;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * Must always override hasNext() and next()
 */
public class ListIterator<T> implements Iterator<T> {
    private ListElement<T> currentElement;

    protected ListIterator(List<T> list, ReadWriteLock lock){
        lock.readLock().lock();
        try {
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
        return currentElement != null;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        T currentValue = currentElement.getValue();
        currentElement = currentElement.getNext();
        return currentValue;
    }
}

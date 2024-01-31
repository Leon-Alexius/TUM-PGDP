package Klausur_3.AboutThreads.ThreadSafeList.Self;

import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class List<T> implements Iterable<T> {
    private ListElement<T> head;
    private ListElement<T> tail;
    private int size;
    private final ReentrantReadWriteLock lock; // using ReentrantReadWriteLock

    /*
    ====================================================================================================================
                                            Constructor and Iterator
    ====================================================================================================================
     */
    public List(){
        this.head = null;
        this.tail = null;
        this.size = 0;
        lock = new ReentrantReadWriteLock();
    }

    /**
     * snapshot iterator
     */
    @Override
    public Iterator<T> iterator() {
        lock.readLock().lock();
        try {
            return new ListIterator<>(this, lock);
        }
        finally {
            lock.readLock().unlock();
        }
    }

    /*
    ====================================================================================================================
                                              Add - Remove Methods
    ====================================================================================================================
     */

    /**
     * Standard add method
     */
    protected void add(T value) {
        lock.writeLock().lock();
        try{
            if (head == null) {
                head = new ListElement<>(value, lock);
                tail = head;
            }
            else {
                tail.setNext(new ListElement<>(value, lock));
                tail = tail.getNext();
            }
            size++;
        }
        finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Standard remove method
     */
    protected T remove(){
        lock.writeLock().lock();
        try {
            if (head == null) {
                return null;
            }
            else {
                return removeAt(size - 1);
            }
        }
        finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * remove an Element at given Index and returns its Value
     */
    protected T removeAt(int index){
        lock.writeLock().lock();
        try {
            if (head == null || index >= size || index < 0) {
                return null;
            }

            ListElement<T> ElementToBeRemoved = getListElementAtIndex(index);
            if(size == 1){
                // remove single element
                head = tail = null;
                size--;
            }
            else if(index == 0){
                // remove head
                head = ElementToBeRemoved.getNext();
                ElementToBeRemoved.setNext(null);
                size--;
            }
            else if(index == size -1){
                // remove tail
                ListElement<T> prevOf_ElementToBeRemoved = getListElementAtIndex(index - 1);
                tail = prevOf_ElementToBeRemoved;
                prevOf_ElementToBeRemoved.setNext(null);
                size--;
            }
            else {
                // remove between head and tail
                ListElement<T> nextOf_ElementToBeRemoved = ElementToBeRemoved.getNext();
                ListElement<T> prevOf_ElementToBeRemoved = getListElementAtIndex(index - 1);
                prevOf_ElementToBeRemoved.setNext(nextOf_ElementToBeRemoved);
                size--;
            }

            return ElementToBeRemoved.getValue();
        }
        finally {
            lock.writeLock().unlock();
        }
    }

    /*
    ====================================================================================================================
                                                Setter and Getter
    ====================================================================================================================
     */

    @Override
    public String toString() {
        lock.readLock().lock();
        try{
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            if(head != null){
                stringBuilder.append(head.getValue());

                if(head.getNext() != null){
                    for (ListElement<T> current = head.getNext(); current != null; current = current.getNext()) {
                        stringBuilder.append(", ");
                        stringBuilder.append(current.getValue());
                    }
                }
            }
            stringBuilder.append("]");
            return stringBuilder.toString();
        }
        finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Get the element at specified index
     */
    protected ListElement<T> getListElementAtIndex(int index){
        lock.readLock().lock();
        try{
            if(index >= size){
                throw new RuntimeException("Index out of bound, can't be equal/ greater than size");
            }

            ListElement<T> currentElement = head;
            while(index > 0){
                currentElement = currentElement.getNext();
                index--;
            }

            return currentElement;
        }
        finally {
            lock.readLock().unlock();
        }
    }

    public ListElement<T> getHead() {
        lock.readLock().lock();
        try {
            return head;
        }
        finally {
            lock.readLock().unlock();
        }
    }
}

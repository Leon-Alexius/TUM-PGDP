package Klausur_2_Part2.ListEx;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Must always override hasNext() and next()
 * @param <DataType>
 */
public class ListIterator<DataType> implements Iterator<DataType> {
    private ListElement<DataType> currentElement;

    public ListIterator(ListElement<DataType> firstElement){
        this.currentElement = firstElement;
    }

    /**
     * Check if current Element has next Element
     * @return true/ false
     */
    @Override
    public boolean hasNext() {
        return currentElement != null;
    }

    /**
     * Return current value and move the Element pointer to next element
     * @return current Element Value
     */
    @Override
    public DataType next() {
        // error handling based on JavaDocs
        // https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Iterator.html
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        DataType currentValue = currentElement.getValue();
        currentElement = currentElement.getNext();
        return currentValue;
    }
}

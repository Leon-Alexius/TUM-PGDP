package Klausur_2_Part2.ListEx;

import java.util.Iterator;

public class List2DIterator<DataType> implements Iterator<DataType> {
    private final Iterator<List<DataType>> list2DIterator;
    private Iterator<DataType> innerListIterator;

    public List2DIterator(List<List<DataType>> list2D){

        // Iterator of 2D List, starting at first inner List
        this.list2DIterator = new ListIterator<>(list2D.getHead());

        // Iterator of an inner List Starting at Head Element, skips empty InnerList
        while (list2DIterator.hasNext()) {
            innerListIterator = new ListIterator<>(list2DIterator.next().getHead());
            if (innerListIterator.hasNext()) {
                break;
            }
        }
    }

    @Override
    public boolean hasNext() {
        // check if an InnerList has an element and if the element hasNext
        return innerListIterator != null && innerListIterator.hasNext();
    }

    @Override
    public DataType next() {
        DataType currentElementValue = innerListIterator.next();

        // if we have reached last element of an InnerArray but still have other InnerArray, we move the innerIterator
        while(!innerListIterator.hasNext() && list2DIterator.hasNext()){
            innerListIterator = new ListIterator<>(list2DIterator.next().getHead());
        }

        return currentElementValue;
    }
}

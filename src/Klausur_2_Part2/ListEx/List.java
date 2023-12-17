package Klausur_2_Part2.ListEx;

import java.util.Iterator;

public class List<DataType> implements Iterable<DataType> {
    private ListElement<DataType> head;
    private ListElement<DataType> tail;
    private int size;

    /*
    ====================================================================================================================
                                               Constructor and Reset
    ====================================================================================================================
     */
    public List(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public Iterator<DataType> iterator() {
        return new ListIterator<>(head);
    }

    public void reset(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /*
    ====================================================================================================================
                                              Add - Remove Methods
    ====================================================================================================================
     */
    public void add(DataType value) {
        if (head == null) {
            head = new ListElement<>(value);
            tail = head;
        }
        else {
            tail.setNext(new ListElement<>(value));
            tail = tail.getNext();
        }
        size++;
    }


    /*
    ====================================================================================================================
                                                Setter and Getter
    ====================================================================================================================
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        if(head != null){
            stringBuilder.append(head.getValue());

            if(head.getNext() != null){
                for (ListElement<DataType> current = head.getNext(); current != null; current = current.getNext()) {
                    stringBuilder.append(", ");
                    stringBuilder.append(current.getValue());
                }
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public ListElement<DataType> getHead() {
        return head;
    }

    public ListElement<DataType> getTail() {
        return tail;
    }

    public int getSize() {
        return size;
    }

    public void setHead(ListElement<DataType> head) {
        this.head = head;
    }

    public void setTail(ListElement<DataType> tail) {
        this.tail = tail;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

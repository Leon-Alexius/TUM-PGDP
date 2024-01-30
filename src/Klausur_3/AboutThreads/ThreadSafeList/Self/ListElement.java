package Klausur_3.AboutThreads.ThreadSafeList.Self;

public class ListElement<T> {
    private T value;
    private ListElement<T> next;

    public ListElement(T value, ListElement<T> next){
        this.value = value;
        this.next = next;
    }

    public ListElement(T value){
        this.value = value;
        this.next = null;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    /*
    ====================================================================================================================
                                                Getter and Setter
    ====================================================================================================================
    */
    public T getValue() {
        return value;
    }

    public ListElement<T> getNext() {
        return next;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setNext(ListElement<T> next) {
        this.next = next;
    }
}

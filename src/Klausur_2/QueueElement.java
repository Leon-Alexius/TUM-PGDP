package Klausur_2;

public class QueueElement {
    private int value;
    private QueueElement next;

    /*
    ====================================================================================================================
                                                  Constructor
    ====================================================================================================================
     */
    public QueueElement(int value) {
        this.value = value;
        this.next = null;
    }

    public QueueElement(int value, QueueElement next){
        this.value = value;
        this.next = next;
    }

    /*
    ====================================================================================================================
                                                Setter and Getter
    ====================================================================================================================
     */
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public QueueElement getNext() {
        return next;
    }

    public void setNext(QueueElement next) {
        this.next = next;
    }
}

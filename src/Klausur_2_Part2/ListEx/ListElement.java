package Klausur_2_Part2.ListEx;

public class ListElement<DataType> {
    private DataType value;
    private ListElement<DataType> next;

    /*
    ====================================================================================================================
                                                  Constructor
    ====================================================================================================================
     */
    public ListElement(DataType value, ListElement<DataType> next){
        this.value = value;
        this.next = next;
    }

    public ListElement(DataType value){
        this.value = value;
        this.next = null;
    }

    /*
    ====================================================================================================================
                                                Other Methods
    ====================================================================================================================
     */

    @Override
    public String toString() {
        return value.toString();
    }

    /*
    ====================================================================================================================
                                                    Setter and Getter
    ====================================================================================================================
    */
    public DataType getValue() {
        return value;
    }

    public ListElement<DataType> getNext() {
        return next;
    }

    public void setValue(DataType value) {
        this.value = value;
    }

    public void setNext(ListElement<DataType> next) {
        this.next = next;
    }
}

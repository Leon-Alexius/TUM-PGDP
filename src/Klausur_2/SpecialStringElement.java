package Klausur_2;

public class SpecialStringElement {
    private char[] data;
    private SpecialStringElement next;

    /*
    ====================================================================================================================
                                                    Constructor
    ====================================================================================================================
     */

    public SpecialStringElement(char[] data) {
        this.data = data;
    }

    /*
    ====================================================================================================================
                                                  Setter and Getter
    ====================================================================================================================
     */

    public char[] getData() {
        return data;
    }

    public SpecialStringElement getNext() {
        return next;
    }

    public void setData(char[] data) {
        this.data = data;
    }

    public void setNext(SpecialStringElement next) {
        this.next = next;
    }
}

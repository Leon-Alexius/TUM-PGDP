package Klausur_2;

public class SpecialString {
    private SpecialStringElement first;

    /*
    ====================================================================================================================
                                                    Constructor
    ====================================================================================================================
     */

    /**
     * A String is a Chained List of Character Arrays: <code>{{'a', 'b'}, {'c', 'd', 'e'}}</code>
     * @param data char[], asserted as not <code>null</code>
     */
    public SpecialString(char[] data) {
        // Du kannst davon ausgehen, dass der Parameter data nicht null ist
        assert data != null : "Error: data is null";
        concat(data);
    }

    /*
    ====================================================================================================================
                                              Add - Remove Methods
    ====================================================================================================================
     */

    /**
     * Adds char[] to end of current List
     * @param dataToAppend char[]
     */
    public void concat(char[] dataToAppend) {
        // Add to empty List
        if(first == null) {
            first = new SpecialStringElement(dataToAppend);
            return;
        }

        // Add to end of List
        SpecialStringElement currentElement = first;
        while (currentElement.getNext() != null) {
            currentElement = currentElement.getNext();
        }
        currentElement.setNext(new SpecialStringElement(dataToAppend));
    }

    /*
    ====================================================================================================================
                                                Get List Index
    ====================================================================================================================
     */

    /**
     * Get index of a character (first occurrence)
     * @param c character
     * @return index or -1
     */
    public int indexOf(char c) {
        SpecialStringElement currentElement = first;
        int index = 0;
        while (currentElement != null) {
            for (int i = 0; i < currentElement.getData().length; i++) {
                if (currentElement.getData()[i] == c) {
                    return index;
                }
                index++;
            }
            currentElement = currentElement.getNext();
        }
        return -1;
    }

    /**
     * Get index of a character (last occurrence)
     * @param c character
     * @return index or -1
     */
    public int lastIndexOf(char c) {
        SpecialStringElement current = first;
        int lastIndexOfC = -1;
        int i = 0;
        while (current != null) {
            for (int j = 0; j < current.getData().length; j++) {
                if (current.getData()[j] == c) {
                    lastIndexOfC = i;
                }
                i++;
            }
            current = current.getNext();
        }
        return lastIndexOfC;
    }

    /*
    ====================================================================================================================
                                                Other Methods
    ====================================================================================================================
     */

    /**
     * Get the total character count of current List = String.length();
     * @return length
     */
    public int length() {
        int length = 0;
        SpecialStringElement currentElement = first;
        while (currentElement != null) {
            length += currentElement.getData().length;
            currentElement = currentElement.getNext();
        }
        return length;
    }

    /**
     * Using java.util.String.toString(char[] value) to represent current List -> create new char[] array
     * @return new String representation
     */
    @Override
    public String toString() {
        int length = length();
        char[] completeData = new char[length];

        SpecialStringElement currentElement = first;
        int index = 0;
        while (currentElement != null) {
            for (int i = 0; i < currentElement.getData().length; i++) {
                completeData[index++] = currentElement.getData()[i];
            }
            currentElement = currentElement.getNext();
        }

        // Um den zurückzugebenden String zu erzeugen, verwende den Konstruktor String(char[] value).
        return new String(completeData);
    }

    /**
     * Check if a String has same Characters as other String (same length, same order, same element value)
     * @param other other String
     * @return true/ false
     */
    public boolean equals(SpecialString other) {
        if (other == null) {
            return false;
        }
        if (this.length() != other.length()) {
            return false;
        }

        // Guaranteed: same length, not null
        SpecialStringElement currentElement = first;
        SpecialStringElement otherCurrentElement = other.first;
        int otherIndex = 0;

        // start check, iterate through current List
        while (currentElement != null) {
            for (int i = 0; i < currentElement.getData().length; i++, otherIndex++) {
                /*
                it is possible to represent {a, b, c, d, e} as {{a, b, c}, {d, e}}
                Therefore, need to "jump" to next List Element and reset index
                 */
                if (otherIndex >= otherCurrentElement.getData().length) {
                    otherCurrentElement = otherCurrentElement.getNext();
                    otherIndex = 0;
                }

                if (currentElement.getData()[i] != otherCurrentElement.getData()[otherIndex]) {
                    return false;
                }
            }
            currentElement = currentElement.getNext();
        }
        return true;
    }

    /*
    ====================================================================================================================
                                                  Setter and Getter
    ====================================================================================================================
     */

    public SpecialStringElement getFirst() {
        return first;
    }
}

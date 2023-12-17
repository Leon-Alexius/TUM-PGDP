package Klausur_2_Part2.Tree;

public class TreeElement<DataType extends Comparable<DataType>> {
    private DataType value;
    private TreeElement<DataType> left;
    private TreeElement<DataType> right;

    /*
    ====================================================================================================================
                                                  Constructor
    ====================================================================================================================
     */
    public TreeElement(DataType value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    /*
    ====================================================================================================================
                                                    ADD Methods
    ====================================================================================================================
     */

    /**
     * Given a newValue less/equals than current value, then insert to leftSubTree, otherwise insert to rightSubTree
     * @param newValue new Value
     */
    public void insert(DataType newValue) {
        if (newValue.compareTo(this.value) <= 0) {
            // if left is empty, then new Element, if not, then insert to the existing Element
            if (left == null) {
                left = new TreeElement<>(newValue);
            }
            else {
                left.insert(newValue);
            }
        }
        else {
            // if right is empty, then new Element, if not, then insert to the existing Element
            if (right == null) {
                right = new TreeElement<>(newValue);
            }
            else {
                right.insert(newValue);
            }
        }
    }

    /*
    ====================================================================================================================
                                                    Setter and Getter
    ====================================================================================================================
    */
    public void setValue(DataType value) {
        this.value = value;
    }

    public void setLeft(TreeElement<DataType> left) {
        this.left = left;
    }

    public void setRight(TreeElement<DataType> right) {
        this.right = right;
    }

    public DataType getValue() {
        return value;
    }

    public TreeElement<DataType> getLeft() {
        return left;
    }

    public TreeElement<DataType> getRight() {
        return right;
    }
}

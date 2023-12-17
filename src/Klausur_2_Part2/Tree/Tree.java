package Klausur_2_Part2.Tree;

import java.util.Iterator;
import java.util.Objects;

public class Tree<DataType extends Comparable<DataType>> implements Iterable<DataType> {
    private TreeElement<DataType> treeRoot;
    private final Order order;

    /*
    ====================================================================================================================
                                                  Constructor
    ====================================================================================================================
     */
    public Tree(Order order){
        this.treeRoot = null;
        this.order = order;
    }

    @Override
    public Iterator<DataType> iterator() {
        return new TreeIterator<>(treeRoot, order);
    }

    /*
    ====================================================================================================================
                                              Add - Remove Methods
    ====================================================================================================================
     */
    public void insert(DataType value) {
        Objects.requireNonNull(value);
        if (treeRoot == null) {
            treeRoot = new TreeElement<>(value);
        }
        else {
            treeRoot.insert(value);
        }
    }

    /*
    ====================================================================================================================
                                                    Setter and Getter
    ====================================================================================================================
    */

    public TreeElement<DataType> getTreeRoot() {
        return treeRoot;
    }

    public Order getOrder() {
        return order;
    }
}

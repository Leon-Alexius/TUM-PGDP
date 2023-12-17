package Klausur_2_Part2.Tree;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of TreeIterator
 * <ul>
 *     <li> In-Order = Left - Root - Right </li>
 *     <li> Pre-Order = Root - Left - Right </li>
 *     <li> Post-Order = Left - Right - Root </li>
 * </ul>
 * @param <DataType>
 * @author Dark-Rosaleen
 */
public class TreeIterator<DataType extends Comparable<DataType>> implements Iterator<DataType> {
    TreeElement<DataType> currentElement;
    TreeElement<DataType> leftElement;
    TreeElement<DataType> rightElement;
    TreeIterator<DataType> leftIterator;
    TreeIterator<DataType> rightIterator;
    boolean currentElementValueHasBeenYielded = false;
    private final Order order;

    public TreeIterator(TreeElement<DataType> treeRoot, Order order){
        this.currentElement = treeRoot;
        this.order = order;
        if(treeRoot != null){
            this.leftElement = treeRoot.getLeft();
            this.rightElement = treeRoot.getRight();

            // Left and Right SubTree Iterator
            this.leftIterator = new TreeIterator<>(leftElement, order);
            this.rightIterator = new TreeIterator<>(rightElement, order);
        }
    }

    /**
     * Check hasNext() based on Order
     * <ul>
     *     <li>
     *         <b>In-Order</b>:<br>
     *         Return true if current element is not empty AND (has left element OR self value has not been used OR has right element)
     *     </li>
     * </ul>
     *
     * @return boolean
     */
    @Override
    public boolean hasNext() {
        if (currentElement == null) {
            return false;
        }

        return switch (order) {
            case PRE ->
                // check Root - Left - Right (Pre-Order)
                    !currentElementValueHasBeenYielded || leftIterator.hasNext() || rightIterator.hasNext();
            case IN ->
                // check Left - Root - Right (In-Order)
                    leftIterator.hasNext() || !currentElementValueHasBeenYielded || rightIterator.hasNext();
            case POST ->
                // check Left - Right - Root (Post-Order)
                    leftIterator.hasNext() || rightIterator.hasNext() || !currentElementValueHasBeenYielded;
        };
    }

    /**
     * Implementation of next() based on Order
     * @return value based on Order
     */
    @Override
    public DataType next() {
        // check if current Iterator has next
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }

        switch (order) {
            case IN -> {
                // Return based on In-Order = Left - Root - Right
                if (leftIterator.hasNext()) {
                    return leftIterator.next();
                }
                else if (!currentElementValueHasBeenYielded) {
                    currentElementValueHasBeenYielded = true;
                    return currentElement.getValue();
                }
                else {
                    return rightIterator.next();
                }
            }
            case PRE -> {
                // Return based on Pre-Order = Root - Left - Right
                if (!currentElementValueHasBeenYielded) {
                    currentElementValueHasBeenYielded = true;
                    return currentElement.getValue();
                }
                else if (leftIterator.hasNext()) {
                    return leftIterator.next();
                }
                else {
                    return rightIterator.next();
                }
            }
            case POST -> {
                // Return based on Post-Order = Left - Right - Root
                if (leftIterator.hasNext()) {
                    return leftIterator.next();
                }
                else if (rightIterator.hasNext()) {
                    return rightIterator.next();
                }
                else {
                    currentElementValueHasBeenYielded = true;
                    return currentElement.getValue();
                }
            }
            default -> {
                return null;
            }
        }
    }
}

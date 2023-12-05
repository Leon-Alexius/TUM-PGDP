import Klausur_2.DoubleChainedList;

/**
 * For testing purposes only/**
 * @author Drago - Lie Leon Alexius (DarkRosaleen)
 */
public class Main {
    public static void main(String[] args) {
        DoubleChainedList doubleChainedList = new DoubleChainedList(); // [0, 1, 2, 3, 4, 5]
        doubleChainedList.add_Recursive(-7);
        doubleChainedList.add_Recursive(5);
        doubleChainedList.add_Recursive(9);


        System.out.println(doubleChainedList.getSize());

        doubleChainedList.insertAt_Recursive(Integer.MAX_VALUE, 0);
    }
}

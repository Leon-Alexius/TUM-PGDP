import Klausur_2.DoubleChainedList;

/**
 * For testing purposes only/**
 * @author Drago - Lie Leon Alexius (DarkRosaleen)
 */
public class Main {
    public static void main(String[] args) {
        DoubleChainedList doubleChainedList = new DoubleChainedList(); // [-7, 3, 5]
        doubleChainedList.add_Recursive(-7);
        doubleChainedList.add_Recursive(3);
        doubleChainedList.add_Recursive(5);

        DoubleChainedList doubleChainedList1 = new DoubleChainedList(); // [-8, 0, 9]
        doubleChainedList1.add_Recursive(-8);
        doubleChainedList1.add_Recursive(0);
        doubleChainedList1.add_Recursive(9);

        DoubleChainedList.zip(doubleChainedList, doubleChainedList1); // [-7, -8, 3, 0, 5, 9]

        doubleChainedList.removeAt_Recursive(0);
        System.out.println(doubleChainedList.toString());
    }
}

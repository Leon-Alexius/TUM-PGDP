import Klausur_2_Part1.DoubleChainedList;
import Klausur_2_Part1.DoubleChainedListElement;
import Klausur_2_Part2.MemoryRing;

/**
 * For testing purposes only/**
 * @author Drago - Lie Leon Alexius (DarkRosaleen)
 */
public class Main {
    public static void main(String[] args) {
        MemoryRing memoryRing = new MemoryRing(4);
        memoryRing.addElement(1234);
        memoryRing.addElement(-4321);
        System.out.println(memoryRing);

        memoryRing.removeElement();
        memoryRing.removeElement();
        memoryRing.removeElement();
        System.out.println(memoryRing);
    }
}

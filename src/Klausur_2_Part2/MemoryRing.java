package Klausur_2_Part2;

import java.util.Arrays;

/**
 * The idea is to use an Array as a ring, having the "in" and "out" pointer
 * @author PGDP: W06H03 - PUM Server Upgrade
 */
public class MemoryRing {
    private int[] memory;
    private int inPointer;
    private int outPointer;
    private int usedMemoryCount;

    /*
    ====================================================================================================================
                                               Constructor and Reset
    ====================================================================================================================
     */
    public MemoryRing(int capacity) {
        this.memory = new int[capacity];
        this.inPointer = 0;
        this.outPointer = 0;
        this.usedMemoryCount = 0;
    }

    public void reset(){
        this.memory = new int[memory.length];
        this.inPointer = this.outPointer = this.usedMemoryCount = 0;
    }

    /*
    ====================================================================================================================
                                              Add - Remove Methods
    ====================================================================================================================
     */

    /**
     * adds an Element to the Memory Ring, do nothing if Memory is full
     * @param value new Value
     * @return true if successful, otherwise, false
     */
    public boolean addElement(int value) {
        if (isFull()) {
            return false;
        }

        memory[inPointer++] = value;
        inPointer = inPointer % memory.length; // make sure the index is in range 0 ~ (length - 1)
        usedMemoryCount++;
        return true;
    }

    /**
     * Remove the Element pointed by the outPointer, if Memory is empty, do nothing
     * @return value of removed Element or Integer.MIN_VALUE
     */
    public int removeElement() {
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        }

        int value = memory[outPointer++];
        outPointer = outPointer % memory.length; // make sure the index is in range 0 ~ (length - 1)
        usedMemoryCount--;
        return value;
    }


    /*
    ====================================================================================================================
                                                 Others Methods
    ====================================================================================================================
     */

    /**
     * Check if current Memory Ring is empty
     * @return boolean
     */
    public boolean isEmpty() {
        return usedMemoryCount == 0;
    }

    /**
     * Check if current Memory Ring is full
     * @return boolean
     */
    public boolean isFull() {
        return usedMemoryCount == memory.length;
    }

    /**
     * String representation of current Memory Ring
     * <br>
     * <br>
     * <code>RingBuffer := { capacity = 4, out = 0, in = 2, Elements stored = 2, memory = [1234, -4321, 0, 0], buffer = [1234, -4321] }</code>
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RingBuffer := { capacity = ").append(memory.length).append(", out = ").append(outPointer).append(", in = ")
                .append(inPointer).append(", Elements stored = ").append(usedMemoryCount).append(", memory = ").append(Arrays.toString(memory))
                .append(", buffer = [");
        if (!isEmpty()) {
            assert inPointer >= 0 && inPointer < memory.length: "Fatal Error: inPointer is out of bounds for current memory";
            int i = outPointer;
            do {
                sb.append(memory[i]).append(", ");
                i = (i + 1) % memory.length;
            } while (i != inPointer);
            sb.setLength(sb.length() - 2);
        }
        sb.append("] }");
        return sb.toString();
    }

    /*
    ====================================================================================================================
                                                Setter and Getter
    ====================================================================================================================
     */
    public int[] getMemory() {
        return memory;
    }

    public int getInPointer() {
        return inPointer;
    }

    public int getOutPointer() {
        return outPointer;
    }

    public int getUsedMemoryCount() {
        return usedMemoryCount;
    }

    public void setMemory(int[] memory) {
        this.memory = memory;
    }

    public void setInPointer(int inPointer) {
        this.inPointer = inPointer;
    }

    public void setOutPointer(int outPointer) {
        this.outPointer = outPointer;
    }

    public void setUsedMemoryCount(int usedMemoryCount) {
        this.usedMemoryCount = usedMemoryCount;
    }
}

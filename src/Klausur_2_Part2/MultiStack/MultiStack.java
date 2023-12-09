package Klausur_2_Part2.MultiStack;

public class MultiStack {
    private final Stack initialStack;

    /*
    ====================================================================================================================
                                                    Constructor
    ====================================================================================================================
     */
    public MultiStack() {
        // always start with a stack of capacity = 1
        initialStack = new Stack(1);
    }

    /*
    ====================================================================================================================
                                              Add - Remove Methods
    ====================================================================================================================
     */
    public void push(int val) {
        // start push
        Stack currentStack = initialStack;
        boolean success = currentStack.push(val);

        // if not successful, iterate through the stacks (add new Stack if end reached)
        while(!success){
            if(currentStack.getNextStack() == null){
                Stack newStack = new Stack(currentStack.getCapacity() * 2); // new capacity = 2 * old
                currentStack.setNextStack(newStack);
            }
            currentStack = currentStack.getNextStack();
            success = currentStack.push(val);
        }
    }

    public int pop(){
        // check if current MultiStack is empty
        if(initialStack.isEmpty()){
            return Integer.MIN_VALUE;
        }

        // start pop
        Stack currentStack = initialStack;
        Stack previousStack = null;
        while(currentStack.getNextStack() != null){
            previousStack = currentStack;
            currentStack = currentStack.getNextStack();
        }
        int popValue = currentStack.pop();

        // check if stack is empty after pop, if yes, then unchain the stack
        // We never remove initialStack even if it is empty!
        if(currentStack.isEmpty() && previousStack != null){
            previousStack.setNextStack(null);
        }
        return popValue;
    }

    /*
    ====================================================================================================================
                                                 Others Methods
    ====================================================================================================================
     */
    public int getTopStackValue(){
        Stack currentStack = initialStack;

        while (currentStack.getNextStack() != null){
            currentStack = currentStack.getNextStack();
        }

        return currentStack.getTopValue();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        Stack currentStack = initialStack;

        output.append("{");
        do {
            output.append(currentStack);
            currentStack = currentStack.getNextStack();

            // case when there is element is only one element
            if(currentStack == null){
                break;
            }
            output.append(", ");
        } while (currentStack.getNextStack() != null);

        // case when there is element is only one element
        if(currentStack != null){
            output.append(currentStack);
        }
        output.append("}");

        return String.valueOf(output);
    }
}

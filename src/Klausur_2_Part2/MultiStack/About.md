# About MultiStacks

---

## Concept
<p>
MultiStacks implements Stack, Stack implements StackElement

MultiStacks is simply just a chain of Stack, each having their own Capacity

A MultiStacks object always has a Stack with Capacity of 1 StackElement at first
</p>

---

## push
<p>
the <code>push(int value)</code> method tries to push a new StackElement to current Stack, if current Stack is full, 
then it goes to the next Stack, trying the same thing.

If it reaches the end of the Stack Chain, then it creates a new Stack at the end of the chain, with capacity 2x ("newest") old capacity,
then adds the new StackElement to the new Stack

Example: <code>capacity 1 -> capacity 2 -> capacity 4 -> null</code>
</p>

## pop
<p>
the <code>pop()</code> method simply takes the last Element in Last Stack

if the Stack becomes empty, then we release it from current Stack Chain
</p>

## getTopStackValue
<p>
the <code>getTopStackValue()</code> method returns the Last Element Value in last Stack without removing it (similar to pop)
</p>
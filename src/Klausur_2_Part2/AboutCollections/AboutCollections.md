## <code>List</code> vs <code>Set</code> 
In Java, both `List` and `Set` are interfaces that extend the `Collection` interface and are used to store a collection of objects. Here's a brief overview:

**List Interface**:
- It is an ordered collection of objects in which duplicate values are allowed to store¹.
- The methods of the `List` are based on the index, so all the operations like insert, delete, update, and search are based on the index².
- It can contain null and duplicate values².
- Implementations of the `List` interface include `ArrayList`, `LinkedList`, `Vector`, and `Stack`¹².

**Set Interface**:
- It is an unordered collection of objects in which duplicate values cannot be stored¹.
- It implements the mathematical set¹.
- It stores the value in a sorted way, so it doesn't maintain the insertion order².
- Implementations of the `Set` interface include `HashSet`, `LinkedHashSet`, etc.

Here's a simple comparison:

| List                             | Set                                          |
|----------------------------------|----------------------------------------------|
| Indexed sequence                 | Non-indexed sequence                         |
| Allows duplicate elements        | Doesn't allow duplicate elements             |
| Positional access to elements    | Positional access to elements is not allowed |
| Can store multiple null elements | Can store only one null element              |

---

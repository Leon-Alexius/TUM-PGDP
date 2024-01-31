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

## `entrySet` vs `keySet`
In Java, `Map` is an interface that provides three methods to retrieve different views of its mappings: `keySet()`, `entrySet()`, and `values()`. The `keySet()` and `entrySet()` methods are often used when you want to iterate over a map¹².

Here's the difference between `keySet()` and `entrySet()`:

- `keySet()`: This method returns a `Set` view of the keys contained in the map¹. If you modify the returned set, the map will be updated as well. However, you can only access the keys with this method, not the values.

```java
Map<Integer, String> map = new HashMap<>();
map.put(1, "Geeks");
map.put(2, "For");
map.put(3, "Geeks");

// Using keySet()
for (Integer key : map.keySet()) {
    System.out.print(key + " ");
}
```

- `entrySet()`: This method returns a `Set` view of the key-value mappings contained in the map. Each element in this set is a `Map.Entry` object, which allows you to access both the key and the value¹. If you modify the returned set, the map will be updated as well.

```java
Map<Integer, String> map = new HashMap<>();
map.put(1, "Geeks");
map.put(2, "For");
map.put(3, "Geeks");

// Using entrySet()
for (Map.Entry<Integer, String> entry : map.entrySet()) {
    Integer key = entry.getKey();
    String value = entry.getValue();
    System.out.println(key + "=" + value);
}
```

In terms of performance, traversal over a large map using `entrySet()` is generally better than using `keySet()`, especially when you need both keys and values. This is because with `keySet()`, you would need to use `Map.get(key)` to retrieve each value, which could be an expensive operation depending on the implementation of the map.

package Klausur_2_Part2.AboutCollections;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

// https://docs.oracle.com/javase/8/docs/api/java/util/List.html
public class AboutLists {
    /**
     * Method to convert ArrayList to LinkedList
     * @param arrayList arrayList
     * @return linkedList
     * @param <T> dataType
     */
    public static <T> LinkedList<T> arrayListToLinkedList(ArrayList<T> arrayList) {
        return new LinkedList<>(arrayList);
    }

    /**
     * Method to convert LinkedList to ArrayList
     * @param linkedList linkedList
     * @return arrayList
     * @param <T> dataType
     */
    public static <T> ArrayList<T> linkedListToArrayList(LinkedList<T> linkedList) {
        return new ArrayList<>(linkedList);
    }

    /**
     * Method to convert List to Map (keys from 1 to n)
     * @param list anyList
     * @return Map
     * @param <T> dataType
     */
    public static <T> Map<Integer, T> listToMap(List<T> list) {
        Map<Integer, T> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(i + 1, list.get(i));
        }
        return map;
    }

    /**
     * remove duplicate elements from a List using LinkedHashSet (original intact)
     * @param list list
     * @return newList (ArrayList)
     * @param <T> dataType
     */
    public static <T> List<T> removeDuplicatesWithOrder(List<T> list) {
        return new ArrayList<>(new LinkedHashSet<>(list));
    }

    /**
     * remove duplicate elements from a List using Stream (original intact)
     * @param list list
     * @return newList (also ArrayList -> implementation of .toList() - not documented)
     * @param <T> dataType
     */
    public static <T> List<T> removeDuplicatesWithStream(List<T> list) {
        return list.stream().distinct().collect(Collectors.toList());
    }

    /**
     * converts a <code>List</code> of type <code>T</code> to an array of type <code>E[]</code>
     * <br>
     * This method will throw a <code>ClassCastException</code> if the elements in the list cannot be cast to type <code>E</code>
     * @param list list of type T
     * @param clazz ex. Integer.class
     * @return E[]
     * @param <T> dataType
     * @param <E> dataType
     */
    @SuppressWarnings("unchecked")
    public static <T, E> E[] toArray(List<T> list, Class<E> clazz) {
        E[] array = (E[]) Array.newInstance(clazz, list.size());
        for (int i = 0; i < list.size(); i++) {
            array[i] = clazz.cast(list.get(i));
        }
        return array;
    }

    /**
     * Merge a List of T and E, E must extend T, returning List of T
     * @param list1 List T
     * @param list2 List E
     * @return List T
     * @param <T> dataType
     * @param <E> extends T
     */
    public static <T, E extends T> List<T> mergeList(List<T> list1, List<E> list2) {
        List<T> mergedList = new ArrayList<>(list1);
        mergedList.addAll(list2);
        return mergedList;
    }
}

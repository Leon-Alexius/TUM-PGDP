package Klausur_2_Part2.AboutCollections;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

// https://docs.oracle.com/javase/8/docs/api/java/util/Set.html
public class AboutSets {
    /**
     * Method to convert HashSet to LinkedHashSet
     * @param hashSet hashSet
     * @return linkedHashSet
     * @param <T> dataType
     */
    public static <T> LinkedHashSet<T> hashSetToLinkedHashSet(HashSet<T> hashSet) {
        return new LinkedHashSet<>(hashSet);
    }

    /**
     * Method to convert LinkedHashSet to HashSet
     * @param linkedHashSet linkedHashSet
     * @return hashSet
     * @param <T> dataType
     */
    public static <T> HashSet<T> linkedHashSetToHashSet(LinkedHashSet<T> linkedHashSet) {
        return new HashSet<>(linkedHashSet);
    }

    /**
     * converts a <code>Set</code> of type <code>T</code> to an array of type <code>E[]</code>
     * <br>
     * This method will throw a <code>ClassCastException</code> if the elements in the list cannot be cast to type <code>E</code>
     * @param set set of type T
     * @param clazz ex. Integer.class
     * @return E[]
     * @param <T> dataType
     * @param <E> dataType
     */
    @SuppressWarnings("unchecked")
    public static <T, E> E[] toArray(Set<T> set, Class<E> clazz) {
        E[] array = (E[]) Array.newInstance(clazz, set.size());
        int i = 0;
        for (T element : set) {
            array[i++] = clazz.cast(element);
        }
        return array;
    }

    /**
     * Merge a Set of T and E, E must extend T, returning Set of T
     * @param set1 Set T
     * @param set2 Set E
     * @return Set T
     * @param <T> dataType
     * @param <E> extends T
     */
    public static <T, E extends T> Set<T> mergeSet(Set<T> set1, Set<E> set2) {
        Set<T> mergedSet = new HashSet<>(set1);
        mergedSet.addAll(set2);
        return mergedSet;
    }
}

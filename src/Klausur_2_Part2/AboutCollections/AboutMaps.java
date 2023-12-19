package Klausur_2_Part2.AboutCollections;

import java.util.*;

// https://docs.oracle.com/javase/8/docs/api/java/util/Map.html
public class AboutMaps {
    /**
     * Returns the key associated with the value (first occurrence)
     * @param map any Map
     * @param value any value
     * @return key to the value
     * @param <T> any type
     * @param <E> any type
     */
    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        if(value == null || map == null){
            return null;
        }
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Returns the keys associated with the value (set of Keys)
     * @param map any Map
     * @param value any value
     * @return key to the value
     * @param <T> any type
     * @param <E> any type
     */
    public static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
        Set<T> keys = new HashSet<>();
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    /**
     * Method to convert HashMap to LinkedHashMap
     * @param hashMap hashMap
     * @return linkedHashMap
     * @param <K> dataType
     * @param <V> dataType
     */
    public static <K, V> LinkedHashMap<K, V> hashMapToLinkedHashMap(HashMap<K, V> hashMap) {
        return new LinkedHashMap<>(hashMap);
    }

    /**
     * Method to convert LinkedHashMap to HashMap
     * @param linkedHashMap linkedHashMap
     * @return hashMap
     * @param <K> dataType
     * @param <V> dataType
     */
    public static <K, V> HashMap<K, V> linkedHashMapToHashMap(LinkedHashMap<K, V> linkedHashMap) {
        return new HashMap<>(linkedHashMap);
    }

    /**
     * Flip <key, value> to <value, key> assuming the values are all distinct
     * @param map any Map
     * @return new flipped Map
     * @param <K> dataType
     * @param <V> dataType
     */
    public static <K, V> Map<V, K> flipMap(Map<K, V> map) {
        Map<V, K> flippedMap = new HashMap<>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            flippedMap.put(entry.getValue(), entry.getKey());
        }
        return flippedMap;
    }

    /**
     * Merge a map of (K, T) with (K, E), E extends T, returns map of (K, T)
     * @param map1 map (K,T)
     * @param map2 map (K,E)
     * @return new Map of (K,T)
     * @param <K> key
     * @param <T> value
     * @param <E> value extends T
     */
    public static <K, T, E extends T> Map<K, T> mergeMap(Map<K, T> map1, Map<K, E> map2) {
        Map<K, T> mergedMap = new HashMap<>(map1);
        mergedMap.putAll(map2);
        return mergedMap;
    }
}

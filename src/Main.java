import Klausur_2_Part2.AboutCollections.AboutLists;
import Klausur_2_Part2.AboutCollections.AboutMaps;
import Klausur_2_Part2.AboutCollections.AboutSets;

import java.util.*;

/**
 * For testing purposes only/**
 * @author Drago - Lie Leon Alexius (DarkRosaleen)
 */
public final class Main {
    public static void main(String[] args) {
        // Test mergeSet
        Set<Number> set1 = new HashSet<>(Arrays.asList(1, 2, 3));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(4, 5, 6));
        Set<Number> mergedSet = AboutSets.mergeSet(set1, set2);
        System.out.println("Merged Set: " + mergedSet);

        // Test mergeList
        List<Number> list1 = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(4, 5, 6));
        List<Number> mergedList = AboutLists.mergeList(list1, list2);
        System.out.println("Merged List: " + mergedList);

        // Test mergeMap
        Map<String, Number> map1 = new HashMap<>();
        map1.put("One", 1);
        map1.put("Two", 2);
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("Three", 3);
        map2.put("Four", 4);
        Map<String, Number> mergedMap = AboutMaps.mergeMap(map1, map2);
        System.out.println("Merged Map: " + mergedMap);
    }
}

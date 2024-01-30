package Klausur_3.AboutStreams;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Tools {
    /**
     * <code>
     * List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9); <br>
     * Function<Integer, Integer> function = x -> x * x; <br>
     * List<Integer> result = applyAtEachIndex_Filter(list, 3, function); <br>
     * </code>
     * <br>
     * Will result in <code>result = {1, 16, 49}</code>, chosen index = (0, 3, 6)
     */
    public static <T, K> List<K> applyAtEachIndex_Filter(List<T> list, int index, Function<T, K> function){
        return list.stream()
                .filter(item -> list.indexOf(item) % index == 0)
                .map(function)
                .collect(Collectors.toList());
    }

    /**
     * <code>
     * List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9); <br>
     * Function<Integer, Integer> function = x -> x * x; <br>
     * List<Integer> result = applyAtEachIndex_Filter(list, 3, function); <br>
     * </code>
     * <br>
     * Will result in <code>result = {1, 2, 3, 16, 5, 6, 49, 8, 9}</code>, chosen index = (0, 3, 6)
     */
    @SuppressWarnings("unchecked")
    public static <T, K> List<K> applyAtEachIndex_NoFilter(List<T> list, int index, Function<T, K> function){
        return (List<K>) IntStream.range(0, list.size())
                .mapToObj(i -> i % index == 0 ? function.apply(list.get(i)) : list.get(i))
                .collect(Collectors.toList());
    }

    /**
     * Test Here
     */
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Function<Integer, Integer> function = x -> x * x;
        List<Integer> result = applyAtEachIndex_NoFilter(list, 3, function);
        result.forEach(System.out::println);
    }
}

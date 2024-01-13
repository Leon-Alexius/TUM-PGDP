package Klausur_3.AboutFunctional_Interface;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class Tools {
    /**
     * Apply the given Function to each element inside given List, then return a new applied List
     */
    public static <A, B> List<B> mapList(List<A> list, Function<A, B> function) {
        List<B> result = new ArrayList<>(list.size());
        for (A a : list) {
            result.add(function.apply(a));
        }
        return result;
    }

    /**
     * Change each element of given List to its String representation
     */
    public static <A> List<String> mapListToString(List<A> toString) {
        return mapList(toString, Objects::toString);
    }

    /**
     * Filter given List by given Predicate and return new filtered List
     */
    public static <A> List<A> filterList(List<A> list, Predicate<A> filter) {
        List<A> result = new ArrayList<>(list.size());
        for (A a : list) {
            if (filter.test(a)) {
                result.add(a);
            }
        }
        return result;
    }
}

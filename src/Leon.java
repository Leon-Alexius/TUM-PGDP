import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Leon{
    public static void main(String[] args) {
        int b = (int) 5.4;
    }

    public static <X, Y> void processElementsA (Iterable<X> source, Predicate<X> tester, Function<X, Y> mapper, Consumer<Y> block) {
        Stream<Y> temp = StreamSupport.stream(source.spliterator(), false)
                .filter(tester)
                .map(mapper);
        temp.forEach(block);
    }

    public static <X, Y> void processElementsB (Iterable<X> source, Predicate<X> tester, Function<X, Y> mapper, Consumer<Y> block) {
        for (X p : source) {
            if (tester.test(p)) {
                Y data = mapper.apply(p);
                block.accept(data);
            }
        }
    }
}

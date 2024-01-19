package Klausur_3.AboutStreams.EIdI;

import java.util.function.Predicate;

interface Stream<T> {
    Pair<T> eval();

    class Pair<T> {
        private T value;
        private Stream<T> rest;

        protected Pair(T value, Stream<T> rest) {
            this.value = value;
            this.rest = rest;
        }
    }

    static <T> Stream<T> empty() {
        // initial
        return () -> null;
    }

    static <T> Stream<T> of(T x) {
        return () -> new Pair<>(x, empty());
    }

    static <T> Stream<T> of(T[] args) {
    // Methode aus dem Interface Stream<T>
        class State {
            private int count = 0;
            Stream<T> of() {
                if (count==args.length) return empty();
                final T value = args[count++];
                return () -> new Pair<T>(value, of());
            }
        }
        return new State().of();
    }

    default T findFirst() {
        Pair<T> pair = eval();
        if (pair==null){
            return null;
        }
        return pair.value;
    }

    default void forEach(Consumer<? super T> action) {
        // terminal operation
        for(Pair<T> pair = eval(); pair!=null;
            pair = pair.rest.eval())
            action.accept(pair.value);
    }

    default Stream<T> filter(Predicate<? super T> p) {
        // intermediate operation
        return () -> {
            Pair<T> pair = Stream.this.eval();
            if (pair == null) {
                return null;
            }
            if (p.test(pair.value)) {
                return new Pair<T>(pair.value, pair.rest.filter(p));
            }
            else {
                return pair.rest.filter(p).eval();
            }
        };
    }
}

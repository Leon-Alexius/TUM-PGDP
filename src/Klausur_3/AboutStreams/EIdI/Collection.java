package Klausur_3.AboutStreams.EIdI;

import java.util.Iterator;

public interface Collection<T> {
    Iterator<T> iterator();

    default Stream<T> stream() {
    // default Methode des Interface Collection<T>
        class State {
            Iterator<T> x = iterator();
            Stream<T> stream() {
                return () -> (x.hasNext())? new Stream.Pair<>(x.next(), stream()) : null;
            }
        }
        return new State().stream();
    }
}

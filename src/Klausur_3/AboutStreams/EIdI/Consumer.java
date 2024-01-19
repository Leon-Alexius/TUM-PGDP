package Klausur_3.AboutStreams.EIdI;

// https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html
interface Consumer<T> {
    /**
     * Performs this operation on the given argument.
     */
    void accept(T t);

    /**
     * Returns a composed Consumer that performs, in sequence, this operation followed by the after operation.
     * @param after Consumer Operation
     * @return a composed Consumer
     */
    default Consumer<T> andThen(Consumer<? super T> after) {
        return (T t) -> {
            accept(t);
            after.accept(t);
        };
    }
}

/**
 * For testing purposes only/**
 * @author Drago - Lie Leon Alexius (DarkRosaleen)
 */
public final class Main {
    public static class A<S, T> {

    }

    private class B<S> extends A<S, Number>{

    }

    public class C<S, T> {

    }


    public static void main(String[] args) {
        Main main = new Main();

        // A is inner class, public, and static
        Main.A<Integer, Number> a = new A<>();

        // B is inner class, private, and not static
        Main.B<Integer> b = main.new B<>();

        // A is inner class, public, and not static
        Main.C<Integer, Number> c = main.new C<>();
        // Main.C<Integer, Number> c = new C<>(); will throw an error
    }
}

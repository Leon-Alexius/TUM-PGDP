package DiscreteStructure;

public class Euros {
    private static int p(int n, int k) {
        if (k == 0 && n > 0) {
            return 0;
        }
        if (n == k) {
            return 1;
        }
        if (k > n) {
            return 0;
        }
        return p(n - 1, k - 1) + p(n - k, k);
    }

    public static void main(String[] args) {
        System.out.println(p(9, 5));
    }
}

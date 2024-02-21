package DiscreteStructure;

/**
 * calculates the greatest common divisor (GCD) of two numbers a and b,
 * as well as the coefficients of Bézout’s identity (x and y), which satisfy the equation ax + by = gcd(a, b)
 */
public class ExtendedEuclideanAlgorithm {
    public static int[] extendedEuclidean(int a, int b) {
        if (b == 0) {
            return new int[]{a, 1, 0};
        } else {
            int[] result = extendedEuclidean(b, a % b);
            int gcd = result[0];
            int x = result[2];
            int y = result[1] - (a / b) * result[2];
            return new int[]{gcd, x, y};
        }
    }

    public static void main(String[] args) {
        int a = 935;
        int b = 1491;
        int[] result = extendedEuclidean(a, b);
        System.out.println("GCD: " + result[0]);
        System.out.println("Coefficients (x, y): (" + result[1] + ", " + result[2] + ")");
    }
}


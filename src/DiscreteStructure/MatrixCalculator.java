package DiscreteStructure;

/**
 * Given a Matrix (M), we want to calculate the average value of M + M^2 + ... + M^n
 */
public class MatrixCalculator {
    private final double[][] matrix;
    private final long n;

    public MatrixCalculator(double[][] matrix, long n) {
        this.matrix = matrix;
        this.n = n;
    }

    public double[][] calculateAverageSum() {
        double[][] result = new double[matrix.length][matrix[0].length];
        double[][] temp = matrix.clone();

        for (long i = 1; i <= n; i++) {
            addMatrices(result, temp);
            temp = multiplyMatrices(temp, matrix);
        }

        return multiplyMatrixByScalar(result, 1.0 / n);
    }

    private void addMatrices(double[][] a, double[][] b) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                a[i][j] += b[i][j];
            }
        }
    }

    private double[][] multiplyMatrices(double[][] a, double[][] b) {
        double[][] result = new double[a.length][b[0].length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return result;
    }

    private double[][] multiplyMatrixByScalar(double[][] matrix, double scalar) {
        double[][] result = new double[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result[i][j] = matrix[i][j] * scalar;
            }
        }

        return result;
    }

    /**
     * Call this method to get a better view of the matrix
     * @param matrix matrix in 2D-Array
     * @return String representation
     */
    public String formattedOutput(double[][] matrix) {
        StringBuilder sb = new StringBuilder();

        for (double[] row : matrix) {
            sb.append("[");
            for (double num : row) {
                sb.append(num).append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
            sb.append("]\n");
        }

        return sb.toString();
    }
}

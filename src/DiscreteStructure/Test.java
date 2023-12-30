package DiscreteStructure;

public class Test {
    public static void main(String[] args) {
        // a stochastic matrix (don't care about eigenvalue)
        double[][] matrixA = {
                {0, 0.5, 0, 0.5},
                {0, 0, 0.5, 0.5},
                {1, 0, 0, 0},
                {0, 0.5, 0, 0.5}
        };

        // a matrix with eigenvalue = 1
        double[][] matrixB = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };

        // a convergent to 0 matrix (all eigenvalue < 1)
        double[][] matrixC = {
                {0.25, 0.25, 0.25, 0.25},
                {(double) 1 /3, 0, (double) 1 /3, (double) 1 /3},
                {0, 0, 0, 0},
                {0, 0.5, 0, 0.5}
        };

        // a divergent matrix
        double[][] matrixD = {
                {2,0},
                {0,2}
        };

        MatrixCalculator matrixCalculator = getMatrixCalculator(matrixC, 9999999);
        System.out.println(matrixCalculator.formattedOutput(matrixCalculator.calculateAverageSum()));
    }

    private static MatrixCalculator getMatrixCalculator(double[][] matrix, long n) {
        // also calculate the eigenvalues and eigenvector
        CalculateEigen calculateEigen = new CalculateEigen(matrix);
        calculateEigen.getEigenValue();
        System.out.println();
        calculateEigen.getEigenVector();
        System.out.println();

        return new MatrixCalculator(matrix, n);
    }

}

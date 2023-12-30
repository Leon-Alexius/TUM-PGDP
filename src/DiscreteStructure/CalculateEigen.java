package DiscreteStructure;

import org.apache.commons.math4.legacy.linear.EigenDecomposition;
import org.apache.commons.math4.legacy.linear.MatrixUtils;
import org.apache.commons.math4.legacy.linear.RealMatrix;
import org.apache.commons.math4.legacy.linear.RealVector;

public class CalculateEigen {
    private final RealMatrix realMatrix;
    private final EigenDecomposition eigenDecomposition;
    private final double[] realEigenValues;

    public CalculateEigen(double[][] matrix){
        this.realMatrix = MatrixUtils.createRealMatrix(matrix);
        this.eigenDecomposition = new EigenDecomposition(realMatrix);
        // Get the real parts of the eigenvalues
        this.realEigenValues = eigenDecomposition.getRealEigenvalues();
    }

    public void getEigenValue(){
        for (double eigenvalue : realEigenValues) {
            System.out.println("Eigenvalue: " + eigenvalue);
        }
    }

    /**
     * <strong> Not completely tested </strong> - might contain bugs
     */
    public void getEigenVector(){
        // Iterate over eigenvalues and find corresponding eigenvectors
        for (double eigenvalue : realEigenValues) {

            // Check if the eigenvalue is real (not complex)
            if (!Double.isInfinite(eigenvalue) && !Double.isNaN(eigenvalue)) {

                // Iterate over the indices and find the corresponding eigenvector
                for (int i = 0; i < realMatrix.getRowDimension(); i++) {
                    double[] eigenvectorData = eigenDecomposition.getEigenvector(i).toArray();

                    if (eigenvectorData[0] == eigenvalue) {
                        RealVector eigenvector = eigenDecomposition.getEigenvector(i);

                        // Print the eigenvalue and eigenvector
                        // System.out.println("Eigenvalue: " + eigenvalue);
                        System.out.println("Eigenvector: " + eigenvector);

                        break;
                    }
                }
            }
        }
    }
}

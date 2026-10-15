
public class ComplexMatrix {
    private int rows;
    private int cols;
    private Complex[][] matrix;

    public ComplexMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        matrix = new Complex[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = new Complex(0, 0);
            }
        }
    }


    public Complex getElement(int row, int col) {
        return matrix[row][col];
    }

    public void setElement(int row, int col, Complex value) {
        matrix[row][col] = value;
    }


    public ComplexMatrix add(ComplexMatrix other) {
        if (rows != other.rows || cols != other.cols) {
            throw new IllegalArgumentException("Размеры не совпадают");
        }

        ComplexMatrix result = new ComplexMatrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.matrix[i][j] = matrix[i][j].add(other.matrix[i][j]);
            }
        }

        return result;
    }


    public ComplexMatrix multiply(ComplexMatrix other) {
        if (cols != other.rows) {
            throw new IllegalArgumentException("нельзя   умножить матрицы");
        }

        ComplexMatrix result = new ComplexMatrix(rows, other.cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                Complex sum = new Complex(0, 0);

                for (int k = 0; k < cols; k++) {
                    sum = sum.add(matrix[i][k].multiply(other.matrix[k][j]));
                }

                result.matrix[i][j] = sum;
            }
        }

        return result;
    }


    public ComplexMatrix transpose() {
        ComplexMatrix result = new ComplexMatrix(cols, rows);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.matrix[j][i] = matrix[i][j];
            }
        }

        return result;
    }


    public Complex determinant() {
        if (rows != cols) {
            throw new IllegalArgumentException("Матрица должна быть кв.");
        }

        if (rows == 1) {
            return matrix[0][0];
        }

        if (rows == 2) {
            return matrix[0][0].multiply(matrix[1][1])
                    .subtract(matrix[0][1].multiply(matrix[1][0]));
        }

        Complex det = new Complex(0, 0);

        for (int j = 0; j < cols; j++) {
            ComplexMatrix minor = new ComplexMatrix(rows - 1, cols - 1);

            for (int r = 1; r < rows; r++) {
                int c2 = 0;

                for (int c = 0; c < cols; c++) {
                    if (c != j) {
                        minor.setElement(r - 1, c2, matrix[r][c]);
                        c2++;
                    }
                }
            }

            Complex term = matrix[0][j].multiply(minor.determinant());

            if (j % 2 == 0) {
                det = det.add(term);
            } else {
                det = det.subtract(term);
            }
        }

        return det;
    }


    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + "\t");
            }

            System.out.println();
        }
    }
}

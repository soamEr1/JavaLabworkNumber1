public class ComplexMatrix {
    private int howManyRows;
    private int howManyCols;
    private Complex[][] numbersBox;

    public ComplexMatrix(int howManyRows, int howManyCols) {
        this.howManyRows = howManyRows;
        this.howManyCols = howManyCols;

        numbersBox = new Complex[howManyRows][howManyCols];


        for (int row = 0; row < howManyRows; row++) {
            for (int col = 0; col < howManyCols; col++) {
                numbersBox[row][col] = new Complex(0, 0);

            }
        }
    }


    public Complex getElement(int row, int col) {
        return numbersBox[row][col];
    }


    public void setElement(int row, int col, Complex value) {
        numbersBox[row][col] = value;

    }

    public ComplexMatrix add(ComplexMatrix anotherMatrix) {

        if (howManyRows != anotherMatrix.howManyRows || howManyCols != anotherMatrix.howManyCols) {
            throw new IllegalArgumentException("Размеры не совпадают");
        }


        ComplexMatrix answerMatrix = new ComplexMatrix(howManyRows, howManyCols);


        for (int row = 0; row < howManyRows; row++) {
            for (int col = 0; col < howManyCols; col++) {
                answerMatrix.numbersBox[row][col] =

                        numbersBox[row][col].add(
                                anotherMatrix.numbersBox[row][col]);

            }
        }

        return answerMatrix;
    }



    public ComplexMatrix multiply(ComplexMatrix anotherMatrix) {

        if (howManyCols != anotherMatrix.howManyRows) {
            throw new IllegalArgumentException("Нелзя умножить матрицы");
        }

        ComplexMatrix answerMatrix = new ComplexMatrix(howManyRows, anotherMatrix.howManyCols);

        for (int row = 0; row < howManyRows; row++) {
            for (int col = 0; col < anotherMatrix.howManyCols; col++) {
                Complex tempSum = new Complex(0, 0);

                for (int k = 0; k < howManyCols; k++) {
                    tempSum = tempSum.add(
                            numbersBox[row][k].multiply(
                                    anotherMatrix.numbersBox[k][col]));
                }


                answerMatrix.numbersBox[row][col] = tempSum;
            }
        }

        return answerMatrix;

    }



    public ComplexMatrix transpose() {

        ComplexMatrix answerMatrix = new ComplexMatrix(howManyCols, howManyRows);



        for (int row = 0; row < howManyRows; row++) {

            for (int col = 0; col < howManyCols; col++) {
                answerMatrix.numbersBox[col][row] = numbersBox[row][col];
            }
        }

        return answerMatrix;
    }



    public Complex determinant() {
        if (howManyRows != howManyCols) {

            throw new IllegalArgumentException(
                    "Матрица должна быть квадратной");
        }


        if (howManyRows == 1) {
            return numbersBox[0][0];
        }



        if (howManyRows == 2) {

            return numbersBox[0][0].multiply(numbersBox[1][1]).subtract(numbersBox[0][1].multiply(numbersBox[1][0]));
        }

        Complex detNumber = new Complex(0, 0);

        for (int col = 0; col < howManyCols; col++) {
            ComplexMatrix smallMatrix = new ComplexMatrix(howManyRows - 1, howManyCols - 1);


            for (int row = 1; row < howManyRows; row++) {
                int newCol = 0;

                for (int oldCol = 0; oldCol < howManyCols; oldCol++) {

                    if (oldCol != col) {
                        smallMatrix.setElement(row - 1, newCol,
                                numbersBox[row][oldCol]);
                        newCol++;
                    }
                }
            }

            Complex partOfDet = numbersBox[0][col].multiply(smallMatrix.determinant());


            if (col % 2 == 0) {
                detNumber = detNumber.add(partOfDet);
            } else {
                detNumber = detNumber.subtract(partOfDet);

            }
        }

        return detNumber;
    }


    public void print() {
        for (int row = 0; row < howManyRows; row++) {

            for (int col = 0; col < howManyCols; col++) {
                System.out.print(numbersBox[row][col] + "\t");
            }

            System.out.println();
        }
    }
}

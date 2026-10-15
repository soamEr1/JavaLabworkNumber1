public class Complex {

    private double normalPart;
    private double iPart;
    public Complex(double normalPart, double iPart) {

        this.normalPart = normalPart;
        this.iPart = iPart;
    }

    public double getReal() {
        return normalPart;
    }

    public double getImaginary() {
        return iPart;
    }

    public Complex add(Complex secondNumber) {
        return new Complex(
                normalPart + secondNumber.normalPart,
                iPart + secondNumber.iPart
        );
    }

    public Complex subtract(Complex secondNumber) {
        return new Complex(
                normalPart - secondNumber.normalPart,
                iPart - secondNumber.iPart

        );

    }

    public Complex multiply(Complex secondNumber) {

        double newReal = normalPart * secondNumber.normalPart - iPart * secondNumber.iPart;
        double newImaginary = normalPart * secondNumber.iPart + iPart * secondNumber.normalPart;
        return new Complex(newReal, newImaginary);
    }


    public Complex divide(Complex secondNumber) {
        double bottom = secondNumber.normalPart * secondNumber.normalPart + secondNumber.iPart * secondNumber.iPart;

        if (bottom == 0) {
            throw new ArithmeticException("Деление на ноль");
        }


        double newReal = (normalPart * secondNumber.normalPart + iPart * secondNumber.iPart) / bottom;
        double newImaginary = (iPart * secondNumber.normalPart - normalPart * secondNumber.iPart) / bottom;

        return new Complex(newReal, newImaginary);
    }

    public double abs() {
        return Math.sqrt(normalPart * normalPart + iPart * iPart);
    }


    @Override

    public String toString() {
        if (iPart == 0) {
            return normalPart + "";
        }
        if (normalPart == 0) {
            return iPart + "i";
        }
        if (iPart > 0) {
            return normalPart + " + " + iPart + "i";
        }
        return normalPart + " - " + (-iPart) + "i";

    }
}

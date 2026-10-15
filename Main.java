import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Введите строки:");
        int rowCount = keyboard.nextInt();
        System.out.println("Введите столбцы:");
        int colCount = keyboard.nextInt();
        ComplexMatrix firstMatrix = new ComplexMatrix(rowCount, colCount);
        ComplexMatrix secondMatrix = new ComplexMatrix(rowCount, colCount);
        System.out.println("Заполняем первую матрицу");

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                System.out.println("Элемент " + row + " " + col);

                System.out.print("Часть без i: ");
                double realPart = keyboard.nextDouble();

                System.out.print("Часть с i: ");
                double imaginaryPart = keyboard.nextDouble();

                firstMatrix.setElement(row, col, new Complex(realPart, imaginaryPart));
            }
        }
        System.out.println("Теперь вторая матрица");
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                System.out.println("Элемент " + row + " " + col);
                System.out.print("Часть без i: ");
                double realPart = keyboard.nextDouble();
                System.out.print("Часть с i: ");
                double imaginaryPart = keyboard.nextDouble();
                secondMatrix.setElement(row, col,new Complex(realPart, imaginaryPart));
            }
        }

        System.out.println("Матрица A:");
        firstMatrix.print();
        System.out.println("Матрица B:");
        secondMatrix.print();
        ComplexMatrix lastResult = null;

        while (true) {
            System.out.println();
            System.out.println("Что делаем?");
            System.out.println("1 - сложить");
            System.out.println("2 - умножить");
            System.out.println("3 - транспнировать");
            System.out.println("4 - найти определитель");
            System.out.println("5 - показать текущий результат");
            System.out.println("0 - выйти");
            int menuNumber = keyboard.nextInt();

            try {
                if (menuNumber == 1) {
                    if (lastResult == null) {
                        lastResult = firstMatrix.add(secondMatrix);
                    } else {
                        lastResult = lastResult.add(firstMatrix);
                    }
                    System.out.println("Получилось:");
                    lastResult.print();
                } else if (menuNumber == 2) {
                    if (lastResult == null) {
                        lastResult = firstMatrix.multiply(secondMatrix);
                    } else {
                        lastResult = lastResult.multiply(firstMatrix);
                    }

                    System.out.println("Резpультат умножения:");
                    lastResult.print();

                } else if (menuNumber == 3) {
                    if (lastResult == null) {
                        lastResult = firstMatrix.transpose();
                    } else {
                        lastResult = lastResult.transpose();
                    }

                    System.out.println("После транспанирования:");
                    lastResult.print();

                } else if (menuNumber == 4) {
                    if (lastResult == null) {
                        System.out.println(firstMatrix.determinant());
                    } else {
                        System.out.println(lastResult.determinant());
                    }

                } else if (menuNumber == 5) {
                    
                    if (lastResult == null) {
                        System.out.println("Пока результата нет");
                    } else {
                        lastResult.print();
                    }

                } else if (menuNumber == 0) {
                    break;

                } else {
                    System.out.println("Такого пункта нет");
                }

            } catch (IllegalArgumentException e) {
                System.out.println("С такими размерами операция не раотает");
            }
        }

        keyboard.close();
    }
}

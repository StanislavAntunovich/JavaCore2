package ru.geekbrains.lesson2;

import ru.geekbrains.lesson2.exceptions.MyArrayException;

import java.util.Arrays;
import java.util.Random;

public class MainClass {

    public static void main(String[] args) {
        String[][][] arrayData = new String[][][]{getRightTestData(), getWrongSizeTestData(), getWrongValueTestData()};
        System.out.println();

        for (String[][] data : arrayData) {
            try {
                int sum = MyArrayCounting.calculateArraySum(data);
                System.out.println("Сумма всех элементов массива равна: " + sum);
            } catch (MyArrayException ex) {
                System.out.println("Данные не могут быть посчитаны по причине: " + ex.getLocalizedMessage());
            }
        }
    }

    private static String[][] getRightTestData() {
        Random r = new Random();
        String[][] data = new String[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                data[i][j] = String.valueOf(r.nextInt(100));
            }
        }
        System.out.println("рандомный корректный массив: " + Arrays.deepToString(data));
        return data;
    }

    private static String[][] getWrongSizeTestData() {
        return new String[][]{{"2", "3", "3"}, {"2", "3"}};
    }

    private static String[][] getWrongValueTestData() {
        String[][] data = new String[][]{
                {"1", "2", "3", "4"},
                {"2", "3", "4", "5"},
                {"2", "3", "4", "5"},
                {"2", "3", "4", "5"},
        };
        Random r = new Random();
        data[r.nextInt(5)][r.nextInt(5)] = String.valueOf((char) r.nextInt(128));
        System.out.println("Рандомный не корректный массив: " + Arrays.deepToString(data));
        return data;
    }
}

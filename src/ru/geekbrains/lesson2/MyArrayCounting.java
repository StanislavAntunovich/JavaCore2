package ru.geekbrains.lesson2;

import ru.geekbrains.lesson2.exceptions.MyArrayDataException;
import ru.geekbrains.lesson2.exceptions.MyArraySizeException;

public class MyArrayCounting {

    public static int calculateArraySum(String[][] array) throws MyArraySizeException, MyArrayDataException {
        checkArrSize(array);
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.valueOf(array[i][j]);
                } catch (NumberFormatException ex) {
                    throw new MyArrayDataException(i, j, array[i][j]);
                }
            }
        }
        return sum;
    }

    private static void checkArrSize(String[][] array) throws MyArraySizeException {
        if (array.length != 4)
            throw new MyArraySizeException();

        for (String[] strings : array) {
            if (strings.length != 4)
                throw new MyArraySizeException();
        }
    }
}

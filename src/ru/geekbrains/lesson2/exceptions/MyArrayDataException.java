package ru.geekbrains.lesson2.exceptions;

public class MyArrayDataException extends MyArrayException {
    public MyArrayDataException(int x, int y, String value) {
        super(String.format("В ячейке %dx%d неверное значение: %s", x, y, value));
    }

}

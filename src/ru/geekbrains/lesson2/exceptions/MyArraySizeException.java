package ru.geekbrains.lesson2.exceptions;

public class MyArraySizeException extends MyArrayException {
    public MyArraySizeException() {
        super("Массив неверного размера, должен быть 4x4");
    }
}

package ru.geekbrains.lesson2.exceptions;

import java.io.IOException;

public class MyArrayException extends IOException {
    MyArrayException(String s) {
        super(s);
    }

    public MyArrayException(String s, Throwable throwable) {
        super(s, throwable);
    }
}

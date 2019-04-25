package ru.geekbrains.lesson4;

public class AuthException extends Exception {
    public AuthException(String reason) {
        super(reason);
    }
}

package ru.geekbrains.lesson4.server.auth;

public interface AuthService {
    boolean isAuthorized(String login , String password);
}

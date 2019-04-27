package ru.geekbrains.lesson4.server.auth;

import java.util.HashMap;
import java.util.Map;

public class AuthServiceImpl implements AuthService {

    private Map<String, String> users = new HashMap<>();

    public AuthServiceImpl() {
        users.put("Master", "ADDQD");
        users.put("Margarita", "qwerty001");
        users.put("Voland", "qwerty002");
        users.put("Begemot", "qwerty003");
    }

    @Override
    public boolean isAuthorized(String login, String password) {
        String pwd = users.get(login);
        return pwd != null && pwd.equals(password);
    }
}

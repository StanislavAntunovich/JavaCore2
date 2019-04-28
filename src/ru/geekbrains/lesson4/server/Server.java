package ru.geekbrains.lesson4.server;

import ru.geekbrains.lesson4.AuthException;
import ru.geekbrains.lesson4.server.auth.AuthService;
import ru.geekbrains.lesson4.server.auth.AuthServiceImpl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static ru.geekbrains.lesson4.MessagesPatterns.*;

public class Server {

    public static void main(String[] args) {
        new Server().startServer(7777);
    }


    private boolean isOnline;
    private AuthService authService = new AuthServiceImpl();

    private Map<String, ClientHandler> clientHandlers = Collections.synchronizedMap(new HashMap<>());

    //TODO: refactor
    public void sendAddressedMessage(String from, String to, String message) {
        ClientHandler toCli = clientHandlers.getOrDefault(to, null);
        ClientHandler fromCli = clientHandlers.get(from);
        if (toCli != null) {
            toCli.sendMessage(String.format(MESSAGE_PATTERN,from, message));
            fromCli.sendMessage(String.format(MESSAGE_PATTERN,from, message));
        } else {
            fromCli.sendMessage(String.format(USER_OFFLINE_PATTERN, to));
        }
    }

    public void sendBroadcastMessage(String message) {
        for (ClientHandler cl : clientHandlers.values())
            cl.sendMessage(message);
    }

    public void subscribe(String login, ClientHandler clientHandler) {
        userCameOnline(login);
        clientHandlers.put(login, clientHandler);
    }

    public void unSubscribe(String login) {
        clientHandlers.remove(login);
        userCameOffline(login);
    }

    public void startServer(int port) {
        isOnline = true;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (isOnline) {
                Socket candidateSocket = serverSocket.accept();
                DataOutputStream out = new DataOutputStream(candidateSocket.getOutputStream());
                DataInputStream in = new DataInputStream(candidateSocket.getInputStream());

                String authMessage = in.readUTF();
                String[] auth = authMessage.split(" ");
                try {
                    String userLogin = checkAuthorization(auth);
                    out.writeUTF("/auth succeeded");
                    this.subscribe(userLogin, new ClientHandlerImpl(userLogin, candidateSocket, this));

                } catch (AuthException e) {
                    e.printStackTrace();
                    out.writeUTF("/error " + e.getMessage());
                    out.flush();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // не очень логично

    private String checkAuthorization(String[] auth) throws AuthException {
        if (!auth[0].equals("/auth") || auth.length != 3) {
            throw new AuthException("Wrong auth message");
        } else if (!authService.isAuthorized(auth[1], auth[2])) {
            throw new AuthException("Wrong login or password");
        }

        if (clientHandlers.containsKey(auth[1])) {
            throw new AuthException("User already logged in");
        }
        return auth[1];
    }

    public void sendUsersList(String userLogin) {
        ClientHandler cl = clientHandlers.get(userLogin);
        if (!clientHandlers.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (String login : clientHandlers.keySet()){
                if (!login.equals(userLogin)) {
                    sb.append(login);
                    sb.append(" ");
                }
            }
            cl.sendMessage(String.format(USERS_LIST_PATTERN, sb.toString().trim()));
        }
    }

    private void userCameOnline(String userLogin) {
        sendBroadcastMessage(String.format(USER_CAME_ONLINE_PATTERN, userLogin));
    }

    private void userCameOffline(String userLogin) {
        sendBroadcastMessage(String.format(USER_CAME_OFLINE_PATTERN, userLogin));
    }




}

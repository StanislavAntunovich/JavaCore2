package ru.geekbrains.lesson4.client.network;

import javafx.application.Platform;
import ru.geekbrains.lesson4.AuthException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

import static ru.geekbrains.lesson4.MessagesPatterns.*;

public class Network {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Thread receiverThread;

    private IncomeMessageHandler incomeMessageHandler;

    private final String host;

    private final int port;
    private String login;


    public Network(String host, int port) throws IOException {
        this.host = host;
        this.port = port;

        this.socket = new Socket(host, port);

        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());


        receiverThread = new Thread(() -> {
            while (true) {
                try {
                    String msg = in.readUTF();
                    System.out.println("income msg: " + msg);
                    if (msg.startsWith("/"))
                        handleServerCommand(msg);
                    else {
                        String[] splitedMsg = msg.split(">", 2);
                        incomeMessageHandler.handleMessage(splitedMsg[0], splitedMsg[1]);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        });

    }

    private void handleServerCommand(String msg) {
        String[] preparedMsg = msg.split(" ", 2);
        String command = preparedMsg[0];
        //TODO: поменять на switch
        if (preparedMsg[0].equals(USERS_LIST_COMMAND)) {
            incomeMessageHandler.setOnlineUsersList(Arrays.asList(preparedMsg[1].split(" ")));
            return;
        }
        if (command.equals(USER_CAME_OFLINE_COMMAND)) {
            incomeMessageHandler.removeOnlineUser(preparedMsg[1]);
            return;
        }
        if (command.equals(USER_CAME_ONLINE_COMMAND)) {
            incomeMessageHandler.addOnlineUser(preparedMsg[1]);
            return;
        }
    }

    public void authorize(String login, String password) throws IOException, AuthException {

        try {
            out.writeUTF(String.format(AUTH_MESSAGE, login, password));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String authResponse = in.readUTF();

        if (authResponse.equals("/auth succeeded")) {
            this.login = login;
            Platform.runLater(() -> receiverThread.start());
            receiverThread.setDaemon(true);
        } else {
            throw new AuthException(authResponse.split(" ", 2)[1]);
        }
    }

    public void requestOnlineUsersList() throws IOException {
        out.writeUTF(REQUEST_ONLINE_USERS);
    }

    public void sendMessage(String to, String message) {
        try {
            if (!to.equals("all")) {
                sendAddressedMessage(to, message);
            } else {
                out.writeUTF(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendAddressedMessage(String to, String message) throws IOException {
        System.out.println("addressed msg: " + String.format(ADDRESSED_MESSAGE_PATTERN, to, message));
        out.writeUTF(String.format(ADDRESSED_MESSAGE_PATTERN, to, message));
    }


    public String getLogin() {
        return login;
    }

    public void setIncomeMessageHandler(IncomeMessageHandler incomeMessageHandler) {
        this.incomeMessageHandler = incomeMessageHandler;
    }
}

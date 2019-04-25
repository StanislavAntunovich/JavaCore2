package ru.geekbrains.lesson4.client.network;

import javafx.application.Platform;
import ru.geekbrains.lesson4.AuthException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static ru.geekbrains.lesson4.MessagesPatterns.ADDRESSED_MESSAGE_PATTERN;
import static ru.geekbrains.lesson4.MessagesPatterns.AUTH_MESSAGE;

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
                    String[] splitedMsg = msg.split(">", 2);
                    incomeMessageHandler.handleMessage(splitedMsg[0], splitedMsg[1]);
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        });

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

    public void sendMessage(String message) {
        try {
            if (message.startsWith("/w")) {
                String[] messageSplited = message.split(" ", 3);
                sendAddressedMessage(messageSplited[1], messageSplited[2]);
            } else {
                out.writeUTF(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendAddressedMessage(String to, String message) throws IOException {
        out.writeUTF(String.format(ADDRESSED_MESSAGE_PATTERN, to, message));
    }


    public String getLogin() {
        return login;
    }

    public void setIncomeMessageHandler(IncomeMessageHandler incomeMessageHandler) {
        this.incomeMessageHandler = incomeMessageHandler;
    }
}

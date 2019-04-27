package ru.geekbrains.lesson4.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static ru.geekbrains.lesson4.MessagesPatterns.MESSAGE_PATTERN;

public class ClientHandlerImpl implements ClientHandler {

    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;
    private Thread receiverThread;

    private Server server;

    private String login;

    public ClientHandlerImpl(String login, Socket socket, Server server) throws IOException {
        this.login = login;
        this.socket = socket;
        this.server = server;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());

        this.receiverThread = new Thread(() -> {
            while (true) {
                try {
                    String message = in.readUTF();
                    if (message.startsWith("/w ")) {
                        String[] splitedMessage = message.split(" ", 3);
                        server.sendAddressedMessage(login,
                                splitedMessage[1],
                                String.format(MESSAGE_PATTERN, login, splitedMessage[2]));
                    } else {
                        server.sendBroadcastMessage(String.format(MESSAGE_PATTERN, login, message));
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                    server.unSubscribe(login);
                    break;
                }
            }
        });

        receiverThread.setDaemon(true);
        receiverThread.start();
    }

    @Override
    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopHandling() {
        try {
            socket.close();
        } catch (IOException e) {

        }

        server.unSubscribe(login);

    }




}

package ru.geekbrains.lesson5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocServer {

    private static final int PORT = 8888;
    private static boolean online;

    private static void startReading(Socket socket) throws IOException {
        DataInputStream in = new DataInputStream(socket.getInputStream());

        new Thread(() -> {
            while (online) {
                try {
                    System.out.println("New message: " + in.readUTF());
                } catch (IOException e) {
                    e.printStackTrace();
                    online = false;
                }
            }
        }).start();

    }

    private static void startWriting(Socket socket) throws IOException {

        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        Scanner scanner = new Scanner(System.in);

        new Thread(() -> {
            String message;
            do {
                System.out.print("Input message > ");
                message = scanner.nextLine();

                if (message.equals("/stop")) {
                    online = false;
                    break;
                }
                try {
                    out.writeUTF(message);
                } catch (IOException e) {
                    e.printStackTrace();
                    online = false;
                }
            } while (online && scanner.hasNextLine());
        }).start();
    }

    public static void main(String[] args) {
        online = true;

        try {

            ServerSocket serverSocket = new ServerSocket(PORT);

            System.out.println("server waiting for connection");

                Socket socket = serverSocket.accept();
                startReading(socket);
                startWriting(socket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

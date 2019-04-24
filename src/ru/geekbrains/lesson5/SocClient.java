package ru.geekbrains.lesson5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SocClient {

    private static final String HOST = "localhost";
    private static final int PORT = 8888;
    private static boolean online = true;

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
                if (message.equals("/quit")) {
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

        try {
            Socket socket = new Socket(HOST, PORT);
            startReading(socket);
            startWriting(socket);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

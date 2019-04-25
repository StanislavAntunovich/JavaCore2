package ru.geekbrains.lesson4.client.network;

public interface IncomeMessageHandler {
    void handleMessage(String from, String message);

}

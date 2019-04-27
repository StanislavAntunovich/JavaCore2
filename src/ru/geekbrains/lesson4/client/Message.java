package ru.geekbrains.lesson4.client;

import java.time.LocalDateTime;

public class Message {
    private LocalDateTime created;
    private String user;
    private String messageText;
    private boolean isPrivate;

    public Message() {
        created = LocalDateTime.now();
    }

    public Message(String user, String messageText, boolean isPrivate) {
        this.isPrivate = isPrivate;
        this.user = user;
        this.messageText = messageText;
        created = LocalDateTime.now();
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }
}

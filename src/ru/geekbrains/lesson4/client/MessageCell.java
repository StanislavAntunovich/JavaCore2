package ru.geekbrains.lesson4.client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class MessageCell extends ListCell<Message> {

    @FXML
    private HBox messageCellTop;

    @FXML
    private Label labelTimeStamp;

    @FXML
    private TextArea txtMessage;

    @FXML
    private Label labelUser;

    public MessageCell() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/fxml/message_cell.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
                e.printStackTrace();
        }
    }

    @Override
    protected void updateItem(Message message, boolean b) {
        super.updateItem(message, b);

        labelTimeStamp.setText(message.getCreated().toString());
        labelUser.setText(message.getUser());
        txtMessage.setText(message.getMessageText());
    }
}

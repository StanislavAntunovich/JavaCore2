package ru.geekbrains.lesson4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class Controller {
    @FXML
    private TextArea areaMessages;

    @FXML
    private TextField txtMessageInput;

    @FXML
    private Button bttnSendMessage;


    @FXML
    private void initialize() {
        bttnSendMessage.setOnAction(this::sendMessage);
        txtMessageInput.setOnAction(this::sendMessage);
    }

    private void sendMessage(ActionEvent actionEvent) {
        String message = txtMessageInput.getText();
        areaMessages.appendText(message + "\n");
        txtMessageInput.clear();
        txtMessageInput.requestFocus();
    }
}

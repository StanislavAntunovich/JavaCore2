package ru.geekbrains.lesson4.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import ru.geekbrains.lesson4.AuthException;
import ru.geekbrains.lesson4.client.network.Network;

import java.io.IOException;

import static ru.geekbrains.lesson4.MessagesPatterns.INCOME_MESSAGE;


public class Controller {

    private Alert errorAlert;

    private Network network;

    private boolean isAuthorized;

    @FXML
    private TextArea areaMessages;

    @FXML
    private TextField txtMessageInput;

    @FXML
    private Button bttnSendMessage;

    @FXML
    private VBox panelLogin;

    @FXML
    private VBox panelMessages;

    @FXML
    private Button bttnOk;

    @FXML
    private Button bttnCancel;

    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField txtPassword;


    @FXML
    private void initialize() {
        errorAlert = new Alert(Alert.AlertType.ERROR);

        bttnSendMessage.setOnAction(this::sendMessage);
        txtMessageInput.setOnAction(this::sendMessage);
        bttnOk.setOnAction(this::okBttnCLicked);
        txtPassword.setOnAction(this::okBttnCLicked);
        bttnCancel.setOnAction(this::cancelBttnClicked);

        setAuthorized(false);
    }

    private void sendMessage(ActionEvent actionEvent) {

        String message = txtMessageInput.getText();
        network.sendMessage(message);
        txtMessageInput.clear();
        txtMessageInput.requestFocus();
    }

    private void printMessage(String from, String message) {
        final String messageFrom = from.equals(network.getLogin()) ? "Вы" : from;
        areaMessages.appendText(String.format(INCOME_MESSAGE, messageFrom, message));
    }

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
        panelLogin.setVisible(!isAuthorized);
        panelLogin.setManaged(!isAuthorized);
        panelMessages.setVisible(isAuthorized);
        panelMessages.setManaged(isAuthorized);
    }

    public boolean authorize(String login, String password) {
        try {
            //TODO:
            network.authorize(login, password);
        } catch (AuthException e) {
            showError("Authorization ERROR", e.getMessage());
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private void okBttnCLicked(ActionEvent event) {
        try {
            network = new Network("localhost", 7777);
            network.setIncomeMessageHandler(this::printMessage);
            //TODO: проверка на пустые значения
            setAuthorized(authorize(txtLogin.getText(), txtPassword.getText()));
        } catch (IOException e) {
            showError("Network ERROR", "No network connection");
        }
        txtPassword.clear();
    }

    private void cancelBttnClicked(ActionEvent event) {
        System.exit(1);
    }

    private void showError(String title, String errorMessage) {
        errorAlert.setTitle(title);
        errorAlert.setContentText(errorMessage);
        errorAlert.setHeaderText(null);
        errorAlert.showAndWait();
    }
}

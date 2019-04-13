package ru.geekbrains.lesson4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("resources/fxml/main_window.fxml"));
        primaryStage.setTitle("GeekChat");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinHeight(380.0);
        primaryStage.setMinWidth(380.0);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
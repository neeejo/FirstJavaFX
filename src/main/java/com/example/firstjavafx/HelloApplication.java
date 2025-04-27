package com.example.firstjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(
                Objects.requireNonNull(
                        getClass().getResource("root.fxml")
                )
        );

        Scene scene = new Scene(root);

        String css = Objects.requireNonNull(
                getClass().getResource("/stylesheet.css")
        ).toExternalForm();
        scene.getStylesheets().add(css);

        Image icon = new Image(
                Objects.requireNonNull(
                        getClass().getResourceAsStream("/assets/icon.png")
                )
        );

        stage.getIcons().add(icon);
        stage.setTitle("First JavaFX !!!");

        stage.setResizable(true);

        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");

        stage.setScene(scene);
        stage.show();
    }
}
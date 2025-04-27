package com.example.firstjavafx.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MediaPlayerController {

    @FXML
    private AnchorPane box;
    @FXML
    private MediaView mediaPlayer;
    @FXML
    private Button play, pause, reset;

    private MediaPlayer instance;
    private File movieFile;


    public void initialize() {
        if (movieFile != null && movieFile.exists()) {
            Media media = new Media(movieFile.toURI().toString());
            instance = new MediaPlayer(media);
            mediaPlayer.setMediaPlayer(instance);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(2), event -> instance.play())
            );
            timeline.setCycleCount(1);
            timeline.play();
        }else {
            System.out.println("File null in Initialize");
        }
    }

    public void setMovieFile(File movieFile) {
        this.movieFile = movieFile;
    }

    public void playMedia(ActionEvent actionEvent) {
        if (instance != null) {
            instance.play();
        }else{
            System.out.println("instance null playMedia");
        }
    }

    public void pauseMedia(ActionEvent actionEvent) {
        if (instance != null) {
            instance.pause();
        }else{
            System.out.println("instance null pauseMedia");
        }
    }

    public void resetMedia(ActionEvent actionEvent) {
        if (instance != null) {
            instance.stop();
        }else{
            System.out.println("instance null resetMedia");
        }
    }
}

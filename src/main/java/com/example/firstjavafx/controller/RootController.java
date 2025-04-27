package com.example.firstjavafx.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import ws.schild.jave.EncoderException;
import ws.schild.jave.InputFormatException;
import ws.schild.jave.MultimediaObject;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


public class RootController implements Initializable {
    @FXML
    private AnchorPane container;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TilePane movieCards;

    @FXML
    private Label emptyFolder = new Label("The folder is empty");

    private final String folderPath = "F:\\"; //test

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scrollPane.setStyle("-fx-background: transparent;");
        loadVideoFiles();

    }

    private void loadVideoFiles() {
        var folder = new File(folderPath);
        List<File> movies = Arrays.stream(Objects.requireNonNull(
                    folder.listFiles()
                    ))
                    .filter(this::isVideoType)
                    .toList();

        if (movies.isEmpty()) {
            emptyFolder.setVisible(true);
            scrollPane.setContent(emptyFolder);
        } else {
            emptyFolder.setVisible(false);
            scrollPane.setContent(movieCards);
            movies.forEach(movie -> {
                var movieCard = createMovieCard(movie);
                movieCards.getChildren().add(movieCard);
            });

        }

    }

    private boolean isVideoType(File file) {
        if(!file.isFile()) {
            return false;
        }
        var path = Paths.get(file.getAbsolutePath());
        var mimeType = "";
        try {
            mimeType = Objects.requireNonNull(Files.probeContentType(path));
        }catch (Exception e) {
            System.out.println("Errore isVideoType" + e.getMessage());
            return false;
        }
        return mimeType.startsWith("video/");
    }

    private VBox createMovieCard(File movie) {
        var vbox = new VBox();
        vbox.setPadding(new Insets(5));


        var imageView = new ImageView(
                new Image(
                        Objects.requireNonNull(
                                getClass().getResourceAsStream("/assets/woods.png")
                        )
                )
        );
        imageView.setFitHeight(180);
        imageView.setFitWidth(180);
        var rating = new Label("Rating: ******* 7/10");
        var title = new Label("Titolo: \n" + movie.getName());
        var cast = new Label("Cast: Pippo, Pluto, Paperino\n");
        var description = new Label("Trama: Ho visto Pippo che bacia Pluto che bacia Paperino che bacia Pippo\n");
        var length = new Label("Durata: 2:01:41");
        buildRows(movie, imageView.getImage(), rating, title, cast, description, length);
        vbox.getChildren().addAll(
                imageView,
                rating,
                title,
                cast,
                description,
                length
        );
        return vbox;
    }

    private void buildRows(File file,
                           Image image,
                           Label rating,
                           Label title,
                           Label cast,
                           Label description,
                           Label length) {
        var multimediaObject = new MultimediaObject(file);

        try {
            var info = multimediaObject.getInfo();
            length = getLengthLabel(info.getDuration());


        } catch (InputFormatException e) {
            System.out.println("Errore InputForm in buildRows {}" + e.getMessage());
        } catch (EncoderException e) {
            System.out.println("Errore Encoder in buildRows {}" + e.getMessage());
        }
    }

    private Label getLengthLabel(long duration) {
        var durationSeconds = duration / 1000;
        var hours = durationSeconds / 3600;
        var minutes = (durationSeconds % 3600) / 60;
        var seconds = durationSeconds % 60;
        return new Label(String.format("Durata: %02d:%02d:%02d", hours, minutes, seconds));

    }
}

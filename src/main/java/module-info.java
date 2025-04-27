module com.example.firstjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires jave.core;
    requires javafx.media;


    opens com.example.firstjavafx to javafx.fxml;
    opens com.example.firstjavafx.controller to javafx.fxml;


    exports com.example.firstjavafx;
}
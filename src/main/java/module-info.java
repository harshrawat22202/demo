module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires javafx.media;
    requires java.desktop;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}
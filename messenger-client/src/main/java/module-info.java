module com.example.messengerclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens clientSample to javafx.fxml;
    exports clientSample;
}
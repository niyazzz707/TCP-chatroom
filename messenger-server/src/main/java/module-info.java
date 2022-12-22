module com.example.messengerserver {
    requires javafx.controls;
    requires javafx.fxml;


    opens messengerServer to javafx.fxml;
    exports messengerServer;
}
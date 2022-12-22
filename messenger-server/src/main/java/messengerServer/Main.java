package messengerServer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(root);
        Image image=new Image("C:\\Users\\niyaz\\IdeaProjects\\messenger-server\\src\\main\\resources\\img\\icon-server.png");
        stage.getIcons().add(image);
        stage.setTitle("Server");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
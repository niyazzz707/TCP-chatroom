package clientSample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    @FXML
    private TextField messageTextField;

    @FXML
    private ScrollPane scrollPainMain;

    @FXML
    private Button sendButton;

    @FXML
    private VBox vBoxMessages;

    private Client client;

    public void sendMessage() {
        String messageToSend=messageTextField.getText();

        if(!messageToSend.isEmpty()) {
            HBox hBox=new HBox();
            hBox.setAlignment(Pos.CENTER_RIGHT);

            hBox.setPadding(new Insets(5,5,5,10));
            Text text= new Text(messageToSend);

            TextFlow textFlow=new TextFlow(text);
            textFlow.setStyle("-fx-background-color:  #40008c;" +
                    "-fx-background-radius: 20px;" +
                    "-fx-font-family: 'Roboto Light';" +
                    "-fx-font-size: 14px");


            textFlow.setPadding(new Insets(5,10,5,10));
            text.setFill(Color.color(0.934,0.945,0.996));

            hBox.getChildren().add(textFlow);
            vBoxMessages.getChildren().add(hBox);

            client.sendMessageToServer(messageToSend);
            messageTextField.clear();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            client=new Client(new Socket("localhost",4444));
            System.out.println("Connected to server");
        } catch (IOException e) {
            e.printStackTrace();
        }

        vBoxMessages.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                scrollPainMain.setVvalue((double) t1);
            }
        });

        client.receiveMessageFromServer(vBoxMessages);

        sendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                sendMessage();
            }
        });

            messageTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    if (keyEvent.getCode() == KeyCode.ENTER) {
                        sendMessage();
                    }
                }
            });
    }

    public static void addLabel(String messageFromServer, VBox vBox) {
        HBox hBox=new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5,5,5,10));
        ImageView imageView=new ImageView();

        Text text=new Text(messageFromServer);
        TextFlow textFlow= new TextFlow(text);

        textFlow.setStyle("-fx-background-color:#808080;" +
                "-fx-background-radius: 20px;" +
                "-fx-font-family: 'Roboto Light';" +
                "-fx-font-size: 14px");

        textFlow.setPadding(new Insets(5,10,5,10));
        text.setFill(Color.WHITE);
        hBox.getChildren().add(textFlow);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vBox.getChildren().add(hBox);
            }
        });
    }
}
package Main;

import Auth.LoginController;
import Network.TCPConnection;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    TCPConnection conn;

    @Override
    public void start(Stage primaryStage) throws Exception{
        conn = TCPConnection.getInstance();
        TCPConnection.setHost("127.0.0.1");
        TCPConnection.setPort(44901);
        TCPConnection.getInstance().initSocket();

        Parent root = FXMLLoader.load(getClass().getResource("../Auth/LoginForm.fxml"));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

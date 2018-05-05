package Registration;

import Network.TCPConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Andrew on 02.05.2018.
 */
public class RegistrationController implements Initializable{

    @FXML
    private Button btn_register;

    @FXML
    private TextField txtField_login;

    @FXML
    private TextField txtField_fio;

    @FXML
    private TextField txtField_mail;

    @FXML
    private PasswordField passField_pass;

    @FXML
    private PasswordField passField_passSecur;

    public RegistrationController() {
        System.out.println("register form");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_register.setOnAction(e -> registerClient());
    }

    private void registerClient(){
        System.out.println("new client");
        try {
            TCPConnection.getInstance().send("select * from fucky");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

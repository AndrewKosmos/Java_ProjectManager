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
        try {
            if(passField_pass.getText().equals(passField_passSecur.getText())){
                TCPConnection.getInstance().send("insert into mupp_user(login,pass,fullname,email)" +
                        "values (\"" + txtField_login.getText() + "\"," +
                        "\"" + passField_pass.getText() + "\", \"" +
                        txtField_fio.getText() + "\",\"" +
                        txtField_mail.getText() + "\");");
                System.out.println("USER CREATED!");
            }
            else{
                System.out.println("PASSWORD NOT CONFIRMED!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

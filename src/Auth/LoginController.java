package Auth;

import Network.TCPConnection;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{
    @FXML
    private Button btn_close;

    @FXML
    private Label managerLabel;

    @FXML
    private Button btn_login;

    @FXML
    private Button btn_register;

    @FXML
    private TextField login_text;

    @FXML
    private PasswordField password_text;

    public LoginController() {
    }

    @FXML
    protected void on_btn_close_clicked(MouseEvent event){
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_register.setOnAction(e -> createRegistrationForm());
        btn_login.setOnAction(e -> signUp());
    }

    private void signUp() {
        String query = "select count(*) from mupp_user where login=\"" + login_text.getText() + "\"" +
                        " and pass=\"" + password_text.getText() + "\";";
        try {
            String result = TCPConnection.getInstance().sendAndRecieve(query);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createRegistrationForm(){
        try {
            Stage regWin = new Stage();
            regWin.initModality(Modality.APPLICATION_MODAL);
            regWin.setTitle("Регистрация");
            regWin.setWidth(300);
            regWin.setHeight(500);

            Parent root = FXMLLoader.load(getClass().getResource("../Registration/RegistrationForm.fxml"));
            regWin.setScene(new Scene(root,300,520));
            regWin.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

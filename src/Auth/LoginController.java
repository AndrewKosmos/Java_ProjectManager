package Auth;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    public LoginController() {
    }

    @FXML
    protected void on_btn_close_clicked(MouseEvent event){
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_register.setOnAction(e -> createRegistrationForm());
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

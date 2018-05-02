package Auth;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class LoginController {
    @FXML
    private Button btn_close;

    @FXML
    private Label managerLabel;

    @FXML
    private void initialize(){

    }

    @FXML
    protected void on_btn_close_clicked(MouseEvent event){
        Platform.exit();
    }
}

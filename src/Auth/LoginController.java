package Auth;

import Main.Main;
import Main.MainWinController;
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
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

import javafx.util.Callback;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
            //System.out.println(result);
            Object resultJson = new JSONParser().parse(result);
            JSONObject resultObject = (JSONObject)resultJson;
            JSONArray resultsArray = (JSONArray)resultObject.get("result");
            JSONObject countObj = (JSONObject) resultsArray.get(0);
            String countStr = (String) countObj.get("count(*)");
            int count = Integer.parseInt(countStr);

            if(count == 0){
                System.out.println("NO SUCH USER");
            }
            if(count == 1){
                System.out.println("SIGNED UP");
                Stage mainWindow = new Stage();
                mainWindow.setTitle("Project Manager");

                Map<Class, Callable<?>> creators = new HashMap<>();
                creators.put(MainWinController.class, new Callable<MainWinController>() {

                    @Override
                    public MainWinController call() throws Exception {
                        return new MainWinController(login_text.getText());
                    }

                });

                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Main/MainWinForm.fxml"));
                loader.setControllerFactory(new Callback<Class<?>, Object>() {

                    @Override
                    public Object call(Class<?> param) {
                        Callable<?> callable = creators.get(param);
                        if (callable == null) {
                            try {
                                // default handling: use no-arg constructor
                                return param.newInstance();
                            } catch (InstantiationException | IllegalAccessException ex) {
                                throw new IllegalStateException(ex);
                            }
                        } else {
                            try {
                                return callable.call();
                            } catch (Exception ex) {
                                throw new IllegalStateException(ex);
                            }
                        }
                    }
                });

                Parent root = loader.load();
                mainWindow.setScene(new Scene(root,864,566));
                mainWindow.show();

                Stage primary = (Stage) btn_login.getScene().getWindow();
                primary.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
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

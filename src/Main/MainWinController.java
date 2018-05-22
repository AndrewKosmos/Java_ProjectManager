package Main;

import Models.UserModel;
import Network.TCPConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Andrew on 10.05.2018.
 */
public class MainWinController implements Initializable {

    @FXML
    private BorderPane borderPane_root;

    @FXML
    private Label label_fio;

    @FXML
    private Button btn_showTasks;

    @FXML
    private ImageView imgView_avatar;

    private final String user_login;
    public UserModel currentUser;

    public MainWinController(String login){
        this.user_login = login;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label_fio.setText(getFIO());
        btn_showTasks.setOnAction(e -> showTasksScene());
    }

    private void showTasksScene() {
        System.out.println("Show tasks!");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("TaskScreen/TaskScreen.fxml"));
            borderPane_root.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFIO(){
        String loginStr = "User";
        String query = "select * from mupp_user where login=\"" + user_login + "\";";
        try {
            String result = TCPConnection.getInstance().sendAndRecieve(query);
            Object resultJson = null;
            try {
                resultJson = new JSONParser().parse(result);
                JSONObject resultObject = (JSONObject)resultJson;
                JSONArray resultsArray = (JSONArray)resultObject.get("result");
                JSONObject loginObj = (JSONObject) resultsArray.get(0);
                currentUser = UserModel.fromJson(loginObj);
                loginStr = currentUser.getUserFullname();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loginStr;
    }

    public String getUser_login() {
        return user_login;
    }
}

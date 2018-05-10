package Main;

import Network.TCPConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
    private Label label_fio;

    @FXML
    private ImageView imgView_avatar;

    private final String user_login;

    public MainWinController(String login){
        this.user_login = login;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label_fio.setText(getFIO());
    }

    private String getFIO(){
        String loginStr = "User";
        String query = "select fullname from mupp_user where login=\"" + user_login + "\";";
        try {
            String result = TCPConnection.getInstance().sendAndRecieve(query);
            Object resultJson = new JSONParser().parse(result);
            JSONObject resultObject = (JSONObject)resultJson;
            JSONArray resultsArray = (JSONArray)resultObject.get("result");
            JSONObject loginObj = (JSONObject) resultsArray.get(0);
            loginStr = (String) loginObj.get("fullname");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return loginStr;
    }
}

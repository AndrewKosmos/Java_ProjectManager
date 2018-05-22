package Models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by Andrew on 11.05.2018.
 */
public class UserModel {

    private int userId;
    private String userLogin;
    private String userPass;
    private String userFullname;
    private String userEmail;

    public UserModel(){}

    public UserModel(int userId,String userLogin,String userPass, String userFullname,String userEmail){
        this.userId = userId;
        this.userLogin = userLogin;
        this.userPass = userPass;
        this.userFullname = userFullname;
        this.userEmail = userEmail;
    }

    public UserModel(String userLogin,String userPass, String userFullname,String userEmail){
        this.userLogin = userLogin;
        this.userPass = userPass;
        this.userFullname = userFullname;
        this.userEmail = userEmail;
    }

    public static UserModel fromJson(JSONObject loginObj){
        UserModel model = new UserModel();
        model.userId = Integer.parseInt((String)loginObj.get("user_id"));
        model.userLogin = (String) loginObj.get("login");
        model.userPass = (String) loginObj.get("pass");
        model.userFullname = (String) loginObj.get("fullname");
        model.userEmail = (String) loginObj.get("email");
//        Object resultJson = null;
//        try {
//            resultJson = new JSONParser().parse(message);
//            JSONObject resultObject = (JSONObject)resultJson;
//            JSONArray resultsArray = (JSONArray)resultObject.get("result");
//            JSONObject loginObj = (JSONObject) resultsArray.get(0);
//            model.userId = Integer.parseInt((String)loginObj.get("user_id"));
//            model.userLogin = (String) loginObj.get("login");
//            model.userPass = (String) loginObj.get("pass");
//            model.userFullname = (String) loginObj.get("fullname");
//            model.userEmail = (String) loginObj.get("email");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        return model;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserFullname() {
        return userFullname;
    }

    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "login" + userLogin + "fullname" + userFullname;
    }
}

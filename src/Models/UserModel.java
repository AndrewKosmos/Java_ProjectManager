package Models;

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
}

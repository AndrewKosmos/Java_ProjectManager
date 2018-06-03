package Main.ProjectScreen;

import Main.MainWinController;
import Network.TCPConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewProjectSreenController implements Initializable {

    @FXML
    private Button btn_projectNew;
    @FXML
    private TextField tf_projName;
    @FXML
    private TextField tf_projectGit;
    @FXML
    private TextArea ta_projectDesc;
    private int managerId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        managerId = MainWinController.currentUser.getUserId();
        btn_projectNew.setOnAction(e -> createProject());
    }

    private void createProject() {
        String insertQuery = "insert into mupp_project(project_name,project_description,project_percent,manager,git_url) " +
                "values(\"" + tf_projName.getText() + "\",\"" + ta_projectDesc.getText() + "\",0," + managerId + ",\"" +
                tf_projectGit.getText() + "\");";
        try {
            TCPConnection.getInstance().send(insertQuery);
            Stage stage = (Stage) btn_projectNew.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

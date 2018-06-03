package Main.TaskScreen;

import Network.TCPConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class newTaskWinController implements Initializable {

    @FXML
    private TextField tf_finishDate;
    @FXML
    private TextField tf_assignee;
    @FXML
    private TextField tf_projId;
    @FXML
    private TextArea ta_desc;
    @FXML
    private TextField tf_name;
    @FXML
    private Button btn_addTask;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_addTask.setOnAction(e -> createTask());
    }

    private void createTask() {
        String insertQuery = "insert into mupp_task(name,description,project_id,assignee,start_date,finish_date) " +
                "values(\"" + tf_name.getText() + "\",\"" + ta_desc.getText() + "\"," + tf_projId.getText() + "," + tf_assignee.getText() + "," +
                (int)LocalDate.now().toEpochDay() + "," + tf_finishDate.getText() + ");";
        try {
            TCPConnection.getInstance().send(insertQuery);
            Stage stage = (Stage) btn_addTask.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

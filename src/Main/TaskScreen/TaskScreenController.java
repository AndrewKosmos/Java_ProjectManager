package Main.TaskScreen;

import Main.TaskCard.TaskCardController;
import Models.TaskCardModel;
import Models.TaskModel;
import Network.TCPConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Andrew on 12.05.2018.
 */
public class TaskScreenController implements Initializable {

    @FXML
    private ListView<TaskCardModel> listView_tasks;
    @FXML
    private Label taskNameLabel;
    @FXML
    private StackPane tasksScreenFiller;
    @FXML
    private Label projNameLabel;
    @FXML
    private Label projManagerLabel;
    @FXML
    private TextArea taskUpdTextArea;
    @FXML
    private Slider taskPercentProgressbar;
    @FXML
    private ComboBox<String> taskPriorityCBX;
    @FXML
    private ComboBox<String> taskStatusCBX;
    @FXML
    private Button taskUpdateBtn;

    public ObservableList<TaskCardModel> tasksList = FXCollections.observableArrayList();
    public ObservableList<String> priorityList = FXCollections.observableArrayList("нормально", "срочно");

    public TaskScreenController(){}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String query = "select * from mupp_task t left join mupp_project u on t.project_id=u.project_id where t.project_id=1;";

        try {
            String result = TCPConnection.getInstance().sendAndRecieve(query);
            System.out.println(result);
            Object resultJson = new JSONParser().parse(result);
            JSONObject resultObject = (JSONObject)resultJson;
            JSONArray resultsArray = (JSONArray)resultObject.get("result");
            for(Object e : resultsArray){
                JSONObject obj = (JSONObject) e;
                TaskCardModel model = new TaskCardModel(obj);
                tasksList.add(model);
            }

            listView_tasks.setItems(tasksList);

            listView_tasks.setCellFactory(new TaskCellFactory());
            listView_tasks.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TaskCardModel>() {
                @Override
                public void changed(ObservableValue<? extends TaskCardModel> observable, TaskCardModel oldValue, TaskCardModel newValue) {
                    if(oldValue == null){
                        tasksScreenFiller.setVisible(false);
                    }
                    if(newValue == null){
                        tasksScreenFiller.setVisible(true);
                    }
                    setTaskFields(newValue);
                }
            });
            taskPriorityCBX.setItems(priorityList);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void setTaskFields(TaskCardModel value){
        taskNameLabel.setText(value.getTask().getTaskName());
        projNameLabel.setText("Проект: " + value.getProject().getProjectName());

        String query = "select fullname from mupp_user where user_id=" + value.getProject().getProjectManager() + ";";
        String managerName = "Undefined manager";
        try{
            String result = TCPConnection.getInstance().sendAndRecieve(query);
            System.out.println(result);
            Object resultJson = new JSONParser().parse(result);
            JSONObject resultObject = (JSONObject)resultJson;
            JSONArray resultsArray = (JSONArray)resultObject.get("result");
            JSONObject obj = (JSONObject) resultsArray.get(0);
            managerName = (String) obj.get("fullname");
        }catch (IOException e){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        projManagerLabel.setText("Наблюдатель: " + managerName);
        taskUpdTextArea.setText(value.getTask().getTaskUpdateText());
        taskPercentProgressbar.setValue((double) value.getTask().getTaskPercent());
        taskPriorityCBX.getSelectionModel().select(value.getTask().getTaskPriority());
    }
}

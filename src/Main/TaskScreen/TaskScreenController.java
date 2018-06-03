package Main.TaskScreen;

import Main.MainWinController;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
    @FXML
    private Button btn_rmTask;
    @FXML
    private Button btn_addTask;

    private TaskCardModel currentModel;

    public ObservableList<TaskCardModel> tasksList = FXCollections.observableArrayList();
    public ObservableList<String> priorityList = FXCollections.observableArrayList("нормально", "срочно");
    public ObservableList<String> statusList = FXCollections.observableArrayList("в процессе", "приостановлена", "закрыта");

    public TaskScreenController(){}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_addTask.setOnAction(e -> newTaskWin());
        String query = "select * from mupp_task t left join mupp_project u on t.project_id=u.project_id where t.assignee=" + MainWinController.currentUser.getUserId() + ";";

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
                    currentModel = newValue;
                    setTaskFields(newValue);
                }
            });
            taskPriorityCBX.setItems(priorityList);
            taskStatusCBX.setItems(statusList);
            taskUpdateBtn.setOnAction(e -> updateTask());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void newTaskWin() {
        try {
            Stage newTaskWin = new Stage();
            newTaskWin.initModality(Modality.APPLICATION_MODAL);
            newTaskWin.setTitle("Создание новой задачи");
            newTaskWin.setWidth(300);
            newTaskWin.setHeight(400);

            Parent root = FXMLLoader.load(getClass().getResource("newTaskWin.fxml"));
            newTaskWin.setScene(new Scene(root,300,400));
            newTaskWin.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateTask() {
        for(int i = 0; i < tasksList.size(); i++){
            if(currentModel == tasksList.get(i)){
                tasksList.get(i).getTask().setTaskPercent((int)taskPercentProgressbar.getValue());
                tasksList.get(i).getTask().setTaskUpdateText(taskUpdTextArea.getText());
                tasksList.get(i).getTask().setTaskPriority(taskPriorityCBX.getSelectionModel().getSelectedIndex());
                tasksList.get(i).getTask().setTaskStatus(taskStatusCBX.getSelectionModel().getSelectedIndex());
                tasksList.get(i).getTask().setTaskLastUpdateDate((int)LocalDate.now().toEpochDay());
                listView_tasks.refresh();
                String sendQuery = "update mupp_task set percent=" + tasksList.get(i).getTask().getTaskPercent() +
                        ", update_text=\"" + tasksList.get(i).getTask().getTaskUpdateText() + "\"," +
                        "priority=" + tasksList.get(i).getTask().getTaskPriority() + "," +
                        "status=" + tasksList.get(i).getTask().getTaskStatus() + "," +
                        "last_update_date=" + tasksList.get(i).getTask().getTaskLastUpdateDate() + " where " +
                        "task_id=" + tasksList.get(i).getTask().getTaskId() + ";";
                try {
                    TCPConnection.getInstance().send(sendQuery);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
        taskStatusCBX.getSelectionModel().select(value.getTask().getTaskStatus());
    }
}

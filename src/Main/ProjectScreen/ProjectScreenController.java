package Main.ProjectScreen;

import Main.MainWinController;
import Main.TaskScreen.TaskCellFactory;
import Models.ProjectModel;
import Models.TaskCardModel;
import Models.TaskModel;
import Network.TCPConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Created by Andrew on 12.05.2018.
 */
public class ProjectScreenController implements Initializable {

    @FXML
    private ListView<ProjectModel> listView_projects;
    @FXML
    private Label projectNameLabel;
    @FXML
    private TextArea projectDescText;
    @FXML
    private TextField projectGitText;
    @FXML
    private StackPane tasksScreenFiller;
    @FXML
    private ListView<TaskModel> projectTasksLV;
    @FXML
    private Button taskUpdateBtn;
    @FXML
    private Button addProjBtn;

    private ProjectModel currentModel;

    public ObservableList<ProjectModel> projectsList = FXCollections.observableArrayList();
    public ObservableList<String> projectsNamesList = FXCollections.observableArrayList();
    public ObservableList<TaskModel> tasksList = FXCollections.observableArrayList();

    public ProjectScreenController(){}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addProjBtn.setOnAction(e -> getNewProjectScreen());
        String query = "select * from mupp_project;";
        try {
            String result = TCPConnection.getInstance().sendAndRecieve(query);
            Object resultJson = new JSONParser().parse(result);
            JSONObject resultObject = (JSONObject)resultJson;
            JSONArray resultsArray = (JSONArray)resultObject.get("result");
            for(Object e : resultsArray){
                JSONObject obj = (JSONObject) e;
                ProjectModel model = ProjectModel.fromJson(obj);
                projectsList.add(model);
                projectsNamesList.add(model.toString());
            }

            listView_projects.setItems(projectsList);
            listView_projects.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProjectModel>() {
                @Override
                public void changed(ObservableValue<? extends ProjectModel> observable, ProjectModel oldValue, ProjectModel newValue) {
                    if(oldValue == null){
                        tasksScreenFiller.setVisible(false);
                    }
                    if(newValue == null){
                        tasksScreenFiller.setVisible(true);
                    }
                    currentModel = newValue;
                    setProjectFields(currentModel);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void getNewProjectScreen() {
        System.out.println("NEW PROJECT SCREEN");
    }

    private void setProjectFields(ProjectModel value){
        projectNameLabel.setText(value.getProjectName());
        projectDescText.setText(value.getProjectDescription());
        projectGitText.setText(value.getProjectGitUrl());

        tasksList.clear();
        String query = "select * from mupp_task where project_id=" + currentModel.getProjectId() + ";";
        String result = null;
        try {
            result = TCPConnection.getInstance().sendAndRecieve(query);
            Object resultJson = new JSONParser().parse(result);
            JSONObject resultObject = (JSONObject)resultJson;
            JSONArray resultsArray = (JSONArray)resultObject.get("result");
            for(Object e : resultsArray){
                JSONObject obj = (JSONObject) e;
                TaskModel model = TaskModel.fromJson(obj);
                tasksList.add(model);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        projectTasksLV.setItems(tasksList);

    }
}

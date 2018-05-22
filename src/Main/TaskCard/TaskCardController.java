package Main.TaskCard;

import Models.TaskCardModel;
import Models.TaskModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by Andrew on 13.05.2018.
 */
public class TaskCardController extends ListCell<TaskCardModel>{
    @FXML
    private Label label_taskName;
    @FXML
    private AnchorPane taskRoot;
    @FXML
    private Label taskCardDesc;
    @FXML
    private Label taskCardStatus;
    @FXML
    private ProgressBar taskCardProgress;
    @FXML
    private DatePicker taskCardUpdateDate;
    @FXML
    private Label taskCardUpdText;

    public TaskCardController(){
        loadFXML();
    }

    private void loadFXML(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TaskCardView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateItem(TaskCardModel item, boolean empty) {
        super.updateItem(item, empty);

        if(empty){
            setText(null);
            setGraphic(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
        else{
            //label_taskName.setText(item.getTask().getTaskName());
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            label_taskName.setText(item.getTask().getTaskName() + " #" + item.getTask().getTaskId());
            taskCardDesc.setText(item.getTask().getTaskDescription());
            int status = item.getTask().getTaskStatus();
            String statusStr;
            switch (status){
                case TaskModel.TASK_STATUS_WIP: {
                    statusStr = "В процессе выполнения";
                    break;
                }
                case TaskModel.TASK_STATUS_PAUSED:{
                    statusStr = "Приостановлена";
                    break;
                }
                case TaskModel.TASK_STATUS_CLOSED:{
                    statusStr = "Закрыта";
                    break;
                }
                default: {
                    statusStr = "UNDEDINED STATUS";
                    break;
                }
            }

            taskCardStatus.setText(statusStr);
            System.out.println(item.getTask().getTaskPercent() / 100);
            taskCardProgress.setProgress((double) item.getTask().getTaskPercent() / 100);
            taskCardUpdateDate.setValue(LocalDate.ofEpochDay((long)item.getTask().getTaskLastUpdateDate()));
            taskCardUpdText.setText(item.getTask().getTaskUpdateText());
            setGraphic(taskRoot);
        }
    }
}

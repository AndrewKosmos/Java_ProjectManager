package Models;

import org.json.simple.JSONObject;

/**
 * Created by Andrew on 13.05.2018.
 */
public class TaskCardModel {
    private TaskModel task;
    private ProjectModel project;
    //private UserModel manager;

    public TaskCardModel(JSONObject obj){
        task = TaskModel.fromJson(obj);
        project = ProjectModel.fromJson(obj);
    }

    public TaskModel getTask() {
        return task;
    }

    public void setTask(TaskModel task) {
        this.task = task;
    }

    public ProjectModel getProject() {
        return project;
    }

    public void setProject(ProjectModel project) {
        this.project = project;
    }
}

package Models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by Andrew on 11.05.2018.
 */
public class TaskModel {
    public static final int TASK_STATUS_WIP = 0;
    public static final int TASK_STATUS_PAUSED = 1;
    public static final int TASK_STATUS_CLOSED = 2;

    public static final int TASK_PRIORITY_NORMAL = 0;
    public static final int TASK_PRIORITY_MAJOR = 1;

    private int taskId;
    private int taskPercent;
    private int taskProjectId;
    private int taskAssignee;
    private int taskStartDate;
    private int taskFinishDate;
    private int taskLastUpdateDate;
    private int taskStatus;
    private int taskPriority;
    private String taskName;
    private String taskDescription;
    private String taskUpdateText;

    public TaskModel(){}

    public TaskModel(int taskId, String taskName, String taskDescription, String taskUpdateText,
                     int taskPercent, int taskProjectId, int taskAssignee, int taskStartDate,
                     int taskFinishDate, int taskLastUpdateDate, int taskStatus, int taskPriority){
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskUpdateText = taskUpdateText;
        this.taskPercent = taskPercent;
        this.taskProjectId = taskProjectId;
        this.taskAssignee = taskAssignee;
        this.taskStartDate = taskStartDate;
        this.taskFinishDate = taskFinishDate;
        this.taskLastUpdateDate = taskLastUpdateDate;
        this.taskStatus = taskStatus;
        this.taskPriority = taskPriority;
    }

    public TaskModel(String taskName, String taskDescription, String taskUpdateText,
                     int taskPercent, int taskProjectId, int taskAssignee, int taskStartDate,
                     int taskFinishDate, int taskLastUpdateDate, int taskStatus, int taskPriority){
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskUpdateText = taskUpdateText;
        this.taskPercent = taskPercent;
        this.taskProjectId = taskProjectId;
        this.taskAssignee = taskAssignee;
        this.taskStartDate = taskStartDate;
        this.taskFinishDate = taskFinishDate;
        this.taskLastUpdateDate = taskLastUpdateDate;
        this.taskStatus = taskStatus;
        this.taskPriority = taskPriority;
    }

    public static TaskModel fromJson(JSONObject taskObj){
        TaskModel model = new TaskModel();
        model.taskId = Integer.parseInt((String) taskObj.get("task_id"));
        model.taskName = (String) taskObj.get("name");
        model.taskDescription = (String) taskObj.get("description");
        model.taskUpdateText = (String) taskObj.get("update_text");
        model.taskPercent = Integer.parseInt((String) taskObj.get("percent"));
        model.taskProjectId = Integer.parseInt((String) taskObj.get("project_id"));
        model.taskAssignee = Integer.parseInt((String)taskObj.get("assignee"));
        model.taskStartDate = Integer.parseInt((String) taskObj.get("start_date"));
        model.taskFinishDate = Integer.parseInt((String) taskObj.get("finish_date"));
        model.taskLastUpdateDate = Integer.parseInt((String) taskObj.get("last_update_date"));
        model.taskStatus = Integer.parseInt((String) taskObj.get("status"));
        model.taskPriority = Integer.parseInt((String) taskObj.get("priority"));


//        Object resultJson = null;
//        try {
//            resultJson = new JSONParser().parse(message);
//            JSONObject resultObject = (JSONObject)resultJson;
//            JSONArray resultsArray = (JSONArray)resultObject.get("result");
//            JSONObject taskObj = (JSONObject) resultsArray.get(0);
//            model.taskId = Integer.parseInt((String) taskObj.get("task_id"));
//            model.taskName = (String) taskObj.get("name");
//            model.taskDescription = (String) taskObj.get("description");
//            model.taskUpdateText = (String) taskObj.get("update_text");
//            model.taskPercent = Integer.parseInt((String) taskObj.get("percent"));
//            model.taskProjectId = Integer.parseInt((String) taskObj.get("project_id"));
//            model.taskAssignee = Integer.parseInt((String)taskObj.get("assignee"));
//            model.taskStartDate = Integer.parseInt((String) taskObj.get("start_date"));
//            model.taskFinishDate = Integer.parseInt((String) taskObj.get("finish_date"));
//            model.taskLastUpdateDate = Integer.parseInt((String) taskObj.get("last_update_date"));
//            model.taskStatus = Integer.parseInt((String) taskObj.get("status"));
//            model.taskPriority = Integer.parseInt((String) taskObj.get("priority"));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        return model;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getTaskPercent() {
        return taskPercent;
    }

    public void setTaskPercent(int taskPercent) {
        this.taskPercent = taskPercent;
    }

    public int getTaskProjectId() {
        return taskProjectId;
    }

    public void setTaskProjectId(int taskProjectId) {
        this.taskProjectId = taskProjectId;
    }

    public int getTaskAssignee() {
        return taskAssignee;
    }

    public void setTaskAssignee(int taskAssignee) {
        this.taskAssignee = taskAssignee;
    }

    public int getTaskStartDate() {
        return taskStartDate;
    }

    public void setTaskStartDate(int taskStartDate) {
        this.taskStartDate = taskStartDate;
    }

    public int getTaskFinishDate() {
        return taskFinishDate;
    }

    public void setTaskFinishDate(int taskFinishDate) {
        this.taskFinishDate = taskFinishDate;
    }

    public int getTaskLastUpdateDate() {
        return taskLastUpdateDate;
    }

    public void setTaskLastUpdateDate(int taskLastUpdateDate) {
        this.taskLastUpdateDate = taskLastUpdateDate;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public int getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(int taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskUpdateText() {
        return taskUpdateText;
    }

    public void setTaskUpdateText(String taskUpdateText) {
        this.taskUpdateText = taskUpdateText;
    }

    @Override
    public String toString() {
        return taskName;
    }
}

package Models;

/**
 * Created by Andrew on 11.05.2018.
 */
public class TaskModel {
    public static final int TASK_STATUS_WIP = 0;
    public static final int TASK_STATUS_PAUSED = 1;
    public static final int TASK_STATUS_CLOSED = 2;

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
}

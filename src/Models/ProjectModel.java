package Models;

import org.json.simple.JSONObject;

/**
 * Created by Andrew on 11.05.2018.
 */
public class ProjectModel {
    private int projectId;
    private int projectPercent;
    private int projectManager;
    private String projectName;
    private String projectDescription;
    private String projectGitUrl;

    public ProjectModel(){}

    public ProjectModel(int projectId, String projectName, String projectDescription,
                        int projectPercent, int projectManager, String projectGitUrl){
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectPercent = projectPercent;
        this.projectManager = projectManager;
        this.projectGitUrl = projectGitUrl;
    }

    public ProjectModel(String projectName, String projectDescription,
                        int projectPercent, int projectManager, String projectGitUrl){
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectPercent = projectPercent;
        this.projectManager = projectManager;
        this.projectGitUrl = projectGitUrl;
    }

    public static ProjectModel fromJson(JSONObject obj){
        ProjectModel model = new ProjectModel();

        model.projectId = Integer.parseInt((String) obj.get("project_id"));
        model.projectName = (String) obj.get("project_name");
        model.projectDescription = (String) obj.get("project_description");
        model.projectPercent = Integer.parseInt((String)obj.get("project_percent"));
        model.projectManager = Integer.parseInt((String) obj.get("manager"));
        model.projectGitUrl = (String) obj.get("git_url");

        return model;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getProjectPercent() {
        return projectPercent;
    }

    public void setProjectPercent(int projectPercent) {
        this.projectPercent = projectPercent;
    }

    public int getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(int projectManager) {
        this.projectManager = projectManager;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectGitUrl() {
        return projectGitUrl;
    }

    public void setProjectGitUrl(String projectGitUrl) {
        this.projectGitUrl = projectGitUrl;
    }

    @Override
    public String toString() {
        return "Project #" + projectId + ": " + projectName;
    }
}

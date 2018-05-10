package Models;

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
}

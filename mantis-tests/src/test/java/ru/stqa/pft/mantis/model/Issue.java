package ru.stqa.pft.mantis.model;

public class Issue {

  private int id;
  private String summary;
  private String description;
  private Project project;
  private String status;
  private String resolution;

  public int getId() {
    return id;
  }

  public Issue withId(int id) {
    this.id = id;
    return this;
  }

  public String getSummary() {
    return summary;
  }

  public Issue withSummary(String summary) {
    this.summary = summary;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Issue withDescription(String description) {
    this.description = description;
    return this;
  }

  public Project getProject() {
    return project;
  }

  public Issue withProject(Project project) {
    this.project = project;
    return this;
  }

  public String getStatus() {
    return status;
  }

  public Issue withStatus(String status) {
    this.status = status;
    return this;
  }

  public String getResolution() {
    return resolution;
  }

  public Issue withResolution(String resolution) {
    this.resolution = resolution;
    return this;
  }

  @Override
  public String toString() {
    return "Issue{" +
            "id=" + id +
            ", summary='" + summary + '\'' +
            ", status='" + status + '\'' +
            ", resolution='" + resolution + '\'' +
            ", description='" + description + '\'' +
            '}';
  }
  private String state;

  public String getState() {
    return state;
  }

  public Issue withState(String state) {
    this.state = state;
    return this;
  }

}


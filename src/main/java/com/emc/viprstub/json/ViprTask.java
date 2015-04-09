package com.emc.viprstub.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 * @author vladislav.trofimov@emc.com
 * @date 18.09.2014
 */
@JsonRootName("task")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ViprTask {

    @JsonProperty("resource")
    private Resource taskResource;

    @JsonProperty("state")
    private String taskState;

    @JsonProperty("start_time")
    private long startTime;

    @JsonProperty("end_time")
    private long endTime;

    @JsonProperty("op_id")
    private String operationId;

    @JsonProperty("link")
    private Link taskLink;

    @JsonProperty("message")
    private String message;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;


    public Resource getTaskResource() {
        return taskResource;
    }

    public void setTaskResource(Resource taskResource) {
        this.taskResource = taskResource;
    }

    public String getTaskState() {
        return taskState;
    }

    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public Link getTaskLink() {
        return taskLink;
    }

    public void setTaskLink(Link taskLink) {
        this.taskLink = taskLink;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
package com.technical.evaluation.technicalevalution.service.DTO;

import java.io.Serializable;

public class AiModelDTO implements Serializable {

    private String model;
    private String taskType;

    public AiModelDTO() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskType() {
        return taskType;
    }
}

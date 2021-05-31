package com.technical.evaluation.technicalevalution.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;

@Document(collection = "aimodel")
public class AiModel implements Serializable{

    private ObjectId id;
    private String model;
    private String taskType;

    public AiModel() {
    }

    public AiModel(String model, String taskType) {
        this.model = model;
        this.taskType = taskType;
    }

    public AiModel(ObjectId id, String model, String taskType) {
        this.id = id;
        this.model = model;
        this.taskType = taskType;
    }
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
}

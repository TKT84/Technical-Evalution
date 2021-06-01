package com.technical.evaluation.technicalevalution.service.DTO;

import org.joda.time.DateTime;

import java.util.Date;

public class TrainingResultDTO {

    private String model;
    private String taskType;
    private Date date;
    private float precision;
    private float recall;
    private float fScore;

    public TrainingResultDTO() {
    }

    public TrainingResultDTO(String model, String taskType, Date date, float precision, float recall, float fScore) {
        this.model = model;
        this.taskType = taskType;
        this.date = date;
        this.precision = precision;
        this.recall = recall;
        this.fScore = fScore;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPrecision() {
        return precision;
    }

    public void setPrecision(float precision) {
        this.precision = precision;
    }

    public float getRecall() {
        return recall;
    }

    public void setRecall(float recall) {
        this.recall = recall;
    }

    public float getfScore() {
        return fScore;
    }

    public void setfScore(float fScore) {
        this.fScore = fScore;
    }
}

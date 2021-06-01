package com.technical.evaluation.technicalevalution.model;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document(collection = "modeltraining")
public class ModelTraining implements Serializable {

    private ObjectId id;
    private AiModel aiModel = new AiModel();
    private Date date;
    private float precision;
    private float recall;
    private float fScore;

    public ModelTraining(AiModel aiModel, Date date, float precision, float recall, float fScore) {
        this.aiModel = aiModel;
        this.date = date;
        this.precision = precision;
        this.recall = recall;
        this.fScore = fScore;
    }

    public ModelTraining() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AiModel getAiModel() {
        return aiModel;
    }

    public void setAiModel(AiModel aiModel) {
        this.aiModel = aiModel;
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

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}

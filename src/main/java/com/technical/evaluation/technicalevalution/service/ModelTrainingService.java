package com.technical.evaluation.technicalevalution.service;


import com.technical.evaluation.technicalevalution.service.DTO.AiModelDTO;
import com.technical.evaluation.technicalevalution.service.DTO.TrainingResultDTO;
import org.joda.time.DateTime;
import java.util.List;

public interface ModelTrainingService {

    public void postTraining (TrainingResultDTO trainingResultDTO);
    public List<TrainingResultDTO> getResultList(DateTime dateTime, Float precision, Float fScore, Float recall, boolean greaterThan, boolean lowerThan, boolean isEquals);
    public void createAiModelData(int dataSize);
    public List<AiModelDTO> getModels(String model, String taskType);
}


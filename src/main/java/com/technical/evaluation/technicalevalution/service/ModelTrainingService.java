package com.technical.evaluation.technicalevalution.service;

import com.technical.evaluation.technicalevalution.service.DTO.AiModelDTO;
import com.technical.evaluation.technicalevalution.service.DTO.TrainingResultDTO;
import java.time.LocalDateTime;
import java.util.List;

public interface ModelTrainingService {

    public void postTraining (TrainingResultDTO trainingResultDTO);
    public List<TrainingResultDTO> getResultList(LocalDateTime startDate, LocalDateTime endDate, Float precision, Float fScore, Float recall, boolean greaterThan, boolean lowerThan, boolean isEquals);
    public void createAiModelData(int dataSize);
    public List<AiModelDTO> getModels(String model, String taskType);
}


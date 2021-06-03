package com.technical.evaluation.technicalevalution.service;

import com.technical.evaluation.technicalevalution.service.DTO.AiModelDTO;
import com.technical.evaluation.technicalevalution.service.DTO.TrainingResultDTO;
import io.swagger.annotations.ApiParam;

import java.time.LocalDateTime;
import java.util.List;

public interface ModelTrainingService {

    public void postTraining (TrainingResultDTO trainingResultDTO);
    public List<TrainingResultDTO> getResultList(LocalDateTime startDate,
                                                 LocalDateTime endDate,
                                                 Float precision,
                                                 Float recall,
                                                 Float fScore,
                                                 boolean precisionGreaterThan,
                                                 boolean precisionLessThan,
                                                 boolean precisionIsEquals,
                                                 boolean recallGreaterThan,
                                                 boolean recallLessThan,
                                                 boolean recallIsEqual,
                                                 boolean fScoreGreaterThan,
                                                 boolean fScoreLessThan,
                                                 boolean fScoreIsEquals);
    public void createAiModelData(int dataSize);
    public List<AiModelDTO> getModels(String model, String taskType);
}


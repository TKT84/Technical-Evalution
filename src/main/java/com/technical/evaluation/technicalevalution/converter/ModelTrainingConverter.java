package com.technical.evaluation.technicalevalution.converter;

import com.technical.evaluation.technicalevalution.model.AiModel;
import com.technical.evaluation.technicalevalution.model.ModelTraining;
import com.technical.evaluation.technicalevalution.service.DTO.AiModelDTO;
import com.technical.evaluation.technicalevalution.service.DTO.TrainingResultDTO;

import java.util.List;

public interface ModelTrainingConverter {

    public ModelTraining toModelTraining(TrainingResultDTO trainingResultDTO);
    public TrainingResultDTO toResultTraining(ModelTraining modelTraining);
    public List<AiModelDTO> toAiModelDTOS(List<AiModel> aiModels);

    public List<TrainingResultDTO> trainingResults(List<ModelTraining> modelTrainings);
}

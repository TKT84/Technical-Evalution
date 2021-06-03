package com.technical.evaluation.technicalevalution.converter;

import com.technical.evaluation.technicalevalution.model.AiModel;
import com.technical.evaluation.technicalevalution.model.ModelTraining;
import com.technical.evaluation.technicalevalution.repository.model.QueryMetric;
import com.technical.evaluation.technicalevalution.service.DTO.AiModelDTO;
import com.technical.evaluation.technicalevalution.service.DTO.TrainingResultDTO;

import java.util.List;

public interface ModelTrainingConverter {

    public ModelTraining toModelTraining(TrainingResultDTO trainingResultDTO);
    public TrainingResultDTO toResultTraining(ModelTraining modelTraining);
    public List<AiModelDTO> toAiModelDTOS(List<AiModel> aiModels);

    public List<TrainingResultDTO> trainingResults(List<ModelTraining> modelTrainings);

    //FIXME: MOCHE!!!! a revoir!!!!!
    List<QueryMetric> getQueryMetrics(Float precision,
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
}

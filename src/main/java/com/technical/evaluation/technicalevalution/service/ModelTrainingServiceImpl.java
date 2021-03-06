package com.technical.evaluation.technicalevalution.service;

import com.technical.evaluation.technicalevalution.converter.ModelTrainingConverter;
import com.technical.evaluation.technicalevalution.exception.AiModelNotFoundException;
import com.technical.evaluation.technicalevalution.exception.InvalidDataInputException;
import com.technical.evaluation.technicalevalution.model.AiModel;
import com.technical.evaluation.technicalevalution.model.ModelTraining;
import com.technical.evaluation.technicalevalution.repository.AiModelRepository;
import com.technical.evaluation.technicalevalution.repository.ModelTrainingRepository;
import com.technical.evaluation.technicalevalution.repository.model.QueryMetric;
import com.technical.evaluation.technicalevalution.service.DTO.AiModelDTO;
import com.technical.evaluation.technicalevalution.service.DTO.TrainingResultDTO;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ModelTrainingServiceImpl implements ModelTrainingService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ModelTrainingServiceImpl.class);
    private final ModelTrainingConverter modelTrainingConverter;
    private final ModelTrainingRepository modelTrainingRepository;
    private final AiModelRepository aiModelRepository;

    @Autowired
    public ModelTrainingServiceImpl(ModelTrainingConverter modelTrainingConverter, ModelTrainingRepository modelTrainingRepository, AiModelRepository aiModelRepository) {
        this.modelTrainingConverter = modelTrainingConverter;
        this.modelTrainingRepository = modelTrainingRepository;
        this.aiModelRepository = aiModelRepository;
    }

    @Override
    public void postTraining (TrainingResultDTO trainingResultDTO) {

        if(trainingResultDTO == null) {
            LOGGER.error("the Bean ResultTrainingDTO should not be null");
            throw new InvalidDataInputException("Invalid data input.");
        }

        String model = trainingResultDTO.getModel();
        String taskType = trainingResultDTO.getTaskType();

        List<AiModel> aiModel = aiModelRepository.findAiModelsByModelAndTaskType(model, taskType);

        if (aiModel == null || aiModel.size() == 0)
            throw new AiModelNotFoundException("No model found.");

        ModelTraining modelTraining = modelTrainingConverter.toModelTraining(trainingResultDTO);

        modelTrainingRepository.save(modelTraining);
    }

    @Override
    public List<TrainingResultDTO> getResultList(
            LocalDateTime startDate,
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
            boolean fScoreIsEquals) {

        if (startDate == null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("The parameter startDate is null. skipping criterion");
            }
        }

        if (endDate == null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("The parameter endDate is null. skipping criterion");
            }
        }

        if(precision == null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("The parameter precision is null. setting default value at 0");
            }
            precision = 0f;
        }

        if(fScore == null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("The parameter precision is null. setting default value at 0");
            }
            fScore = 0f;
        }

        if(recall == null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("The parameter precision is null. setting default value at 0");
            }
            recall = 0f;
        }

        List<QueryMetric> queryMetrics = modelTrainingConverter.getQueryMetrics(
                precision,
                recall,
                fScore,
                precisionGreaterThan,
                precisionLessThan,
                precisionIsEquals,
                recallGreaterThan,
                recallLessThan,
                recallIsEqual,
                fScoreGreaterThan,
                fScoreLessThan,
                fScoreIsEquals);

        List<ModelTraining> modelTrainings = modelTrainingRepository.findByModelTraining(startDate, endDate, queryMetrics);

        if (modelTrainings == null) {
            LOGGER.error("the Bean ModelTraining should not be null");
            return null;
        }

        return modelTrainingConverter.trainingResults(modelTrainings);
    }

    @Override
    public void createAiModelData(int dataSize) {

        for (int i = 1; i <= dataSize; i++) {

            AiModel aiModel = new AiModel();
            aiModel.setId(ObjectId.get());
            aiModel.setModel("Model-" + i);
            aiModel.setTaskType("Segmentation");
            aiModelRepository.save(aiModel);

        }

        for (int i =  dataSize + 1; i <= dataSize*2; i++) {

            AiModel aiModel = new AiModel();
            aiModel.setId(ObjectId.get());
            aiModel.setModel("Model-" + i);
            aiModel.setTaskType("Classification");
            aiModelRepository.save(aiModel);
        }

        for (int i = dataSize*2 +1; i <= dataSize*3; i++) {

            AiModel aiModel = new AiModel();
            aiModel.setId(ObjectId.get());
            aiModel.setModel("Model-" + i);
            aiModel.setTaskType("Detection");
            aiModelRepository.save(aiModel);
        }
    }

    @Override
    public List<AiModelDTO> getModels(String model, String taskType) {

        if(model == null && taskType == null) {
            LOGGER.error("Missing or null required parameters.");
            throw new InvalidDataInputException("Invalid data input.");
        }

        List<AiModel> aiModels;

        if(model == null || "".equals(model)) {

            aiModels = aiModelRepository.findAiModelsByTaskType(taskType);
        } else if (taskType == null || "".equals(taskType)) {

            aiModels = aiModelRepository.findAiModelsByModel(model);
        } else {

            aiModels = aiModelRepository.findAiModelsByModelAndTaskType(model, taskType);
        }

        return modelTrainingConverter.toAiModelDTOS(aiModels);
    }
}

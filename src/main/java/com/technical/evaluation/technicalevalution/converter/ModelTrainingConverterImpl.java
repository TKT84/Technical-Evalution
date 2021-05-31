package com.technical.evaluation.technicalevalution.converter;

import com.technical.evaluation.technicalevalution.model.AiModel;
import com.technical.evaluation.technicalevalution.model.ModelTraining;
import com.technical.evaluation.technicalevalution.service.DTO.AiModelDTO;
import com.technical.evaluation.technicalevalution.service.DTO.TrainingResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModelTrainingConverterImpl implements ModelTrainingConverter{

    private static final Logger LOGGER = LoggerFactory.getLogger(ModelTrainingConverterImpl.class);

    @Override
    public ModelTraining toModelTraining(TrainingResultDTO trainingResultDTO) {

        if (trainingResultDTO == null) {
            LOGGER.error("The bean ResultTrainingDTO is null cannot convert to ModelTraining");
            return null;
        }

        AiModel aiModel = new AiModel();
        aiModel.setModel(trainingResultDTO.getModel());
        aiModel.setTaskType(trainingResultDTO.getTaskType());

        ModelTraining modelTraining = new ModelTraining();
        modelTraining.setAiModel(aiModel);
        modelTraining.setDate(trainingResultDTO.getDate());
        modelTraining.setfScore(trainingResultDTO.getfScore());
        modelTraining.setRecall(trainingResultDTO.getRecall());
        modelTraining.setPrecision(trainingResultDTO.getPrecision());

        return modelTraining;
    }

    @Override
    public TrainingResultDTO toResultTraining(ModelTraining modelTraining) {

        if (modelTraining == null) {
            LOGGER.error("The bean ModelTraining is null cannot convert to ResultTrainingDTO");
            return null;
        }
        String model = null;
        String taskType = null;
        AiModel aiModel = modelTraining.getAiModel();
        if (aiModel != null) {

            model = aiModel.getModel();
            taskType = aiModel.getTaskType();
        }

        return new TrainingResultDTO(model,
                taskType,
                modelTraining.getDate(),
                modelTraining.getPrecision(),
                modelTraining.getRecall(),
                modelTraining.getfScore());
    }

    @Override
    public List<AiModelDTO> toAiModelDTOS(List<AiModel> aiModels) {

        if (aiModels == null || aiModels.size() == 0) {
            return null;
        }

        List<AiModelDTO> toAiModelDTOS = new ArrayList<>();

        for (AiModel aiModel: aiModels) {

            AiModelDTO aiModelDTO = toAiModelDTO(aiModel);
            toAiModelDTOS.add(aiModelDTO);
        }

        return toAiModelDTOS;
    }

    public AiModelDTO toAiModelDTO(AiModel aiModel) {

        AiModelDTO aiModelDTO = new AiModelDTO();
        aiModelDTO.setModel(aiModel.getModel());
        aiModelDTO.setTaskType(aiModel.getTaskType());

        return aiModelDTO;
    }

    @Override
    public List<TrainingResultDTO> trainingResults(List<ModelTraining> modelTrainings) {

        if(modelTrainings == null || modelTrainings.size() == 0) {
            return null;
        }

        List<TrainingResultDTO> trainingResultDTOS = new ArrayList<>();

        for (ModelTraining modelTraining : modelTrainings) {
            TrainingResultDTO trainingResultDTO = toResultTraining(modelTraining);
            trainingResultDTOS.add(trainingResultDTO);
        }

        return trainingResultDTOS;
    }
}

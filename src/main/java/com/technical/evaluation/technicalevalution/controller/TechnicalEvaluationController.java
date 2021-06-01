package com.technical.evaluation.technicalevalution.controller;

import com.technical.evaluation.technicalevalution.exception.AiModelNotFoundException;
import com.technical.evaluation.technicalevalution.exception.InvalidDataInputException;
import com.technical.evaluation.technicalevalution.service.DTO.AiModelDTO;
import com.technical.evaluation.technicalevalution.service.DTO.TrainingResultDTO;
import com.technical.evaluation.technicalevalution.service.ModelTrainingService;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TechnicalEvaluationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TechnicalEvaluationController.class);
    private final ModelTrainingService modelTrainingService;

    @PostMapping(value="/trainings")
    public ResponseEntity<String> postTraining(@RequestBody() TrainingResultDTO trainingResultDTO){

        try {
            modelTrainingService.postTraining(trainingResultDTO);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("",e);
            return new ResponseEntity<>("KO", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/trainings")
    public ResponseEntity<List<TrainingResultDTO>> getTrainingResults(
            @RequestParam(name = "startDate")
            @DateTimeFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss.SSS") LocalDateTime startDate,
            @RequestParam(name = "endDate")
            @DateTimeFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss.SSS") LocalDateTime enDate,
            @ApiParam(value="start precision") Float precision,
            @ApiParam(value="start recall") Float recall,
            @ApiParam(value="start f-score") Float fScore,
            @ApiParam(value="greater than") boolean greaterThan,
            @ApiParam(value="lower than") boolean lowerThan,
            @ApiParam(value="equals") boolean isEquals
            ){

        List<TrainingResultDTO> trainingResultDTOS = modelTrainingService.getResultList(startDate, enDate, precision, fScore,  recall, greaterThan, lowerThan, isEquals);

        return new ResponseEntity<>(trainingResultDTOS, HttpStatus.OK);
    }

    @PostMapping(value ="/model/data/{dataSize}")
    public ResponseEntity<String>  createAiModelData(@PathVariable(value="dataSize") int dataSize) {

        if (dataSize < 1) {
            throw new InvalidDataInputException("the value of the dataSize should be >= 1");
        }

        modelTrainingService.createAiModelData(dataSize);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping(value ="/models")
    public ResponseEntity<List<AiModelDTO>>  getModels(@ApiParam(value="Model Name") String modelName,
                                                @ApiParam(value="Task Type")  String taskType) {

        List<AiModelDTO> aiModelDTOS = modelTrainingService.getModels(modelName, taskType);

        if (aiModelDTOS == null || aiModelDTOS.size() == 0) {
            throw new AiModelNotFoundException("No model found.");
        }

        return new ResponseEntity<>(aiModelDTOS, HttpStatus.OK);
    }

    @Autowired
    public TechnicalEvaluationController(ModelTrainingService modelTrainingService) {
        this.modelTrainingService = modelTrainingService;
    }
}

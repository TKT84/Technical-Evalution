package com.technical.evaluation.technicalevalution.exception;


import com.technical.evaluation.technicalevalution.controller.TechnicalEvaluationController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class TrainingResultExceptionHandler{

    private static final Logger LOGGER = LoggerFactory.getLogger(TrainingResultExceptionHandler.class);

    @ExceptionHandler(value = TrainingResultNotFoundException.class)
    public ResponseEntity<ErrorJson> exception(TrainingResultNotFoundException exception) {
        ErrorJson errorJson = new ErrorJson();
        errorJson.setErrorCode(exception.getCode());
        errorJson.setErrorMessage(exception.getMessage());
        errorJson.setExceptionType(exception.getClass().getSimpleName());
        return new  ResponseEntity<>(errorJson, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidDataInputException.class)
    public ResponseEntity<ErrorJson> exception(InvalidDataInputException exception) {
        ErrorJson errorJson = new ErrorJson();
        errorJson.setErrorCode(exception.getCode());
        errorJson.setErrorMessage(exception.getMessage());
        errorJson.setExceptionType(exception.getClass().getSimpleName());
        return new  ResponseEntity<>(errorJson, HttpStatus.BAD_REQUEST);
    }
}

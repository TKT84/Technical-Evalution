package com.technical.evaluation.technicalevalution.repository;

import com.technical.evaluation.technicalevalution.model.ModelTraining;
import org.joda.time.DateTime;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface ModelTrainingCustomRepository {

    public List<ModelTraining> findByModelTraining(LocalDateTime startDate, LocalDateTime endDate, float precision, float fScore, float recall, boolean greaterThan, boolean lowerThan, boolean isEquals);

}

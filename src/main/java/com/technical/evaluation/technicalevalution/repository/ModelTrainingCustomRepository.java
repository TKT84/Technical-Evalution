package com.technical.evaluation.technicalevalution.repository;

import com.technical.evaluation.technicalevalution.model.ModelTraining;
import com.technical.evaluation.technicalevalution.repository.model.QueryMetric;

import java.time.LocalDateTime;
import java.util.List;

public interface ModelTrainingCustomRepository {

    public List<ModelTraining> findByModelTraining(LocalDateTime startDate, LocalDateTime endDate, List<QueryMetric> queryMetrics);

}

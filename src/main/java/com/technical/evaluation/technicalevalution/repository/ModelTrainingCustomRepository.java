package com.technical.evaluation.technicalevalution.repository;

import com.technical.evaluation.technicalevalution.model.ModelTraining;
import org.joda.time.DateTime;
import java.util.List;

public interface ModelTrainingCustomRepository {

    public List<ModelTraining> findByModelTraining(DateTime dateTime, float precision, float fScore, float recall, boolean greaterThan, boolean lowerThan, boolean isEquals);

}
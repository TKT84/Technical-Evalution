package com.technical.evaluation.technicalevalution.repository;

import com.technical.evaluation.technicalevalution.model.ModelTraining;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ModelTrainingRepository extends MongoRepository<ModelTraining, Long>, ModelTrainingCustomRepository {


}

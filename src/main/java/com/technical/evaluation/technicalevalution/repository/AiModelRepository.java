package com.technical.evaluation.technicalevalution.repository;

import com.technical.evaluation.technicalevalution.model.AiModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AiModelRepository extends MongoRepository<AiModel, Long> {

    @Query(value = "{'model': ?0 , 'taskType': ?1}")
    public List<AiModel> findAiModelsByModelAndTaskType(String model, String taskType);

    @Query(value = "{'model': ?0}")
    public List<AiModel> findAiModelsByModel(String model);

    @Query(value = "{'taskType': ?0}")
    public List<AiModel> findAiModelsByTaskType(String taskType);
}

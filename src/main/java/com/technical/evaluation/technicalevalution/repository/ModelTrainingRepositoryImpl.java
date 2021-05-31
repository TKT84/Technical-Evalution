package com.technical.evaluation.technicalevalution.repository;

import com.technical.evaluation.technicalevalution.exception.InvalidDataInputException;
import com.technical.evaluation.technicalevalution.model.ModelTraining;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ModelTrainingRepositoryImpl implements ModelTrainingCustomRepository {

    private MongoTemplate mongoTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(ModelTrainingCustomRepository.class);

    @Autowired
    public ModelTrainingRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<ModelTraining> findByModelTraining(DateTime dateTime, float precision, float fScore, float recall, boolean greaterThan, boolean lowerThan, boolean isEquals) {

        List<ModelTraining> trainings = new ArrayList<>();

        if (greaterThan) {
            trainings = findModelTrainingByDateAndPrecisionAndRecallAndFScoreGreaterThan(dateTime, precision, recall, fScore);
        }

        if (lowerThan) {
            trainings = findModelTrainingByDateAndPrecisionAndRecallAndFScoreLowerThan( dateTime, precision, recall, fScore);
        }

        if(isEquals){
            trainings = findModelTrainingByDateAndPrecisionAndRecallAndFScore( dateTime, precision, recall, fScore);
        }

        return trainings;
    }

    public List<ModelTraining> findModelTrainingByDateAndPrecisionAndRecallAndFScore(DateTime date, float precision, float recall, float fScore){

        if (date == null) {
            throw new InvalidDataInputException("Invalid data input 'DateTime'");
        }

        Query query = new Query();

        List<Criteria> criteria = new ArrayList<>();

        if(precision > 0f && precision < 1f) {
            criteria.add(Criteria.where("precision").is(precision));
        } else {
            LOGGER.error("metric precision with value = " + precision + " is outside the research parameters");
        }

        if(precision > 0f && precision < 1f) {
            criteria.add(Criteria.where("recall").is(recall));
        } else {
            LOGGER.error("metric recall with value = " + recall + " is outside the research parameters");
        }

        if(fScore > 0f && fScore < 1f) {
            criteria.add(Criteria.where("precision").is(fScore));
        } else {
            LOGGER.error("metric fScore with value = " + fScore + " is outside the research parameters");
        }

        return mongoTemplate.find(query, ModelTraining.class);
    }

    public List<ModelTraining> findModelTrainingByDateAndPrecisionAndRecallAndFScoreGreaterThan(DateTime date, float precision, float recall, float fScore) {

        if (date == null) {
            throw new InvalidDataInputException("Invalid data input 'DateTime'");
        }

        Query query = new Query();

        List<Criteria> criteria = new ArrayList<>();

        if (precision > 0f && precision < 1f) {
            criteria.add(Criteria.where("precision").gte(precision));
        } else {
            LOGGER.error("metric precision with value = " + precision + " is outside the research parameters");
        }

        if (precision > 0f && precision < 1f) {
            criteria.add(Criteria.where("recall").gte(recall));
        } else {
            LOGGER.error("metric recall with value = " + recall + " is outside the research parameters");
        }

        if (fScore > 0f && fScore < 1f) {
            criteria.add(Criteria.where("precision").gte(fScore));
        } else {
            LOGGER.error("metric fScore with value = " + fScore + " is outside the research parameters");
        }

        return mongoTemplate.find(query, ModelTraining.class);
    }

    public List<ModelTraining> findModelTrainingByDateAndPrecisionAndRecallAndFScoreLowerThan(DateTime date, float precision, float recall, float fScore){

        if (date == null) {
            throw new InvalidDataInputException("Invalid data input 'DateTime'");
        }

        Query query = new Query();

        List<Criteria> criteria = new ArrayList<>();

        if(precision > 0f && precision < 1f) {
            criteria.add(Criteria.where("precision").lte(precision));
        } else {
            LOGGER.error("metric precision with value = " + precision + " is outside the research parameters");
        }

        if(precision > 0f && precision < 1f) {
            criteria.add(Criteria.where("recall").lte(recall));
        } else {
            LOGGER.error("metric recall with value = " + recall + " is outside the research parameters");
        }

        if(fScore > 0f && fScore < 1f) {
            criteria.add(Criteria.where("precision").lte(fScore));
        } else {
            LOGGER.error("metric fScore with value = " + fScore + " is outside the research parameters");
        }

        return mongoTemplate.find(query, ModelTraining.class);
    }
}

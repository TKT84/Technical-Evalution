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
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ModelTrainingRepositoryImpl implements ModelTrainingCustomRepository {

    private final MongoTemplate mongoTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(ModelTrainingCustomRepository.class);

    @Autowired
    public ModelTrainingRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<ModelTraining> findByModelTraining(DateTime date, float precision, float recall, float fScore, boolean greaterThan, boolean lessThan, boolean isEquals){

        if (date == null) {
            throw new InvalidDataInputException("Invalid data input 'DateTime'");
        }

        Query query = getQuery(date, precision, recall, fScore, greaterThan, lessThan, isEquals);

        return mongoTemplate.find(query, ModelTraining.class);
    }

    private Query getQuery(DateTime date, float precision, float recall, float fScore, boolean greaterThan, boolean lessThan, boolean isEquals) {

        Query query = new Query();

        Criteria dateCriteria = this.getCriteriaIsEqualDate(date);
        query.addCriteria(dateCriteria);

        if (precision > 0f && precision < 1f) {
            Criteria precisionCriteria = this.getMetricCriteria("precision",precision, greaterThan, lessThan, isEquals);
            query.addCriteria(precisionCriteria);
        } else {
            LOGGER.error("metric precision with value = " + precision + " is outside the research parameters");
        }

        if (recall > 0f && recall < 1f) {
            Criteria recallCriteria =  this.getMetricCriteria("recall", recall, greaterThan, lessThan, isEquals);
            query.addCriteria(recallCriteria);
        } else {
            LOGGER.error("metric recall with value = " + recall + " is outside the research parameters");
        }

        if (fScore > 0f && fScore < 1f) {
            Criteria fScoreCriteria =  this.getMetricCriteria("fScore", fScore, greaterThan, lessThan, isEquals);
            query.addCriteria(fScoreCriteria);
        } else {
            LOGGER.error("metric fScore with value = " + fScore + " is outside the research parameters");
        }
        return query;
    }

    private  Criteria getMetricCriteria (String metricName, float metric, boolean greaterThan, boolean lessThan, boolean isEquals) {

        Criteria criteria = new Criteria();

        if (metric > 0f && metric < 1f) {

            if (greaterThan) {
                criteria = Criteria.where(metricName).gte(metric);
            }

            if (lessThan) {
                criteria = Criteria.where(metricName).lte(metric);
            }

            if (isEquals) {
                criteria = Criteria.where(metricName).is(metric);
            }
        } else {
            LOGGER.error(" metric "+ metricName +" with value = " + metric + " is outside the research parameters");
        }

        return criteria;
    }

    private  Criteria getCriteriaIsEqualDate(Object obj) {
        return Criteria.where("date").gte(obj);
    }
}

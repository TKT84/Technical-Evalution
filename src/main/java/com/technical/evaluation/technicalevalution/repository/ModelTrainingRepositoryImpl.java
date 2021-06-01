package com.technical.evaluation.technicalevalution.repository;

import com.technical.evaluation.technicalevalution.model.ModelTraining;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
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
    public List<ModelTraining> findByModelTraining(LocalDateTime startDate, LocalDateTime endDate, float precision, float recall, float fScore, boolean greaterThan, boolean lessThan, boolean isEquals){

        Query query = getQuery(startDate, endDate, precision, recall, fScore, greaterThan, lessThan, isEquals);

        return mongoTemplate.find(query, ModelTraining.class);
    }

    private Query getQuery(LocalDateTime startDate, LocalDateTime endDate, float precision, float recall, float fScore, boolean greaterThan, boolean lessThan, boolean isEquals) {

        Query query = new Query();

        Criteria dateCriteria = this.getDateCriteria(startDate, endDate);
        query.addCriteria(dateCriteria).with(Sort.by(Sort.Direction.DESC, "date"));

        if (precision > 0f && precision < 1f) {
            Criteria precisionCriteria = this.getMetricCriteria("precision",precision, greaterThan, lessThan, isEquals);
            query.addCriteria(precisionCriteria).with(Sort.by(Sort.Direction.DESC, "precision"));
        } else {
            LOGGER.warn("metric precision with value = " + precision + " is outside the research parameters");
        }

        if (recall > 0f && recall < 1f) {
            Criteria recallCriteria =  this.getMetricCriteria("recall", recall, greaterThan, lessThan, isEquals);
            query.addCriteria(recallCriteria).with(Sort.by(Sort.Direction.DESC, "recall"));
        } else {
            LOGGER.warn("metric recall with value = " + recall + " is outside the research parameters");
        }

        if (fScore > 0f && fScore < 1f) {
            Criteria fScoreCriteria =  this.getMetricCriteria("fScore", fScore, greaterThan, lessThan, isEquals);
            query.addCriteria(fScoreCriteria).with(Sort.by(Sort.Direction.DESC, "fScore"));
        } else {
            LOGGER.warn("metric fScore with value = " + fScore + " is outside the research parameters");
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

    private  Criteria getDateCriteria(LocalDateTime start, LocalDateTime end) {
        return Criteria.where("date").gte(start).lt(end);
    }
}

package com.technical.evaluation.technicalevalution.repository;

import com.technical.evaluation.technicalevalution.model.ModelTraining;
import com.technical.evaluation.technicalevalution.repository.model.QueryMetric;
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
    public List<ModelTraining> findByModelTraining(LocalDateTime startDate, LocalDateTime endDate, List<QueryMetric> queryMetrics){

        if (queryMetrics == null || queryMetrics.size() == 0)
            return null;

        Query query = getQuery(startDate, endDate, queryMetrics);

        return mongoTemplate.find(query, ModelTraining.class);
    }

    private Query getQuery(LocalDateTime startDate, LocalDateTime endDate, List<QueryMetric> queryMetrics) {

        Query query = new Query();

        Criteria dateCriteria = this.getDateCriteria(startDate, endDate);
        query.addCriteria(dateCriteria).with(Sort.by(Sort.Direction.DESC, "date"));

        for (QueryMetric queryMetric : queryMetrics) {

            Criteria precisionCriteria = this.getMetricCriteria(queryMetric.getMetricEnum().getName(),queryMetric.getMetric(), queryMetric.isGreaterThan(), queryMetric.isLessThan(), queryMetric.isEqual());
            query.addCriteria(precisionCriteria).with(Sort.by(Sort.Direction.DESC, queryMetric.getMetricEnum().getName()));
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

        Criteria criteria = new Criteria();

        if (start == null && end == null) {
            LOGGER.warn("Skipping date filter");
        } else {

            if (start == null) {
                criteria = Criteria.where("date").lt(end);
            }

            if (end == null) {
                criteria = Criteria.where("date").gte(start);
            }

            if (start != null && end != null) {
                criteria = Criteria.where("date").gte(start).lt(end);
            }
        }

        return criteria;
    }
}

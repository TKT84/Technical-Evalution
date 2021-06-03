package com.technical.evaluation.technicalevalution.repository.model;

public class QueryMetric {

    private boolean isGreaterThan;
    private boolean isLessThan;
    private boolean isEqual;
    private float metric;
    private MetricEnum metricEnum;

    public QueryMetric() {
    }

    public QueryMetric(float metric, boolean isGreaterThan, boolean isLessThan, boolean isEqual, MetricEnum metricEnum) {
        this.isGreaterThan = isGreaterThan;
        this.isLessThan = isLessThan;
        this.isEqual = isEqual;
        this.metric = metric;
        this.metricEnum = metricEnum;
    }

    public boolean isGreaterThan() {
        return isGreaterThan;
    }

    public void setGreaterThan(boolean greaterThan) {
        isGreaterThan = greaterThan;
    }

    public boolean isLessThan() {
        return isLessThan;
    }

    public void setLessThan(boolean lessThan) {
        isLessThan = lessThan;
    }

    public boolean isEqual() {
        return isEqual;
    }

    public void setEqual(boolean equal) {
        isEqual = equal;
    }

    public float getMetric() {
        return metric;
    }

    public void setMetric(float metric) {
        this.metric = metric;
    }

    public MetricEnum getMetricEnum() {
        return metricEnum;
    }

    public void setMetricEnum(MetricEnum metricEnum) {
        this.metricEnum = metricEnum;
    }
}

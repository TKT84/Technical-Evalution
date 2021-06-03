package com.technical.evaluation.technicalevalution.repository.model;

public enum MetricEnum {

    PRECISION("precision"), RECALL("recall"), FSCORE("fscore");

    private String name;

    MetricEnum() {
    }

    MetricEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

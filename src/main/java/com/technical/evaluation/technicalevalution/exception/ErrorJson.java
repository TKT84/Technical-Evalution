package com.technical.evaluation.technicalevalution.exception;

public class ErrorJson {

    private String errorCode;
    private String errorMessage;
    private String exceptionType;

    public ErrorJson(String errorCode, String errorMessage, String exceptionType) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.exceptionType = exceptionType;
    }

    public ErrorJson(String errorCode, String exceptionType) {
        this.errorCode = errorCode;
        this.exceptionType = exceptionType;
    }

    public ErrorJson() {
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }
}

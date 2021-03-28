package com.stockmarket.technicalanalysis.exception;

public class TechnicalAnalysisException extends Exception {
    public TechnicalAnalysisException(String exceptionMsg) {
        super(exceptionMsg);
    }
    public TechnicalAnalysisException(String exceptionMsg, Exception throwableException){
        super(exceptionMsg, throwableException);
    }
}

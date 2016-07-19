package com.thoughtworks.ketsu.web.exception;

import java.util.*;

public class InvalidParameterException extends RuntimeException {

    private List<InvalidErrorInfo> exceptionList;

    public InvalidParameterException(String message) {
        super(message);
    }

    public InvalidParameterException(List<String> parameterFields) {
        exceptionList = new ArrayList<InvalidErrorInfo>();

        for (String field: parameterFields) {
            exceptionList.add(new InvalidErrorInfo(field));
        }
    }

    public List<InvalidErrorInfo> getExceptionList(){
        return exceptionList;
    }


    public InvalidParameterException() {
        super();
    }

    public InvalidParameterException(Exception e) {
        super(e);
    }


}

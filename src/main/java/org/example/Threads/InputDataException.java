package org.example.Threads;

public class InputDataException extends Exception {
    private String exceptionDescribe;
    public InputDataException(String message){
        this.exceptionDescribe = message;
    }

    public String getExceptionDescribe() {
        return exceptionDescribe;
    }
}

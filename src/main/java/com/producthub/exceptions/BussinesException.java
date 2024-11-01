package com.producthub.exceptions;

public class BussinesException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final int errorCode = 400;

    public BussinesException(String msg) {
        super(msg);
    }

    public int getErrorCode() {
        return errorCode;
    }
}
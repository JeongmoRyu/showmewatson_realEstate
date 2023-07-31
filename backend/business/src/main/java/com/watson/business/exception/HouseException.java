package com.watson.business.exception;

public class HouseException extends RuntimeException {

    private final int code;

    public int getCode() {
        return code;
    }

    public HouseException(HouseErrorCode errorCode) {
        super(errorCode.getDescription());
        this.code = errorCode.getCode();
    }
}

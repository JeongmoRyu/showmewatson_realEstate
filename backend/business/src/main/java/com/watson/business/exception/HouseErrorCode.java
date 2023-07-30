package com.watson.business.exception;

public enum HouseErrorCode {
    NOT_FOUND_HOUSE_INFO(404,"찾을 수 없는 매물입니다.");
    private int code;
    private String description;
    private HouseErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }
    public int getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
}

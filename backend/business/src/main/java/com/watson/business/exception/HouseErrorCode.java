package com.watson.business.exception;

public enum HouseErrorCode {
    NOT_FOUND_HOUSE_INFO(404,"찾을 수 없는 매물입니다."),
    NOT_FOUND_HOUSE_LIST(404,"매물 리스트를 불러오는데 실패했습니다."),
    NOT_FOUND_USER(401,"다시 로그인해주세요"),
    NOT_REALTOR_USER(401,"권한이 없습니다."),
    NOT_REGIST_HOUSE(500,"등록에 실패했습니다."),
    FAIL_REGIST_CHECKLIST(500,"체크리스트 등록에 실패했습니다."),
    FAIL_FOUND_CHECKLIST(404,"체크리스트 등록에 실패했습니다."),
    NOT_FOUND_LIVE_INFO(404,"존재하지 않는 라이브입니다."),
    NOT_FOUND_LIVE_NOTI(500,"라이브 알림을 신청하지 않았습니다."),
    NOT_REGIST_LIVE_SCHEDULE(500,"라이브 등록에 실패했습니다."),
    NOT_FOUND_FCMTOKEN(404,"FcmToken을 찾을 수 없습니다."),
    NOT_FOUND_LIVE_NOTICE_LIST(404,"알림 목록을 불러오는데 실패했습니다."),
    NOT_FOUND_REALTOR(404,"중개사 정보를 가져올 수 없습니다"),
    NOT_FOUND_EXCEPTION(500,"알수 없는 에러가 발생했습니다.");
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

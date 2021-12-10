package com.dayz.common.enums;

import lombok.Getter;

@Getter
public enum ErrorInfo {

    UNKNOWN("UNKNOWN", "서버 에러로 인해 데이터를 로드 할 수 없습니다."),
    LOGIN_FAIL("LOGIN_FAIL", "로그인에 실패하셨습니다."),

    MEMBER_NOT_FOUND("ERR001","존재하지 않는 사용자입니다.");

    ErrorInfo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;
    private String message;

}
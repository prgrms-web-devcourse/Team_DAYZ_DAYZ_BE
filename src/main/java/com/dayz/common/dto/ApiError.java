package com.dayz.common.dto;

import com.dayz.common.enums.ErrorInfo;
import lombok.Getter;

@Getter
public class ApiError {

    private String code;

    private Object message;

    private ApiError(String code, Object message) {
        this.code = code;
        this.message = message;
    }

    static ApiError of(ErrorInfo errorInfo) {
        return new ApiError(errorInfo.getCode(), errorInfo.getMessage());
    }

    static ApiError of(String code, Object messages) {
        return new ApiError(code, messages);
    }

}

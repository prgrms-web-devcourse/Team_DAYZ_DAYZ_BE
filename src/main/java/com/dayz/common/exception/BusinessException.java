package com.dayz.common.exception;

import com.dayz.common.enums.ErrorInfo;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private ErrorInfo errorInfo;

    public BusinessException(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

}

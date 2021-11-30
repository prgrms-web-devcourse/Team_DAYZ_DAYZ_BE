package com.dayz.common.dto;

import com.dayz.common.enums.ErrorInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ApiResponse<T> {

    private boolean success;

    private T payload;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime serverDatetime;

    protected ApiResponse(boolean success, T payload) {
        this.success = success;
        this.payload = payload;
        this.serverDatetime = LocalDateTime.now();
    }

    public static <T> ApiResponse<T> ok(T response) {
        return new ApiResponse<>(true, response);
    }

    public static ApiResponse error(ErrorInfo errorInfo) {
        return new ApiResponse<>(false, ApiError.of(errorInfo));
    }

    public static ApiResponse error(String code, Object messages) {
        return new ApiResponse<>(false, ApiError.of(code, messages));
    }

}

package com.dayz.common.enums;

import lombok.Getter;

@Getter
public enum TimeStatus {

    PROCESS("PROCESS"),
    CLOSED("CLOSED");

    private String value;

    TimeStatus(String value) {
        this.value = value;
    }

}

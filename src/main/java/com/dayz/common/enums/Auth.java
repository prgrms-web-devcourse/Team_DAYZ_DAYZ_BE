package com.dayz.common.enums;

import lombok.Getter;

@Getter
public enum Auth {

    USER("USER"),
    ATELIER("ATELIER"),
    ADMIN("ADMIN");

    private String value;

    Auth(String value) {
        this.value = value;
    }

}

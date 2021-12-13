package com.dayz.common.enums;

import lombok.Getter;

@Getter
public enum ReservationStatus {

    ACCEPTED("ACCEPTED"),
    CANCELED("CANCELED");

    private String value;

    ReservationStatus(String value) {
        this.value = value;
    }

}

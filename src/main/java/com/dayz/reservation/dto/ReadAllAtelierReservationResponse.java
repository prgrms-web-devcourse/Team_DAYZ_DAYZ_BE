package com.dayz.reservation.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
public class ReadAllAtelierReservationResponse {

    private Long reservationId;

    private String className;

    private String reservationDate;

    private String classDate;

    private String startTime;

    private String endTime;

    private String status;

    public static ReadAllAtelierReservationResponse of(Long reservationId, String className,
        String reservationDate, String classDate, String startTime, String endTime,
        String status) {

        ReadAllAtelierReservationResponse readAllMyReservationResponse = new ReadAllAtelierReservationResponse();
        readAllMyReservationResponse.setReservationId(reservationId);
        readAllMyReservationResponse.setClassName(className);
        readAllMyReservationResponse.setReservationDate(reservationDate);
        readAllMyReservationResponse.setClassDate(classDate);
        readAllMyReservationResponse.setStartTime(startTime);
        readAllMyReservationResponse.setEndTime(endTime);
        readAllMyReservationResponse.setStatus(status);

        return readAllMyReservationResponse;
    }
}

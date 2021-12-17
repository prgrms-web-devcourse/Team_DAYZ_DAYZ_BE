package com.dayz.reservation.dto;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
public class ReadAllMyReservationResponse {

    private Long reservationId;

    private String className;

    private String reservationDate;

    private String classDate;

    private String startTime;

    private String endTime;

    private String status;

    public static ReadAllMyReservationResponse of(Long reservationId, String className,
        String reservationDate, String classDate, String startTime, String endTime,
        String status) {

        ReadAllMyReservationResponse readAllMyReservationResponse = new ReadAllMyReservationResponse();
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

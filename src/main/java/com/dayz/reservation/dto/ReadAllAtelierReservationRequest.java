package com.dayz.reservation.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Getter
@NoArgsConstructor
public class ReadAllAtelierReservationRequest {

    private Long reservationId;

    private String className;

    private LocalDate reservationDate;

    private LocalDate classDate;

    private Long startTime;

    private Long endTime;

    private String status;

    @QueryProjection
    public ReadAllAtelierReservationRequest(Long reservationId, String className, LocalDate reservationDate, LocalDate classDate,
        Long startTime,
        Long endTime, String status) {

        Assert.notNull(reservationId,"Reservation reservationId null 입니다.");
        Assert.notNull(className,"Reservation className null 입니다.");
        Assert.notNull(reservationDate,"Reservation reservationDate null 입니다.");
        Assert.notNull(classDate,"Reservation classDate null 입니다.");
        Assert.notNull(startTime,"Reservation startTime null 입니다.");
        Assert.notNull(endTime,"Reservation endTime null 입니다.");
        Assert.notNull(status,"Reservation status null 입니다.");

        this.reservationId = reservationId;
        this.className = className;
        this.reservationDate = reservationDate;
        this.classDate = classDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }
}

package com.dayz.reservation.dto;

import com.dayz.common.enums.ReservationStatus;
import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@NoArgsConstructor
public class ReadAllMyReservationRequest {

    private Long reservationId;

    private String className;

    private LocalDate reservationDate;

    private LocalDate classDate;

    private Long startTime;

    private Long endTime;

    private String status;

    @QueryProjection
    public ReadAllMyReservationRequest(Long reservationId, String className, LocalDate reservationDate, LocalDate classDate,
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

package com.dayz.reservation.converter;

import com.dayz.common.util.TimeUtil;
import com.dayz.member.domain.Member;
import com.dayz.onedayclass.domain.OneDayClassTime;
import com.dayz.reservation.domain.Reservation;
import com.dayz.reservation.dto.ReadAllAtelierReservationRequest;
import com.dayz.reservation.dto.ReadAllAtelierReservationResponse;
import com.dayz.reservation.dto.ReadAllMyReservationRequest;
import com.dayz.reservation.dto.ReadAllMyReservationResponse;
import com.dayz.reservation.dto.SaveReservationRequest;
import com.dayz.review.dto.ReadAllAtelierReviewsResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationConverter {

    private final TimeUtil timeUtil;

    public Reservation convertReservation(SaveReservationRequest saveReservationRequest,
        Member member, OneDayClassTime oneDayClassTime) {
        return Reservation.of(saveReservationRequest.getPeopleNumber(), saveReservationRequest.getPrice(),
            LocalDate.now(), member, oneDayClassTime);
    }

    public ReadAllMyReservationResponse convertReadAllMyReviewsResponse(
        ReadAllMyReservationRequest reservation) {

        return ReadAllMyReservationResponse.of(reservation.getReservationId(),
            reservation.getClassName()
            , convertDate(reservation.getReservationDate())
            , convertDate(reservation.getClassDate())
            , timeUtil.secondToTimeString(reservation.getStartTime())
            , timeUtil.secondToTimeString(reservation.getEndTime())
            , reservation.getStatus());
    }

    public ReadAllAtelierReservationResponse convertReadAllAtelierReviewsResponse(
        ReadAllAtelierReservationRequest reservation) {

        return ReadAllAtelierReservationResponse.of(reservation.getReservationId(),
            reservation.getClassName()
            , convertDate(reservation.getReservationDate())
            , convertDate(reservation.getClassDate())
            , timeUtil.secondToTimeString(reservation.getStartTime())
            , timeUtil.secondToTimeString(reservation.getEndTime())
            , reservation.getStatus());
    }

    public String convertDate(LocalDate date) {

        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}

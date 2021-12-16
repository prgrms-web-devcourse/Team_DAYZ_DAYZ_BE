package com.dayz.reservation.service;

import com.dayz.common.dto.CustomPageRequest;
import com.dayz.common.dto.CustomPageResponse;
import com.dayz.common.enums.ErrorInfo;
import com.dayz.common.exception.BusinessException;
import com.dayz.member.domain.Member;
import com.dayz.onedayclass.domain.OneDayClassTime;
import com.dayz.onedayclass.domain.OneDayClassTimeRepository;
import com.dayz.reservation.domain.Reservation;
import com.dayz.reservation.domain.ReservationRepository;
import com.dayz.reservation.converter.ReservationConverter;
import com.dayz.reservation.dto.ReadAllAtelierReservationResponse;
import com.dayz.reservation.dto.ReadAllMyReservationResponse;
import com.dayz.reservation.dto.SaveReservationRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final ReservationConverter reservationConverter;

    private final OneDayClassTimeRepository oneDayClassTimeRepository;

    @Transactional
    public Long saveReservation(SaveReservationRequest saveReservationRequest, Member member){
        OneDayClassTime oneDayClassTime = oneDayClassTimeRepository.findById(saveReservationRequest.getClassTimeId())
            .orElseThrow(() -> new BusinessException(ErrorInfo.ONE_DAY_CLASS_TIME_NOT_FOUND));
        Reservation reservation = reservationConverter.convertReservation(saveReservationRequest,member,oneDayClassTime);

        return reservationRepository.save(reservation).getId();
    }

    public CustomPageResponse <ReadAllMyReservationResponse> getMyReservation(CustomPageRequest pageRequest, Long memberId) {
        PageRequest pageable = pageRequest.convertToPageRequest(Reservation.class);

        Page<ReadAllMyReservationResponse> responsePage = reservationRepository.findMyReservation(memberId, pageable)
            .map(reservationConverter::convertReadAllMyReviewsResponse);

        return CustomPageResponse.of(responsePage);
    }

    public CustomPageResponse <ReadAllAtelierReservationResponse> getAtelierReservation(CustomPageRequest pageRequest, Long atelierId) {
        PageRequest pageable = pageRequest.convertToPageRequest(Reservation.class);

        Page<ReadAllAtelierReservationResponse> responsePage = reservationRepository.findAtelierReservation(atelierId, pageable)
            .map(reservationConverter::convertReadAllAtelierReviewsResponse);

        return CustomPageResponse.of(responsePage);
    }


}

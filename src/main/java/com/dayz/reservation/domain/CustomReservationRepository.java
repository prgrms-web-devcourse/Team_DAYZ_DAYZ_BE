package com.dayz.reservation.domain;

import com.dayz.reservation.dto.ReadAllAtelierReservationRequest;
import com.dayz.reservation.dto.ReadAllAtelierReservationResponse;
import com.dayz.reservation.dto.ReadAllMyReservationRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomReservationRepository {

    Page<ReadAllMyReservationRequest> findMyReservation(Long memberId, Pageable pageable);

    Page<ReadAllAtelierReservationRequest> findAtelierReservation(Long atelierId, Pageable pageable);

}

package com.dayz.reservation.domain;

import com.dayz.reservation.dto.ReadAllAtelierReservationRequest;
import com.dayz.reservation.dto.ReadAllMyReservationRequest;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationRepository extends JpaRepository<Reservation, Long>,CustomReservationRepository {

    @Query("select r from Reservation r JOIN fetch r.oneDayClassTime t join fetch t.oneDayClass o where r.id=:reservationId")
    Optional<Reservation> findByreservationId(@Param("reservationId") Long reservationId);

    Page<ReadAllMyReservationRequest> findMyReservation(Long memberId, Pageable pageable);

    Page<ReadAllAtelierReservationRequest> findAtelierReservation(Long atelierId, Pageable pageable);
}

package com.dayz.reservation.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("select r from Reservation r JOIN fetch r.oneDayClassTime t join fetch t.oneDayClass o where r.id=:reservationId")
    Optional<Reservation> findByreservationId(@Param("reservationId") Long reservationId);
}
